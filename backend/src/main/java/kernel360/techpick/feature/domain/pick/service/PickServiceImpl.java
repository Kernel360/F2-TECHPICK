package kernel360.techpick.feature.domain.pick.service;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kernel360.techpick.core.model.folder.Folder;
import kernel360.techpick.core.model.pick.Pick;
import kernel360.techpick.feature.domain.pick.dto.PickCommand;
import kernel360.techpick.feature.domain.pick.dto.PickMapper;
import kernel360.techpick.feature.domain.pick.dto.PickResult;
import kernel360.techpick.feature.infrastructure.folder.FolderAdapter;
import kernel360.techpick.feature.infrastructure.link.writer.LinkWriter;
import kernel360.techpick.feature.infrastructure.pick.PickAdaptor;
import kernel360.techpick.feature.infrastructure.user.reader.UserReader;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PickServiceImpl implements PickService {
	private final UserReader userReader; // TODO: change to UserAdaptor
	private final LinkWriter linkWriter;
	private final PickAdaptor pickAdaptor;
	private final PickMapper pickMapper;
	private final FolderAdapter folderAdapter;

	@Override
	@Transactional(readOnly = true)
	public PickResult getPick(PickCommand.Read command) {
		var user = userReader.readUser(command.userId());
		var pick = pickAdaptor.getPick(user, command.pickId());
		return pickMapper.toReadResult(pick);
	}

	@Override
	@Transactional
	public PickResult saveNewPick(PickCommand.Create command) {
		var user = userReader.readUser(command.userId());
		var folder = folderAdapter.readFolder(user, command.parentFolderId());
		var link = linkWriter.writeLink(command.linkInfo());
		var pick = pickAdaptor.savePick(pickMapper.toEntity(command, user, folder, link));
		return pickMapper.toCreateResult(pick);
	}

	@Override
	@Transactional
	public PickResult updatePick(PickCommand.Update command) {
		var user = userReader.readUser(command.userId());
		var pick = pickAdaptor.getPick(user, command.pickId())
							  .updateMemo(command.memo())
							  .updateTagOrder(command.tagIdList())
							  .updateTitle(command.title());
		// TODO: detach tag from pick (PickTag relation)
		return pickMapper.toUpdateResult(pick);
	}

	@Override
	@Transactional
	public PickResult movePick(PickCommand.Move command) {
		var user = userReader.readUser(command.userId());
		var pick = pickAdaptor.getPick(user, command.pickId());
		var destinationFolder = folderAdapter.readFolder(user, command.parentFolderId());

		if (isParentFolderChanged(pick.getParentFolder(), destinationFolder)) {
			movePickToOtherFolder(pick, destinationFolder, command.orderIdx());
			return pickMapper.toMoveResult(pick);
		}
		pick.getParentFolder().updateChildPickOrder(command.pickId(), command.orderIdx());
		return pickMapper.toMoveResult(pick);
	}

	@Override
	@Transactional
	public void deletePick(PickCommand.Delete command) {
		var user = userReader.readUser(command.userId());
		var pick = pickAdaptor.getPick(user, command.pickId());
		pick.getParentFolder().removeChildPickOrder(command.pickId());
		pickAdaptor.deletePick(pick);
	}

	// TODO: implement equals and hashcode of Folder Entity
	private boolean isParentFolderChanged(Folder originalFolder, Folder destinationFolder) {
		return ObjectUtils.notEqual(originalFolder, destinationFolder);
	}

	private void movePickToOtherFolder(Pick pick, Folder destinationFolder, int orderIndex) {
		pick.getParentFolder().removeChildPickOrder(pick.getId());
		destinationFolder.updateChildPickOrder(pick.getId(), orderIndex);
		pick.updateParentFolder(destinationFolder);
	}
}
