package kernel360.techpick.feature.pick.validator;

import java.util.Objects;

import org.springframework.stereotype.Component;

import kernel360.techpick.core.model.pick.Pick;
import kernel360.techpick.core.model.user.User;
import kernel360.techpick.feature.pick.exception.ApiPickException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PickValidator {

	public void validatePickAccess(User user, Pick pick) {
		if (Objects.equals(user, pick.getUser())) {
			throw ApiPickException.PICK_UNAUTHORIZED_ACCESS();
		}
	}
}
