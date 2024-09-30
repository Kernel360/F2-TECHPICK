package kernel360.techpick.feature.tag.model.dto;

public record TagResponse(
	Long id,
	String name,
	int tagOrder,
	Long userId
) {
}
