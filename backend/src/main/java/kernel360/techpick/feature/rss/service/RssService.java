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

import kernel360.techpick.core.exception.feature.rss.ApiRssException;
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
				rawDataSet);
			rssProvider.saveAllRawData(crawledArticleList);
		} catch (RestClientException e) {
			handleRssException(blog, e);
		}
	}

	private void handleRssException(RssSupportingBlog blog, RestClientException e) {
		log.error("url : {}, error message : {}, error code : {}", blog.getRssFeedUrl(), e.getMessage(),
			ApiRssException.RSS_NOT_FOUND().getApiErrorCode());
	}

	private List<RssRawData> getCrawledArticleList(RssResponse.Channel rss, Set<RssRawData> links) {
		return rss.getItem().stream()
			.filter(item -> links.stream()
				.map(RssRawData::getUrl)
				.noneMatch(url -> url.equals(item.getLink())))
			.map(this::getCrawledArticle)
			.toList();
	}

	private RssRawData getCrawledArticle(RssResponse.Item item) {
		String joinedCategories = null;
		if (Objects.nonNull(item.getCategory())) {
			joinedCategories = String.join(",", item.getCategory());
		}

		return RssRawData.create(item.getTitle(), item.getLink(), item.getGuid(), item.getPubDate(),
			item.getDescription(),
			item.getCreator(), joinedCategories);
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
