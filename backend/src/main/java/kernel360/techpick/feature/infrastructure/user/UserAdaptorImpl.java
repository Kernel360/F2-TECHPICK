package kernel360.techpick.feature.infrastructure.user;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import kernel360.techpick.core.model.user.User;
import kernel360.techpick.core.model.user.UserRepository;
import kernel360.techpick.feature.domain.user.exception.ApiUserException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserAdaptorImpl implements UserAdaptor {
	private final UserRepository userRepository;

	@Override
	public User getUser(Long userId) {
		return userRepository.findById(userId).orElseThrow(ApiUserException::USER_NOT_FOUND);
	}
}
