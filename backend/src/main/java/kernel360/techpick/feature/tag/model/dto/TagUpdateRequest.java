package kernel360.techpick.feature.tag.model.dto;

public record TagUpdateRequest(
	Long id,
	String name,
	int order
) {
}
