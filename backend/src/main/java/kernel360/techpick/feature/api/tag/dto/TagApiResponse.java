package kernel360.techpick.feature.api.tag.dto;

public class TagApiResponse {

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
