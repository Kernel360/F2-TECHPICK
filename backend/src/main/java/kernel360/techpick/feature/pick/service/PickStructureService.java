package kernel360.techpick.feature.pick.service;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kernel360.techpick.core.model.folder.Folder;
import kernel360.techpick.core.model.pick.Pick;
import kernel360.techpick.core.model.user.User;
import kernel360.techpick.feature.folder.model.FolderProvider;
import kernel360.techpick.feature.folder.validator.FolderValidator;
import kernel360.techpick.feature.pick.model.PickProvider;
import kernel360.techpick.feature.pick.service.dto.PickDeleteDto;
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
	private final PickService pickService;

	// 픽 이동
	@Transactional
	public void movePick(User user, PickMoveDto pickMoveDto) {
		// 본인 픽인지 검증 (pickId)
		Pick pick = pickProvider.findById(pickMoveDto.id());
		pickValidator.validatePickAccess(user, pick);

		// 이동하려는 폴더가 본인 폴더인지 검증 (parentFolderId)
		Folder parentFolder = folderProvider.findById(pickMoveDto.parentFolderId());
		folderValidator.validateFolderAccess(user, parentFolder);

		pick.updateParentFolder(parentFolder);
		pickProvider.save(pick);
	}

	// 픽 삭제
	@Transactional
	public void deletePick(User user, PickDeleteDto pickDeleteDto) {
		// 본인 픽인지 검증 (pickId)
		Pick pick = pickProvider.findById(pickDeleteDto.id());
		pickValidator.validatePickAccess(user, pick);

		// 휴지통인지 확인
		folderValidator.validateFolderInRecycleBin(pick.getParentFolder());
		pickProvider.deleteById(pickDeleteDto.id());
	}

	public List<Pick> getPickListOfRootAndRecycleBinByUser(User user) {
		Folder recycleBin = folderProvider.findRecycleBin(user);
		Folder root = folderProvider.findRoot(user);

		List<Pick> recycleBinPicks = pickProvider.findAllByParentFolderId(user, recycleBin.getId());
		List<Pick> rootPicks = pickProvider.findAllByParentFolderId(user, root.getId());
		return Stream.concat(recycleBinPicks.stream(), rootPicks.stream()).toList();
	}

	public List<Pick> getPickListByUser(User user) {
		return pickProvider.findAllByUser(user);
	}
}
