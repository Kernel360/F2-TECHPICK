package kernel360.techpick.feature.folder.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kernel360.techpick.core.model.folder.Folder;
import kernel360.techpick.core.model.folder.FolderType;
import kernel360.techpick.feature.folder.exception.ApiFolderException;
import kernel360.techpick.feature.folder.model.FolderMapper;
import kernel360.techpick.feature.folder.model.FolderProvider;
import kernel360.techpick.feature.folder.service.dto.FolderIdDto;
import kernel360.techpick.feature.folder.service.dto.FolderResponse;
import kernel360.techpick.feature.folder.service.dto.FolderUpdateRequest;
import kernel360.techpick.feature.folder.validator.FolderValidator;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FolderService {

	private final FolderProvider folderProvider;
	private final FolderMapper folderMapper;
	private final FolderValidator folderValidator;

	@Transactional(readOnly = true)
	public Map<String, Long> getBasicFolderIdMap(Long userId) {

		Map<String, Long> idMap = new HashMap<>();

		idMap.put("user", userId);
		idMap.put(FolderType.RECYCLE_BIN.toString(), folderProvider.findRecycleBin(userId).getId());
		idMap.put(FolderType.UNCLASSIFIED.toString(), folderProvider.findUnclassified(userId).getId());
		idMap.put(FolderType.ROOT.toString(), folderProvider.findRoot(userId).getId());

		return idMap;
	}

	@Transactional(readOnly = true)
	public List<FolderResponse> getFolderListByParentFolderId(FolderIdDto idDto) {

		folderValidator.validateFolderAccess(idDto.getUserId(), folderProvider.findById(idDto.getFolderId()));

		return folderProvider.findAllByUserIdAndParentFolderId(idDto.getUserId(), idDto.getFolderId())
			.stream()
			.map(folderMapper::createResponse)
			.toList();
	}

	@Transactional
	public void updateName(FolderIdDto idDto, FolderUpdateRequest request) throws ApiFolderException {

		// 변경하려는 폴더가 본인 폴더인지 검증
		Folder targetFolder = folderProvider.findById(idDto.getFolderId());
		folderValidator.validateFolderAccess(idDto.getUserId(), targetFolder);

		// 변경하려는 폴더가 기본폴더인지 검증
		folderValidator.validateChangeBasicFolder(targetFolder);

		targetFolder.updateName(request.name());
		folderProvider.save(targetFolder);
	}

}
