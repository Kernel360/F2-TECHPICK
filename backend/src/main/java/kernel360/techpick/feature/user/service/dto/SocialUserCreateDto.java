package kernel360.techpick.feature.user.service.dto;

import kernel360.techpick.core.model.user.SocialType;

public record SocialUserCreateDto(
    SocialType socialType,
    String socialProviderId,
    String email
) {}
