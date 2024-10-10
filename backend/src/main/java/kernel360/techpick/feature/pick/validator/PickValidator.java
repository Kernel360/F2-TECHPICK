package kernel360.techpick.feature.pick.validator;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import kernel360.techpick.core.model.pick.Pick;
import kernel360.techpick.core.model.user.User;
import kernel360.techpick.feature.pick.exception.ApiPickException;

@Component
public class PickValidator {

	public void validatePickAccess(User user, Pick pick) {
		if (ObjectUtils.notEqual(user, pick.getUser())) {
			throw ApiPickException.PICK_UNAUTHORIZED_ACCESS();
		}
	}
}
