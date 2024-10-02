package kernel360.techpick.feature.folder.model;

import org.springframework.stereotype.Component;

import kernel360.techpick.core.model.folder.FolderStructure;
import kernel360.techpick.feature.folder.repository.FolderStructureRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FolderStructureProvider {

	private final FolderStructureRepository folderStructureRepository;

	public FolderStructure save(FolderStructure folderStructure) {
		return folderStructureRepository.save(folderStructure);
	}

	public FolderStructure findByUserId(Long userId) {
		return folderStructureRepository.findByUserId(userId);
	}

}
