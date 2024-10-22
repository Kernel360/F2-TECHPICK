package kernel360.techpick.core.auth.user.service;

import kernel360.techpick.core.auth.user.dto.UserCommand;
import kernel360.techpick.core.model.user.User;

public interface UserService {

    User createNewUser(UserCommand.CreateSocialUser command);
}
