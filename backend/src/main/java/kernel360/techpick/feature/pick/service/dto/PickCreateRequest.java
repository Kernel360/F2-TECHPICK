package kernel360.techpick.feature.pick.service.dto;

import java.util.List;

import kernel360.techpick.feature.link.service.dto.LinkRequest;

public record PickCreateRequest(
	String memo,
	String title,
	List<Long> tagList,
	LinkRequest linkRequest
) {
}
