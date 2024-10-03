package kernel360.techpick.feature.pick.service.dto;

import kernel360.techpick.feature.tag.service.dto.TagResponse;

public record PickTagUpdateRequest(
	Long id,
	Long pickId,
	TagResponse tagResponse // List<TagResponse> or TagResponse
) {
}
