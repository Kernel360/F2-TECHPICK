package kernel360.techpick.feature.user.model;

import kernel360.techpick.core.model.user.User;
import kernel360.techpick.feature.user.service.dto.SocialUserCreateDto;

public class UserMapper {
    public static User toUserEntity(SocialUserCreateDto dto, String nickname) {
        return User.basicSocialUser(
            dto.socialType(),
            dto.socialProviderId(),
            nickname,
            dto.email()
        );
    }
}
