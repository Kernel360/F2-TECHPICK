package kernel360.techpick.feature.tag.service.dto;

public record TagResponse(
	Long id,
	String name,
	int order,
	Long userId
) {
}
