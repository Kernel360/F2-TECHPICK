package kernel360.techpick.feature.user.model;

import org.springframework.stereotype.Component;

import kernel360.techpick.core.model.user.User;
import kernel360.techpick.feature.user.service.dto.SocialUserCreateDto;

@Component
public class UserMapper {
    public User toUserEntity(SocialUserCreateDto dto, String nickname) {
        return User.basicSocialUser(
            dto.socialType(),
            dto.socialProviderId(),
            nickname,
            dto.email()
        );
    }
}
