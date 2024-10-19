package kernel360.techpick.feature.infrastructure.pick.reader;

import kernel360.techpick.core.model.pick.Pick;
import kernel360.techpick.core.model.user.User;

public interface PickReader {

    Pick read(User user, Long pickId);
}
