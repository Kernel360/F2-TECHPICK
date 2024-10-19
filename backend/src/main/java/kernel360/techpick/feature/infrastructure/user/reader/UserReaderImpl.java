package kernel360.techpick.feature.infrastructure.user.reader;

import org.springframework.stereotype.Component;

import kernel360.techpick.core.model.user.User;
import kernel360.techpick.core.model.user.UserRepository;
import kernel360.techpick.feature.domain.user.exception.ApiUserException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserReaderImpl implements UserReader {
    UserRepository userRepository;

    @Override
    public User read(Long userId) {
        return userRepository.findById(userId).orElseThrow(ApiUserException::USER_NOT_FOUND);
    }
}
