package kernel360.techpick.feature.api.tag.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class TagApiRequest {

	public record Create(
		@NotBlank String name,
		@NotNull Integer colorNumber) {
	}

	public record Read(
		@NotNull Long tagId) {
	}

	public record Update(
		@NotNull Long tagId,
		@NotEmpty String name,
		@NotNull Integer colorNumber) {
	}

	public record Delete(
		@NotNull Long tagId) {
	}
}
