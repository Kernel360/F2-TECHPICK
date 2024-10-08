package kernel360.techpick.feature.folder.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kernel360.techpick.core.model.folder.Folder;
import kernel360.techpick.feature.folder.exception.ApiFolderException;
import kernel360.techpick.feature.folder.model.FolderProvider;
import kernel360.techpick.feature.folder.service.dto.FolderDeleteDto;
import kernel360.techpick.feature.folder.service.dto.FolderIdDto;
import kernel360.techpick.feature.folder.service.dto.FolderMoveDto;
import kernel360.techpick.feature.folder.validator.FolderValidator;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FolderStructureService {

	private final FolderProvider folderProvider;
	private final FolderValidator folderValidator;

	@Transactional(readOnly = true)
	public Folder getFolderById(FolderIdDto idDto) throws ApiFolderException {

		// 조회하려는 폴더가 본인 폴더인지 검증
		Folder folder = folderProvider.findById(idDto.getFolderId());
		folderValidator.validateFolderAccess(idDto.getUserId(), folder);

		return folder;
	}

	@Transactional(readOnly = true)
	public List<Folder> getFolderListByUserId(Long userId) {

		return folderProvider.findAllByUserIdAndParentFolderIsNotEmpty(userId);
	}

	@Transactional
	public void moveFolder(FolderMoveDto moveDto) throws ApiFolderException {

		// 변경하려는 폴더가 본인 폴더인지 검증
		Folder targetFolder = folderProvider.findById(moveDto.id());
		folderValidator.validateFolderAccess(moveDto.userId(), targetFolder);

		// 변경하려는 폴더가 기본폴더인지 검증
		folderValidator.validateChangeBasicFolder(targetFolder);

		// 변경하려는 폴더의 부모가 본인 폴더인지 검증
		Folder parentFolder = folderProvider.findById(moveDto.parentFolderId());
		folderValidator.validateFolderAccess(moveDto.userId(), parentFolder);

		// 변경하려는 폴더의 부모가 미분류 폴더가 아닌지 검증
		folderValidator.validateFolderNotUnclassified(moveDto.userId(), parentFolder);

		targetFolder.updateParentFolder(parentFolder);
		folderProvider.save(targetFolder);
	}

	@Transactional
	public void deleteFolder(FolderDeleteDto deleteDto) throws ApiFolderException {

		// 삭제하려는 폴더가 본인 폴더인지 검증
		Folder targetFolder = folderProvider.findById(deleteDto.folderId());
		folderValidator.validateFolderAccess(deleteDto.userId(), targetFolder);

		// 삭제하려는 폴더가 휴지통 안에 있는지 검증
		folderValidator.validateFolderInRecycleBin(targetFolder);

		// @OnDelete(action = OnDeleteAction.CASCADE) 에 의해 해당 폴더를 부모로 가지는 모든 폴더/픽이 삭제됨
		folderProvider.deleteById(deleteDto.folderId());
	}
}
