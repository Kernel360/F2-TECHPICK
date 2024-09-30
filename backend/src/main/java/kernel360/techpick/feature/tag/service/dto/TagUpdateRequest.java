package kernel360.techpick.feature.tag.service.dto;

public record TagUpdateRequest(
	Long id,
	String name,
	int tagOrder
) {
}
