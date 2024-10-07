package kernel360.techpick.feature.structure.service.model;

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
import kernel360.techpick.feature.user.UserRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StructureMapper {

	/**
	 * toClientNode 변환 과정에서 name이 설정됩니다.
	 */
	private final NameProvider nameProvider;
	private final UserRepository userRepository;

	public Structure<ClientNode> toClientStructure(Structure<ServerNode> serverStructure) {
		return new Structure<>(

			serverStructure.getRootFolder()
				.stream()
				.map(node -> node.toClientNode(nameProvider))
				.toList(),

			serverStructure.getRecycleBinFolder()
				.stream()
				.map(node -> node.toClientNode(nameProvider))
				.toList()
		);
	}

	public FolderMoveDto toFolderMoveDto(StructureMoveRequest request) {
		return new FolderMoveDto(request.getUserId(), request.getTargetId(), request.getParentFolderId());
	}

	public FolderDeleteDto toFolderDeleteDto(StructureDeleteRequest request) {
		return new FolderDeleteDto(request.getUserId(), request.getTargetId());
	}

	public PickMoveDto toPickMoveDto(StructureMoveRequest request) {
		return new PickMoveDto(request.getTargetId(), request.getUserId(), request.getParentFolderId());
	}

	public PickDeleteDto toPickDeleteDto(StructureDeleteRequest request) {
		return new PickDeleteDto(request.getTargetId(), request.getUserId());
	}

	public StructureJson toStructureJson(Long userId, Structure<ServerNode> structure) {
		
		String json = structure.serialize();
		User user = userRepository.findById(userId).get();

		return StructureJson.create(json, user);
	}
}
