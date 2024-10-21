package kernel360.techpick.feature.infrastructure.pick.reader;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import kernel360.techpick.core.model.pick.Pick;
import kernel360.techpick.core.model.pick.PickRepository;
import kernel360.techpick.core.model.user.User;
import kernel360.techpick.feature.domain.pick.exception.ApiPickException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PickReaderImpl implements PickReader {
    private final PickRepository pickRepository;

    @Override
    public Pick readPick(User user, Long pickId) {
        Pick pick = pickRepository.findById(pickId).orElseThrow(ApiPickException::PICK_NOT_FOUND);
        if (ObjectUtils.notEqual(user, pick.getUser())) {
            throw ApiPickException.PICK_UNAUTHORIZED_ACCESS();
        }
        return pick;
    }
}
