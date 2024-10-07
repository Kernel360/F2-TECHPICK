package kernel360.techpick.feature.pick.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kernel360.techpick.core.model.folder.Folder;
import kernel360.techpick.core.model.pick.Pick;
import kernel360.techpick.feature.folder.model.FolderProvider;
import kernel360.techpick.feature.folder.validator.FolderValidator;
import kernel360.techpick.feature.pick.model.PickProvider;
import kernel360.techpick.feature.pick.service.dto.PickIdDto;
import kernel360.techpick.feature.pick.service.dto.PickMoveDto;
import kernel360.techpick.feature.pick.validator.PickValidator;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PickStructureService {

	private final PickProvider pickProvider;
	private final FolderProvider folderProvider;
	private final PickValidator pickValidator;
	private final FolderValidator folderValidator;

	// 픽 이동
	@Transactional
	public void movePick(PickMoveDto pickMoveDto) {
		// 본인 픽인지 검증 (pickId)
		Pick pick = pickProvider.findById(pickMoveDto.id());
		pickValidator.validatePickAccess(pickMoveDto.userId(), pick);

		// 이동하려는 폴더가 본인 폴더인지 검증 (parentFolderId)
		Folder parentFolder = folderProvider.findById(pickMoveDto.parentFolderId());
		folderValidator.validateFolderAccess(pickMoveDto.userId(), parentFolder);

		pick.updateParentFolder(parentFolder);
	}

	// 픽 삭제
	@Transactional
	public void deletePick(PickIdDto pickIdDto) {
		// 본인 픽인지 검증 (pickId)
		Pick pick = pickProvider.findById(pickIdDto.getUserId());
		pickValidator.validatePickAccess(pickIdDto.getUserId(), pick);

		// 휴지통인지 확인
		folderValidator.validateFolderInRecycleBin(pick.getParentFolder());
		pickProvider.deleteById(pickIdDto.getUserId());
	}
}
