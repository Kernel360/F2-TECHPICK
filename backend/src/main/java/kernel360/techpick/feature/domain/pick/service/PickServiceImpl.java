package kernel360.techpick.feature.domain.pick.service;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kernel360.techpick.feature.domain.pick.dto.PickCommand;
import kernel360.techpick.feature.domain.pick.dto.PickMapper;
import kernel360.techpick.feature.domain.pick.dto.PickResult;
import kernel360.techpick.feature.infrastructure.folder.reader.FolderReader;
import kernel360.techpick.feature.infrastructure.link.writer.LinkWriter;
import kernel360.techpick.feature.infrastructure.pick.reader.PickReader;
import kernel360.techpick.feature.infrastructure.pick.writer.PickWriter;
import kernel360.techpick.feature.infrastructure.user.reader.UserReader;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PickServiceImpl implements PickService {
    private final UserReader userReader;
    private final PickReader pickReader;
    private final PickWriter pickWriter;
    private final PickMapper pickMapper;
    private final LinkWriter linkWriter;
    private final FolderReader folderReader;

    @Override
    @Transactional(readOnly = true)
    public PickResult.Read getPick(PickCommand.Read command) {
        var user = userReader.read(command.userId());
        var pick = pickReader.read(user, command.pickId());
        return pickMapper.toReadResult(pick);
    }

    @Override
    @Transactional
    public PickResult.Create saveNewPick(PickCommand.Create command) {
        var user = userReader.read(command.userId());
        var folder = folderReader.read(user, command.parentFolderId());
        var link = linkWriter.write(command.linkInfo());
        var pick = pickWriter.write(pickMapper.toEntity(command, user, folder, link));
        return pickMapper.toCreateResult(pick);
    }

    @Override
    @Transactional
    public PickResult.Update updatePick(PickCommand.Update command) {
        var user = userReader.read(command.userId());
        var pick = pickReader.read(user, command.pickId())
                             .updateMemo(command.memo())
                             .updateTagOrder(command.tagIdList())
                             .updateTitle(command.title());
        return pickMapper.toUpdateResult(pick);
    }

    @Override
    @Transactional
    public PickResult.Move movePick(PickCommand.Move command) {
        var user = userReader.read(command.userId());
        var pick = pickReader.read(user, command.pickId());
        var originFolder = pick.getParentFolder();
        // move without changing folder
        if (originFolder.getId().equals(command.parentFolderId())) {
            originFolder.updateChildPickOrder(command.pickId(), command.orderIdx());
            return pickMapper.toMoveResult(pick);
        }
        // move to another folder
        originFolder.removeChildPickOrder(command.pickId());
        var destFolder = folderReader.read(user, command.parentFolderId())
                                     .updateChildPickOrder(command.pickId(), command.orderIdx());
        pick.updateParentFolder(destFolder);
        return pickMapper.toMoveResult(pick);
    }

    @Override
    public void deletePick(PickCommand.Delete command) {
        var user = userReader.read(command.userId());
        var pick = pickReader.read(user, command.pickId());
        var folder = pick.getParentFolder();
        folder.removeChildPickOrder(command.pickId());
        pickWriter.remove(pick);
    }
}
