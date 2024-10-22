package kernel360.techpick.core.auth.user.service;

import org.springframework.stereotype.Service;

import kernel360.techpick.core.auth.user.dto.UserCommand;
import kernel360.techpick.core.model.user.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Override
    public User createNewUser(UserCommand.CreateSocialUser command) {
        return null;
    }
}
