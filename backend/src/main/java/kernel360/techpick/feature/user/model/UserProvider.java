package kernel360.techpick.feature.user.model;

import org.springframework.stereotype.Component;

import kernel360.techpick.core.model.user.User;
import kernel360.techpick.feature.user.exception.ApiUserException;
import kernel360.techpick.feature.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserProvider {

    private final UserRepository userRepository;

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(ApiUserException::USER_NOT_FOUND);
    }

    public boolean existsBySocialProviderId(String socialProviderId) {
        return userRepository.existsBySocialProviderId(socialProviderId);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
