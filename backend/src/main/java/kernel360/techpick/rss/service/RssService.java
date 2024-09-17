package kernel360.techpick.rss.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import kernel360.techpick.entity.article.RawCrawledArticle;
import kernel360.techpick.rss.dto.RssResponse;
import kernel360.techpick.rss.dto.Url;
import kernel360.techpick.rss.repository.RssRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RssService {

	private final RestTemplate restTemplate;
	private final RssRepository rssRepository;

	// 새로운 글 탐지 + 초기 데이터 수집
	@Scheduled(cron = "0 0 3 * * *")
	public List<RssResponse.Channel> getNewRss() {
		Set<String> links = new HashSet<>(rssRepository.findAllLinks());
		List<RssResponse.Channel> rssList = new ArrayList<>();
		for (Url url : Url.values()) {
			RssResponse rss = restTemplate.getForObject(url.getUrl(), RssResponse.class);
			rssList.add(Objects.requireNonNull(rss).getChannel());
		}

		List<RawCrawledArticle> articles = getCrawledArticleList(rssList, links);
		rssRepository.saveAll(articles);
		return rssList;
	}

	public RssResponse.Channel getRssByBaeldung() {
		RssResponse rss = restTemplate.getForObject(Url.BAELDUNG_URL.getUrl(), RssResponse.class);
		return Objects.requireNonNull(rss).getChannel();
	}

	public RssResponse.Channel getRssByToss() {
		RssResponse rss = restTemplate.getForObject(Url.TOSS_URL.getUrl(), RssResponse.class);
		return Objects.requireNonNull(rss).getChannel();
	}

	public RssResponse.Channel getRssByWoowa() {
		RssResponse rss = restTemplate.getForObject(Url.WOOWA_URL.getUrl(), RssResponse.class);
		return Objects.requireNonNull(rss).getChannel();
	}

	public RssResponse.Channel getRssByKakao() {
		RssResponse rss = restTemplate.getForObject(Url.KAKAO_URL.getUrl(), RssResponse.class);
		return Objects.requireNonNull(rss).getChannel();
	}

	public RssResponse.Channel getRssByDaangn() {
		RssResponse rss = restTemplate.getForObject(Url.DAANGN_URL.getUrl(), RssResponse.class);
		return Objects.requireNonNull(rss).getChannel();
	}

	public RssResponse.Channel getRssByLine() {
		RssResponse rss = restTemplate.getForObject(Url.LINE_URL.getUrl(), RssResponse.class);
		return Objects.requireNonNull(rss).getChannel();
	}

	private List<RawCrawledArticle> getCrawledArticleList(List<RssResponse.Channel> rssList, Set<String> links) {
		return rssList.stream()
			.flatMap(channel -> channel.getItem().stream())
			.filter(item -> !links.contains(item.getLink()))
			.map(this::getCrawledArticle)
			.toList();
	}

	private RawCrawledArticle getCrawledArticle(RssResponse.Item item) {
		String joinedCategories = null;
		if (Objects.nonNull(item.getCategory())) {
			joinedCategories = String.join(",", item.getCategory());
		}

		return new RawCrawledArticle(item.getTitle(), item.getLink(),
			item.getPubDate(),
			item.getCreator(), joinedCategories);
	}

}
