package kernel360.techpick.feature.domain.link.dto;

import java.time.LocalDateTime;

public record LinkInfo(
    String url,
    String title,
    String description,
    String imageUrl,
    LocalDateTime invalidatedAt
) {}
