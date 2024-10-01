package kernel360.techpick.feature.rss.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import kernel360.techpick.feature.rss.exception.ApiRssException;
import kernel360.techpick.core.model.rss.RssRawData;
import kernel360.techpick.core.model.rss.RssSupportingBlog;
import kernel360.techpick.feature.rss.model.RssProvider;
import kernel360.techpick.feature.rss.service.dto.RssResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RssService {

	private final RestTemplate restTemplate;
	private final RssProvider rssProvider;

	// TODO: 하나의 메서드에서 모든 블로그들을 가져오지 말고, 각 블로그별로 메서드를 분리하여 하는 것이 좋을듯.
	// 			Set에 모든 Rss 데이터를 담지 말고, 블로그 별 Set에 담아서 병렬 처리하는 것이 좋을듯.
	// 중복 체크 테스트가 필요함.
	// 새로운 글 탐지 + 초기 데이터 수집
	@Scheduled(cron = "0 0 3 * * *")
	public List<RssResponse.Channel> getNewRss() {
		Set<RssRawData> rawDataSet = new HashSet<>(rssProvider.findAllRawData());
		List<RssResponse.Channel> rssList = new ArrayList<>();

		List<RssSupportingBlog> blogList = rssProvider.findAllBlog();
		for (RssSupportingBlog url : blogList) {
			getRssRawData(url, rawDataSet);
		}
		return rssList;
	}

	private void getRssRawData(RssSupportingBlog blog, Set<RssRawData> rawDataSet) {
		try {
			RssResponse rss = apiCallWithRetry(blog.getRssFeedUrl(), RssResponse.class);
			List<RssRawData> crawledArticleList = getCrawledArticleList(Objects.requireNonNull(rss).getChannel(),
				rawDataSet, blog);
			rssProvider.saveAllRawData(crawledArticleList);
		} catch (RestClientException e) {
			handleRssException(blog, e);
		}
	}

	private void handleRssException(RssSupportingBlog blog, RestClientException e) {
		log.error("url : {}, error message : {}, error code : {}", blog.getRssFeedUrl(), e.getMessage(),
			ApiRssException.RSS_NOT_FOUND().getApiErrorCode());
	}

	private List<RssRawData> getCrawledArticleList(RssResponse.Channel rss, Set<RssRawData> links,
		RssSupportingBlog blog) {
		return rss.getItem().stream()
			.filter(item -> links.stream()
				.map(RssRawData::getUrl)
				.noneMatch(url -> url.equals(item.getLink())))
			.map(item -> getCrawledArticle(item, blog))
			.toList();
	}

	private RssRawData getCrawledArticle(RssResponse.Item item, RssSupportingBlog blog) {
		String joinedCategories = null;
		if (Objects.nonNull(item.getCategory())) {
			joinedCategories = String.join(",", item.getCategory());
		}

		return RssRawData.create(item.getTitle(), item.getLink(), item.getGuid(), item.getPubDate(),
			item.getDescription(),
			item.getCreator(), joinedCategories, blog.getId());
	}

	private <T> T apiCallWithRetry(String path, Class<T> clazz) {
		final int MAX_ATTEMPTS = 3;
		final int RETRY_INTERVAL = 200;

		RetryTemplate retryTemplate = new RetryTemplate();

		SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
		retryPolicy.setMaxAttempts(MAX_ATTEMPTS);
		retryTemplate.setRetryPolicy(retryPolicy);

		FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
		backOffPolicy.setBackOffPeriod(RETRY_INTERVAL);
		retryTemplate.setBackOffPolicy(backOffPolicy);

		return retryTemplate.execute(context -> {
			log.info("retry count : {}", context.getRetryCount());
			return restTemplate.getForObject(path, clazz);
		});
	}
}
