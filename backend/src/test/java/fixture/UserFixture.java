package fixture;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import kernel360.techpick.core.model.user.Role;
import kernel360.techpick.core.model.user.SocialType;
import kernel360.techpick.core.model.user.User;
import lombok.Builder;
import lombok.Getter;

public class UserFixture {

    private static final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Getter
    @Builder
    public static class TestUser {

        private @Builder.Default Long id = 25349L;
        private @Builder.Default String nickname = "testNickName";
        private @Builder.Default String email = "test@test.com";
        private @Builder.Default Role role = Role.ROLE_USER;
        private @Builder.Default String password = "testPassword";
        private @Builder.Default SocialType socialProvider = SocialType.GOOGLE;
        private @Builder.Default String socialProviderId = "testSocialProviderId";
        private @Builder.Default LocalDateTime deletedAt = null;
        private @Builder.Default String tel = "010-1234-5678";
        private @Builder.Default LocalDateTime createdAt = LocalDateTime.now();
        private @Builder.Default LocalDateTime updatedAt = LocalDateTime.now();

        public User get() {
            return mapper.convertValue(this, User.class);
        }

        // TODO: 테스트에 필요한 경우 정적 메소드 추가

        public static TestUser.TestUserBuilder createUser() {
            return TestUser.builder();
        }

        public static TestUser.TestUserBuilder createUser(Long id) {
            return TestUser.builder().id(id);
        }

        public static TestUser.TestUserBuilder createUser(Long id, LocalDateTime deletedAt) {
            return TestUser.builder().id(id).deletedAt(deletedAt);
        }
    }
}