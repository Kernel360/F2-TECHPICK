package kernel360.techpick.feature.structure.model;

import org.springframework.stereotype.Component;

import kernel360.techpick.core.model.folder.StructureJson;
import kernel360.techpick.core.model.user.User;
import kernel360.techpick.feature.folder.service.dto.FolderDeleteDto;
import kernel360.techpick.feature.folder.service.dto.FolderMoveDto;
import kernel360.techpick.feature.pick.service.dto.PickDeleteDto;
import kernel360.techpick.feature.pick.service.dto.PickMoveDto;
import kernel360.techpick.feature.structure.service.Structure;
import kernel360.techpick.feature.structure.service.dto.StructureDeleteRequest;
import kernel360.techpick.feature.structure.service.dto.StructureMoveRequest;
import kernel360.techpick.feature.structure.service.node.client.ClientNode;
import kernel360.techpick.feature.structure.service.node.server.ServerNode;

@Component
public class StructureMapper {

	public Structure<ClientNode> toClientStructure(
		Structure<ServerNode> serverStructure,
		StructureDataProxy structureDataProxy
	) {
		return new Structure<>(
			serverStructure.getRootFolder()
				.stream()
				.map(node -> node.toClientNode(structureDataProxy))
				.toList(),
			serverStructure.getRecycleBinFolder()
				.stream()
				.map(node -> node.toClientNode(structureDataProxy))
				.toList()
		);
	}

	public FolderMoveDto toFolderMoveDto(Long folderId, Long parentFolderId) {
		return new FolderMoveDto(folderId, parentFolderId);
	}

	public FolderDeleteDto toFolderDeleteDto(Long folderId) {
		return new FolderDeleteDto(folderId);
	}

	public PickMoveDto toPickMoveDto(Long pickId, Long parentFolderId) {
		return new PickMoveDto(pickId, parentFolderId);
	}

	public PickDeleteDto toPickDeleteDto(Long pickId) {
		return new PickDeleteDto(pickId);
	}

	public StructureJson toStructureJsonEntity(User user, Structure<ServerNode> structure) {
		return StructureJson.create(user, structure);
	}
}
