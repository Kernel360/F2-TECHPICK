package kernel360.techpick.feature.link.service.dto;

public record LinkResponse(
    Long id,
    String url,
    String title,
    String description,
    String imageUrl
) {}
