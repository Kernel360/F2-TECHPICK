package kernel360.techpick.feature.domain.tag.dto;

public class TagResult {

	public record Create(
		Long id,
		String name,
		String colorNumber
	) {
	}

	public record Read(
		Long id,
		String name,
		String colorNumber
	) {
	}

	public record Update(
		Long id,
		String name,
		String colorNumber
	) {
	}
}
