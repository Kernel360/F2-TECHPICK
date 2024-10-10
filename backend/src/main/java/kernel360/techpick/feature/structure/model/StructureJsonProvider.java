package kernel360.techpick.feature.structure.model;

import org.springframework.stereotype.Component;

import kernel360.techpick.core.model.folder.StructureJson;
import kernel360.techpick.core.model.user.User;
import kernel360.techpick.feature.structure.exception.ApiStructureException;
import kernel360.techpick.feature.structure.repository.StructureJsonRepository;
import kernel360.techpick.feature.structure.service.Structure;
import kernel360.techpick.feature.structure.service.node.server.ServerNode;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StructureJsonProvider {

	private final StructureJsonRepository structureJsonRepository;

	public StructureJson findStructure(User user) {
		return structureJsonRepository
			.findByUserId(user.getId())
			.orElseThrow(ApiStructureException::USER_STRUCTURE_JSON_NOT_FOUND);
	}

	public boolean existsByUser(User user) {
		return structureJsonRepository.existsByUser(user);
	}

	public StructureJson updateStructureByUser(User user, Structure<ServerNode> structure) {
		StructureJson structureJson = this.findStructure(user);
		structureJson.updateStructure(structure);
		return structureJsonRepository.save(structureJson);
	}

	public StructureJson saveStructure(StructureJson structureJson) {
		return structureJsonRepository.save(structureJson);
	}
}
