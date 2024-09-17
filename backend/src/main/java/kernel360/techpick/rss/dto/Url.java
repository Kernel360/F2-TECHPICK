package kernel360.techpick.rss.dto;

import lombok.Getter;

@Getter
public enum Url {

	// TODO: Blog Entity에 rssUrl로 넣어야 함.
	BAELDUNG_URL("https://www.baeldung.com/feed"),
	TOSS_URL("https://toss.tech/rss.xml"),
	WOOWA_URL("https://techblog.woowahan.com/feed/"),
	KAKAO_URL("https://tech.kakao.com/posts/feed"),
	DAANGN_URL("https://medium.com/feed/daangn"),
	LINE_URL("https://techblog.lycorp.co.jp/ko/feed/index.xml");

	private final String url;

	Url(String url) {
		this.url = url;
	}

}
