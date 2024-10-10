package kernel360.techpick.feature.tag.service.dto;

public record TagUpdateRequest(
	Long tagId,
	String tagName,
	int tagOrder
) {
}
