package kernel360.techpick.feature.tag.service.dto;

import jakarta.validation.constraints.NotNull;

// TODO: validation annotation 추가 필요. 현재는 colorNumber 만 설정함
public record TagUpdateRequest(
	Long tagId,
	String tagName,
	Integer tagOrder,
	@NotNull Integer colorNumber
) { }
