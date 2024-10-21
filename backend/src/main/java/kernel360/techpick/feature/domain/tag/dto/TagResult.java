package kernel360.techpick.feature.domain.tag.dto;

public record TagResult(
	Long id,
	String name,
	Integer colorNumber,
	Long userId
) {
}
