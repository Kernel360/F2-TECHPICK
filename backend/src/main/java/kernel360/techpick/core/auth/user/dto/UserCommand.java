package kernel360.techpick.core.auth.user.dto;

import kernel360.techpick.core.auth.SocialProviderType;

public class UserCommand {

    public record CreateSocialUser(
        SocialProviderType socialProviderType,
        String socialProviderId,
        String email
    ) {}
}
