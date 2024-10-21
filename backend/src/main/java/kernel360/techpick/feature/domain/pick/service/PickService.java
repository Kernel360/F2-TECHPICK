package kernel360.techpick.feature.domain.pick.service;

import kernel360.techpick.feature.domain.pick.dto.PickCommand;
import kernel360.techpick.feature.domain.pick.dto.PickResult;

public interface PickService {

    PickResult.Read getPick(PickCommand.Read command);

    PickResult.Create saveNewPick(PickCommand.Create command);

    PickResult.Update updatePick(PickCommand.Update command);

    PickResult.Move movePick(PickCommand.Move command);

    void deletePick(PickCommand.Delete command);
}
