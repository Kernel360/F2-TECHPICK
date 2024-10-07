package kernel360.techpick.feature.structure.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kernel360.techpick.core.model.folder.StructureJson;
import kernel360.techpick.feature.folder.service.FolderStructureService;
import kernel360.techpick.feature.pick.service.PickStructureService;
import kernel360.techpick.feature.structure.repository.StructureJsonRepository;
import kernel360.techpick.feature.structure.service.dto.StructureDeleteRequest;
import kernel360.techpick.feature.structure.service.dto.StructureMoveRequest;
import kernel360.techpick.feature.structure.service.model.StructureMapper;
import kernel360.techpick.feature.structure.service.node.client.ClientNode;
import kernel360.techpick.feature.structure.service.node.server.ServerNode;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StructureService {

	private final FolderStructureService folderStructureService;
	private final PickStructureService pickStructureService;
	private final StructureJsonRepository structureJsonRepository;
	private final StructureMapper mapper;

	public String getStructureByUserId(Long userId) {

		StructureJson structureJson = structureJsonRepository.findByUserId(userId);
		Structure<ClientNode> clientNodeStructure = Structure.fromJson(structureJson.getStructure(), ClientNode.class);

		return clientNodeStructure.serialize();
	}

	@Transactional
	public void moveFolder(StructureMoveRequest request) {

		folderStructureService.moveFolder(mapper.toFolderMoveDto(request));

		// TODO: validate serverNodeStructure
		Structure<ServerNode> serverNodeStructure = Structure.fromJson(request.getJson(), ServerNode.class);
		// validator.validate(serverNodeStructure);

		structureJsonRepository.save(mapper.toStructureJson(request.getUserId(), serverNodeStructure));
	}

	@Transactional
	public void deleteFolder(StructureDeleteRequest request) {

		folderStructureService.deleteFolder(mapper.toFolderDeleteDto(request));

		// TODO: validate serverNodeStructure
		Structure<ServerNode> serverNodeStructure = Structure.fromJson(request.getJson(), ServerNode.class);
		// validator.validate(serverNodeStructure);

		structureJsonRepository.save(mapper.toStructureJson(request.getUserId(), serverNodeStructure));
	}

	@Transactional
	public void movePick(StructureMoveRequest request) {

		pickStructureService.movePick(mapper.toPickMoveDto(request));

		// TODO: validate serverNodeStructure
		Structure<ServerNode> serverNodeStructure = Structure.fromJson(request.getJson(), ServerNode.class);
		// validator.validate(serverNodeStructure);

		structureJsonRepository.save(mapper.toStructureJson(request.getUserId(), serverNodeStructure));
	}

	@Transactional
	public void deletePick(StructureDeleteRequest request) {
		pickStructureService.deletePick(mapper.toPickDeleteDto(request));

		// TODO: validate serverNodeStructure
		Structure<ServerNode> serverNodeStructure = Structure.fromJson(request.getJson(), ServerNode.class);
		// validator.validate(serverNodeStructure);

		structureJsonRepository.save(mapper.toStructureJson(request.getUserId(), serverNodeStructure));
	}
}
