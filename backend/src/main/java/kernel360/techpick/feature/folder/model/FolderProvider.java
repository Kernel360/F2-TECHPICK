package kernel360.techpick.feature.folder.model;

import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import kernel360.techpick.core.model.folder.Folder;
import kernel360.techpick.core.model.folder.FolderType;
import kernel360.techpick.feature.folder.exception.ApiFolderException;
import kernel360.techpick.feature.folder.repository.FolderRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FolderProvider {

	private final FolderRepository folderRepository;

	public Folder save(Folder folder) {
		return folderRepository.save(folder);
	}

	public Folder findById(Long folderId) {
		return folderRepository.findById(folderId).orElseThrow(ApiFolderException::FOLDER_NOT_FOUND);
	}

	public List<Folder> findAllByUserId(Long userId) {
		return folderRepository.findAllByUserId(userId);
	}

	public List<Folder> findAllByUserIdAndParentFolderId(Long userId, Long parentFolderId) {
		return folderRepository.findAllByUserIdAndParentFolderId(userId, parentFolderId);
	}

	public Folder findUnclassified(Long userId) {
		return folderRepository.findUnclassifiedByUserId(userId);
	}

	public Folder findRecycleBin(Long userId) {
		return folderRepository.findRecycleBinByUserId(userId);
	}

	public Folder findRoot(Long userId) {
		return folderRepository.findRootByUserId(userId);
	}

	public void deleteById(Long folderId) {
		folderRepository.deleteById(folderId);
	}

	public boolean existsByUserIdAndName(Long userId, String name) {
		return folderRepository.existsByUserIdAndName(userId, name);
	}

	// 현재 폴더가 어떤 BasicFolder에 속해있는지 반환하는 함수
	// TODO: Spring Data Jpa에서 재귀쿼리를 제공하지 않아 반복조회로 해결.. QueryDSL 도입 후 리팩토링 필요
	public FolderType findParentBasicFolder(Long folderId) {

		EnumSet<FolderType> basicFolderTypes = FolderType.getBasicFolderTypes();
		Folder currentFolder = this.findById(folderId);

		while (!basicFolderTypes.contains(currentFolder.getFolderType())) {
			currentFolder = currentFolder.getParentFolder();
		}

		return currentFolder.getFolderType();
	}

	public Map<Long, Folder> findAllByUserIdAsMap(Long userId) {
		return folderRepository.findAllByUserId(userId).stream()
							   .collect(Collectors.toMap(Folder::getId, Function.identity()));
	}
}
