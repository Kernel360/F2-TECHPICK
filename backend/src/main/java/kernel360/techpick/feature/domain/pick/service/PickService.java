package kernel360.techpick.feature.domain.pick.service;

import kernel360.techpick.feature.domain.pick.dto.PickCommand;
import kernel360.techpick.feature.domain.pick.dto.PickResult;

public interface PickService {

    PickResult getPick(PickCommand.Read command);

    PickResult saveNewPick(PickCommand.Create command);

    PickResult updatePick(PickCommand.Update command);

    PickResult movePick(PickCommand.Move command);

    void deletePick(PickCommand.Delete command);
}
