package kernel360.techpick.feature.user.model;

import org.springframework.stereotype.Component;

import kernel360.techpick.core.model.user.User;
import kernel360.techpick.feature.user.UserRepository;
import kernel360.techpick.feature.user.exception.ApiUserException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserProvider {

	private final UserRepository userRepository;

	public User findById(Long userId) {
		return userRepository.findById(userId).orElseThrow(ApiUserException::USER_NOT_FOUND);
	}
}
