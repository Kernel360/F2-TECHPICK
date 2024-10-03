package kernel360.techpick.fixture;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import kernel360.techpick.core.model.tag.Tag;
import kernel360.techpick.core.model.user.User;
import kernel360.techpick.feature.tag.service.dto.TagCreateRequest;
import kernel360.techpick.feature.tag.service.dto.TagUpdateRequest;
import lombok.Builder;
import lombok.Getter;

public class TagFixture {

	private static final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

	@Getter
	@Builder
	public static class TestTag {

		private @Builder.Default Long id = 23324L;
		private @Builder.Default String name = "테스트Tag324";
		private @Builder.Default int tagOrder = 23324;
		private @Builder.Default User user = UserFixture.TestUser.createUser().build().get();
		private @Builder.Default LocalDateTime createdAt = LocalDateTime.now();
		private @Builder.Default LocalDateTime updatedAt = LocalDateTime.now();

		public Tag get() {
			return mapper.convertValue(this, Tag.class);
		}

		// TODO: 테스트에 필요한 경우 정적 메소드 추가

		public static TestTag.TestTagBuilder createTag() {
			return TestTag.builder();
		}

		public static TestTag.TestTagBuilder createTag(Long id) {
			return TestTag.builder().id(id);
		}

		public static TestTag.TestTagBuilder createTag(Long id, int tagOrder) {
			return TestTag.builder()
				.id(id)
				.name("테스트유저" + id + "의" + tagOrder + "번째태그")
				.tagOrder(tagOrder);
		}

		public static TestTag.TestTagBuilder createTag(Long id, String name, int tagOrder) {
			return TestTag.builder().id(id).name(name).tagOrder(tagOrder);
		}
	}

	@Getter
	@Builder
	public static class TestTagCreateRequest {

		private @Builder.Default String name = "testTagCreateRequestName";

		public TagCreateRequest get() {
			return mapper.convertValue(this, TagCreateRequest.class);
		}

		// TODO: 테스트에 필요한 경우 정적 메소드 추가

		public static TestTagCreateRequest.TestTagCreateRequestBuilder createTag() {
			return TestTagCreateRequest.builder();
		}

		public static TestTagCreateRequest.TestTagCreateRequestBuilder createTag(String name) {
			return TestTagCreateRequest.builder().name(name);
		}
	}

	@Getter
	@Builder
	public static class TestTagUpdateRequest {

		private @Builder.Default Long id = 23324L;
		private @Builder.Default String name = "testTagUpdateRequestName";
		private @Builder.Default int order = 23324;

		public TagUpdateRequest get() {
			return mapper.convertValue(this, TagUpdateRequest.class);
		}

		// TODO: 테스트에 필요한 경우 정적 메소드 추가

		public static TestTagUpdateRequest.TestTagUpdateRequestBuilder createTag() {
			return TestTagUpdateRequest.builder();
		}

		public static TestTagUpdateRequest.TestTagUpdateRequestBuilder createTag(Long id) {
			return TestTagUpdateRequest.builder().id(id);
		}

		public static TestTagUpdateRequest.TestTagUpdateRequestBuilder createTag(Long id, String name) {
			return TestTagUpdateRequest.builder().id(id).name(name);
		}

		public static TestTagUpdateRequest.TestTagUpdateRequestBuilder createTag(Long id, int order) {
			return TestTagUpdateRequest.builder()
				.id(id)
				.name("테스트유저" + id + "의" + order + "번째태그")
				.order(order);
		}
	}
}
