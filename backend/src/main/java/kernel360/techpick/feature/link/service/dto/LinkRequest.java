package kernel360.techpick.feature.link.service.dto;

public record LinkRequest(
    String url,
    String title,
    String description,
    String imageUrl
) {}
