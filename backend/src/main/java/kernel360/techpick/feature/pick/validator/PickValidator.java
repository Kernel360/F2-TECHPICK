package kernel360.techpick.feature.pick.validator;

import java.util.Objects;

import org.springframework.stereotype.Component;

import kernel360.techpick.core.model.pick.Pick;
import kernel360.techpick.feature.pick.exception.ApiPickException;
import kernel360.techpick.feature.pick.model.PickProvider;
import kernel360.techpick.feature.pick.service.dto.PickCreateRequest;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PickValidator {

	private final PickProvider pickProvider;

	public void validatePickAccess(Long userId, Pick pick) {
		if (userId == null || !Objects.equals(userId, pick.getUser().getId())) {
			throw ApiPickException.PICK_UNAUTHORIZED_ACCESS();
		}
	}

	// 픽 생성 시 해당 링크가 존재하는 지 검증
	public boolean validateExistLink(Long userId, PickCreateRequest pickCreateRequest) {
		return pickProvider.existsByUserIdAndLinkUrl(userId, pickCreateRequest.linkRequest().url());
	}
}
