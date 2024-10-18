package kernel360.techpick.fixture;

import com.fasterxml.jackson.databind.ObjectMapper;

import kernel360.techpick.core.model.link.Link;
import kernel360.techpick.feature.link.service.dto.LinkRequest;
import kernel360.techpick.feature.link.service.dto.LinkResponse;
import lombok.Builder;
import lombok.Getter;

public class LinkFixture {

	private final static ObjectMapper mapper = new ObjectMapper();

	@Getter
	@Builder
	public static class TestLink {

		private @Builder.Default Long id = 32146L;
		private @Builder.Default String url = "https://www.test32146.com";
		private @Builder.Default String title = "Test Title32146";
		private @Builder.Default String description = "Test Description32146";

		public Link get() {
			return mapper.convertValue(this, Link.class);
		}

		// TODO: 테스트에 필요한 경우 정적 메소드 추가

		public static TestLink.TestLinkBuilder createLink() {
			return TestLink.builder();
		}

		public static TestLink.TestLinkBuilder createLink(Long id) {
			return TestLink.builder().id(id);
		}

		public static TestLink.TestLinkBuilder createLink(Long id, String url) {
			return TestLink.builder().id(id).url(url);
		}
	}

	@Getter
	@Builder
	public static class TestLinkRequest {

		private @Builder.Default String url = "https://www.test123499.com";
		private @Builder.Default String title = "Test Title123499";
		private @Builder.Default String description = "Test Description123499";
		private @Builder.Default String imageUrl = "https://example.com/images/og-image.jpg";

		public LinkRequest get() {
			return mapper.convertValue(this, LinkRequest.class);
		}

		// TODO: 테스트에 필요한 경우 정적 메소드 추가

		public static TestLinkRequest.TestLinkRequestBuilder createLinkRequest() {
			return TestLinkRequest.builder();
		}

		public static TestLinkRequest.TestLinkRequestBuilder createLinkRequest(String url) {
			return TestLinkRequest.builder().url(url);
		}
	}

	@Getter
	@Builder
	public static class TestLinkResponse {

		private @Builder.Default Long id = 45390L;
		private @Builder.Default String url = "https://www.test45390.com";
		private @Builder.Default String title = "Test Title45390";
		private @Builder.Default String description = "Test Description45390";
		private @Builder.Default String imageUrl = "https://example.com/images/og-image.jpg";

		public LinkResponse get() {
			return mapper.convertValue(this, LinkResponse.class);
		}

		// TODO: 테스트에 필요한 경우 정적 메소드 추가

		public static TestLinkResponse.TestLinkResponseBuilder createLinkResponse() {
			return TestLinkResponse.builder();
		}

		public static TestLinkResponse.TestLinkResponseBuilder createLinkResponse(Long id) {
			return TestLinkResponse.builder().id(id);
		}

		public static TestLinkResponse.TestLinkResponseBuilder createLinkResponse(Long id, String url) {
			return TestLinkResponse.builder().id(id).url(url);
		}
	}
}
