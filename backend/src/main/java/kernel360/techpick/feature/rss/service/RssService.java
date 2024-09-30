package kernel360.techpick.feature.rss.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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
		blogList.forEach(url -> {
			try {
				RssResponse rss = restTemplate.getForObject(url.getRssFeedUrl(), RssResponse.class);
				List<RssRawData> crawledArticleList = getCrawledArticleList(Objects.requireNonNull(rss).getChannel(),
					rawDataSet);
				rssProvider.saveAllRawData(crawledArticleList);
			} catch (RestClientException e) {
				throw ApiRssException.RSS_NOT_FOUND();
			}

		});
		return rssList;
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
}
