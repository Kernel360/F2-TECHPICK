package kernel360.techpick.feature.domain.pick.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kernel360.techpick.core.model.folder.Folder;
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
	private final FolderReader folderReader;
	private final LinkWriter linkWriter;
	private final PickReader pickReader;
	private final PickWriter pickWriter;
	private final PickMapper pickMapper;

	@Override
	@Transactional(readOnly = true)
	public PickResult getPick(PickCommand.Read command) {
		var user = userReader.readCurrentUser();
		var pick = pickReader.readPick(user, command.pickId());
		return pickMapper.toReadResult(pick);
	}

	@Override
	@Transactional
	public PickResult saveNewPick(PickCommand.Create command) {
		var user = userReader.readCurrentUser();
		var folder = folderReader.readFolder(user, command.parentFolderId());
		var link = linkWriter.writeLink(command.linkInfo());
		var pick = pickWriter.writePick(pickMapper.toEntity(command, user, folder, link));
		return pickMapper.toCreateResult(pick);
	}

	@Override
	@Transactional
	public PickResult updatePick(PickCommand.Update command) {
		var user = userReader.readCurrentUser();
		var pick = pickReader.readPick(user, command.pickId())
			.updateMemo(command.memo())
			.updateTagOrder(command.tagIdList())
			.updateTitle(command.title());
		return pickMapper.toUpdateResult(pick);
	}

	@Override
	@Transactional
	public PickResult movePick(PickCommand.Move command) {
		var user = userReader.readCurrentUser();
		var pick = pickReader.readPick(user, command.pickId());
		var originalParentFolder = pick.getParentFolder();

		if (isParentFolderNotChanged(command, originalParentFolder)) {
			originalParentFolder.updateChildPickOrder(command.pickId(), command.orderIdx());
			return pickMapper.toMoveResult(pick);
		}
		// if moving to another folder
		originalParentFolder.removeChildPickOrder(command.pickId());
		var newParentFolder = folderReader.readFolder(user, command.parentFolderId());
		newParentFolder.updateChildPickOrder(command.pickId(), command.orderIdx());
		pick.updateParentFolder(newParentFolder);
		return pickMapper.toMoveResult(pick);
	}

	@Override
	@Transactional
	public void deletePick(PickCommand.Delete command) {
		var user = userReader.readCurrentUser();
		var pick = pickReader.readPick(user, command.pickId());
		var folder = pick.getParentFolder();
		folder.removeChildPickOrder(command.pickId());
		pickWriter.removePick(pick);
	}

	private boolean isParentFolderNotChanged(PickCommand.Move command, Folder originalFolder) {
		return (command.parentFolderId() == null || originalFolder.getId().equals(command.parentFolderId()));
	}
}
