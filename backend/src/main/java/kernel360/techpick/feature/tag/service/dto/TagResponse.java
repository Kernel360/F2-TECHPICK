package kernel360.techpick.feature.tag.service.dto;

public record TagResponse(
	Long tagId,
	String tagName,
	Integer tagOrder,
	Integer colorNumber,
	Long userId
) {}
