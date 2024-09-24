package kernel360.techpick.feature.rss.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import kernel360.techpick.core.model.rss.RssRawData;
import kernel360.techpick.feature.rss.repository.RssBlogRepository;
import kernel360.techpick.feature.rss.repository.RssRepository;
import kernel360.techpick.feature.rss.model.dto.RssResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RssService {

	private final RestTemplate restTemplate;
	private final RssRepository rssRepository;
	private final RssBlogRepository rssBlogRepository;

	// 새로운 글 탐지 + 초기 데이터 수집
	@Scheduled(cron = "0 0 3 * * *")
	public List<RssResponse.Channel> getNewRss() {
		Set<String> links = new HashSet<>(rssRepository.findAllUrls());
		List<RssResponse.Channel> rssList = new ArrayList<>();

		List<String> urls = rssBlogRepository.findAllUrls();
		urls.forEach(url -> {
			RssResponse rss = restTemplate.getForObject(url, RssResponse.class);
			rssList.add(Objects.requireNonNull(rss).getChannel());
		});

		List<RssRawData> articles = getCrawledArticleList(rssList, links);
		rssRepository.saveAll(articles);
		return rssList;
	}

	private List<RssRawData> getCrawledArticleList(List<RssResponse.Channel> rssList, Set<String> links) {
		return rssList.stream()
			.flatMap(channel -> channel.getItem().stream())
			.filter(item -> !links.contains(item.getLink()))
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
