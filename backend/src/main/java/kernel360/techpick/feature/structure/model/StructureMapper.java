package kernel360.techpick.feature.structure.model;

import kernel360.techpick.feature.folder.service.dto.FolderDeleteDto;
import kernel360.techpick.feature.folder.service.dto.FolderMoveDto;
import kernel360.techpick.feature.pick.service.dto.PickDeleteDto;
import kernel360.techpick.feature.pick.service.dto.PickMoveDto;
import kernel360.techpick.feature.structure.service.Structure;
import kernel360.techpick.feature.structure.service.dto.StructureDeleteRequest;
import kernel360.techpick.feature.structure.service.dto.StructureMoveRequest;
import kernel360.techpick.feature.structure.service.node.client.ClientNode;
import kernel360.techpick.feature.structure.service.node.server.ServerNode;

public class StructureMapper {

	public static Structure<ClientNode> toClientStructure(
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

	public static FolderMoveDto toFolderMoveDto(StructureMoveRequest request) {
		return new FolderMoveDto(request.targetId(), request.parentFolderId());
	}

	public static FolderDeleteDto toFolderDeleteDto(StructureDeleteRequest request) {
		return new FolderDeleteDto(request.targetId());
	}

	public static PickMoveDto toPickMoveDto(StructureMoveRequest request) {
		return new PickMoveDto(request.targetId(), request.parentFolderId());
	}

	public static PickDeleteDto toPickDeleteDto(StructureDeleteRequest request) {
		return new PickDeleteDto(request.targetId());
	}
}
