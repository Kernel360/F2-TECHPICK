package kernel360.techpick.feature.pick.validator;

import java.util.Objects;

import org.springframework.stereotype.Component;

import kernel360.techpick.core.model.pick.Pick;
import kernel360.techpick.feature.pick.exception.ApiPickException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PickValidator {

	public void validatePickAccess(Long userId, Pick pick) {
		if (userId == null || !Objects.equals(userId, pick.getUser().getId())) {
			throw ApiPickException.PICK_UNAUTHORIZED_ACCESS();
		}
	}
}
