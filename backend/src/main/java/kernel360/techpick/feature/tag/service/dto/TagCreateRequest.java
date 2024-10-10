package kernel360.techpick.feature.tag.service.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record TagCreateRequest(
	@NotEmpty String tagName,
	@NotNull Integer colorNumber
) {}
