package kernel360.techpick.feature.folder.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kernel360.techpick.core.model.folder.Folder;
import kernel360.techpick.core.model.folder.FolderType;
import kernel360.techpick.core.model.user.User;
import kernel360.techpick.feature.folder.exception.ApiFolderException;
import kernel360.techpick.feature.folder.model.FolderMapper;
import kernel360.techpick.feature.folder.model.FolderProvider;
import kernel360.techpick.feature.folder.service.dto.FolderCreateRequest;
import kernel360.techpick.feature.folder.service.dto.FolderResponse;
import kernel360.techpick.feature.folder.service.dto.FolderUpdateRequest;
import kernel360.techpick.feature.folder.validator.FolderValidator;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FolderService {

	private final FolderProvider folderProvider;
	private final FolderValidator folderValidator;
	private final FolderMapper folderMapper;

	@Transactional
	public synchronized FolderResponse createGeneralFolder(
		User user,
		FolderCreateRequest request
	) throws ApiFolderException {

		// 생성하려는 폴더 이름이 중복되는지 검증
		folderValidator.validateDuplicateFolderName(user, request.name());

		Folder newFolder = folderProvider.save(
			folderMapper.toFolderEntity(user, FolderType.GENERAL, request)
		);

		return folderMapper.toFolderResponse(newFolder);
	}

	// 최초 사용자 생성시 기본 폴더 생성을 위해 1번 호출 됩니다.
	public List<FolderResponse> createBasicFolders(User user) throws ApiFolderException {
		// 기본 폴더가 이미 있는지 확인
		folderValidator.validateBasicFolderDoesNotExist(user);

		List<Folder> createdFolders = new ArrayList<>();

		for (FolderType folderType : FolderType.getBasicFolderTypes()) {
			Folder newFolder = folderProvider.save(
				folderMapper.toFolderEntity(user, folderType, new FolderCreateRequest(folderType.getLabel()))
			);
			createdFolders.add(newFolder);
		}

		return createdFolders.stream().map(folderMapper::toFolderResponse).toList();
	}

	@Transactional(readOnly = true)
	public Map<String, Long> getBasicFolderIdMap(User user) {

		Map<String, Long> idMap = new HashMap<>();

		idMap.put("user", user.getId());
		idMap.put(FolderType.RECYCLE_BIN.toString(), folderProvider.findRecycleBin(user).getId());
		idMap.put(FolderType.UNCLASSIFIED.toString(), folderProvider.findUnclassified(user).getId());
		idMap.put(FolderType.ROOT.toString(), folderProvider.findRoot(user).getId());

		return idMap;
	}

	@Transactional(readOnly = true)
	public List<FolderResponse> getFolderListByParentFolderId(User user, Long folderId) {

		folderValidator.validateFolderAccess(user, folderProvider.findById(folderId));

		return folderProvider.findAllByUserAndParentFolderId(user, folderId)
			.stream()
			.map(folderMapper::toFolderResponse)
			.toList();
	}

	@Transactional
	public void updateName(User user, Long folderId, FolderUpdateRequest request) throws ApiFolderException {

		// 변경하려는 폴더가 본인 폴더인지 검증
		Folder targetFolder = folderProvider.findById(folderId);
		folderValidator.validateFolderAccess(user, targetFolder);

		// 변경하려는 폴더가 기본폴더인지 검증
		folderValidator.validateChangeBasicFolder(targetFolder);

		// 변경하려는 이름이 중복되는지 검증
		folderValidator.validateDuplicateFolderName(user, request.name());

		targetFolder.updateName(request.name());
		folderProvider.save(targetFolder);
	}
}
