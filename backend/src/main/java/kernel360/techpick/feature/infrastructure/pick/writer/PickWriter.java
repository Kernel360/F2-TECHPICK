package kernel360.techpick.feature.infrastructure.pick.writer;

import kernel360.techpick.core.model.pick.Pick;
import kernel360.techpick.feature.domain.pick.exception.ApiPickException;

public interface PickWriter {

    Pick write(Pick pick) throws ApiPickException;

    void remove(Pick pick);
}
