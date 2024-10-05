package kernel360.techpick.feature.folder.model;

import java.util.List;

import org.springframework.stereotype.Component;

import kernel360.techpick.core.model.folder.Folder;
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
}
