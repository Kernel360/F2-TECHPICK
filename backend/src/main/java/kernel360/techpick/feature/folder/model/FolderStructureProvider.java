package kernel360.techpick.feature.folder.model;

import org.springframework.stereotype.Component;

import kernel360.techpick.core.model.folder.StructureJson;
import kernel360.techpick.feature.structure.repository.StructureJsonRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FolderStructureProvider {

	private final StructureJsonRepository structureJsonRepository;

	public StructureJson save(StructureJson structureJson) {
		return structureJsonRepository.save(structureJson);
	}

	public StructureJson findByUserId(Long userId) {
		return structureJsonRepository.findByUserId(userId);
	}

}
