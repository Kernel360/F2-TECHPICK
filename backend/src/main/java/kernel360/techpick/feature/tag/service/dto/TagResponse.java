package kernel360.techpick.feature.tag.service.dto;

public record TagResponse(
	Long tagId,
	String tagName,
	int tagOrder,
	Long userId
) {}
