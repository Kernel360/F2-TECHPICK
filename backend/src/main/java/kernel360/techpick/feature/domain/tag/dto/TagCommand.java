package kernel360.techpick.feature.domain.tag.dto;

public class TagCommand {

	public record Create(
		Long userId,
		String name,
		Integer colorNumber) {
	}

	public record Read(
		Long userId,
		Long tagId) {
	}

	public record Update(
		Long userId,
		Long tagId,
		String name,
		Integer colorNumber) {
	}

	public record Move(
		Long tagId,
		int orderIdx
	) {
	}

	public record Delete(
		Long userId,
		Long tagId
	) {
	}
}
