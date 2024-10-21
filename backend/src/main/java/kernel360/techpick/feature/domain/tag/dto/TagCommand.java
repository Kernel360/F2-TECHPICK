package kernel360.techpick.feature.domain.tag.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class TagCommand {

	public record Create(
		String name,
		Integer colorNumber) {
	}

	public record Read(
		Long tagId) {
	}

	public record Update(
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
		Long tagId
	) {
	}
}
