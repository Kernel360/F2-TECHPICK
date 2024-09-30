package kernel360.techpick.fixture;

import com.fasterxml.jackson.databind.ObjectMapper;

import kernel360.techpick.core.model.user.JobGroup;
import lombok.Builder;
import lombok.Getter;

public class JobGroupFixture {

	@Getter
	@Builder
	public static class TestJobGroup {

		private static final ObjectMapper mapper = new ObjectMapper();

		private @Builder.Default Long id = 5639L;
		private @Builder.Default String name = "testJobGroup";

		public JobGroup get() {
			return mapper.convertValue(this, JobGroup.class);
		}

		// TODO: 테스트에 필요한 경우 정적 메소드 추가

		public static TestJobGroup.TestJobGroupBuilder createJobGroup() {
			return TestJobGroup.builder();
		}

		public static TestJobGroup.TestJobGroupBuilder createJobGroup(Long id) {
			return TestJobGroup.builder().id(id);
		}

		public static TestJobGroup.TestJobGroupBuilder createJobGroup(Long id, String name) {
			return TestJobGroup.builder().id(id).name(name);
		}
	}
}
