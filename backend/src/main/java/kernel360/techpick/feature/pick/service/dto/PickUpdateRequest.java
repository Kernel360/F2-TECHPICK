package kernel360.techpick.feature.pick.service.dto;

import java.util.List;

public record PickUpdateRequest(
	Long id,
	String title,
	String memo,
	List<Long> tagList
) {
}
