package kernel360.techpick.feature.structure.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kernel360.techpick.core.model.folder.Folder;
import kernel360.techpick.core.model.user.User;
import kernel360.techpick.feature.folder.service.FolderStructureService;
import kernel360.techpick.feature.pick.service.PickStructureService;
import kernel360.techpick.feature.structure.model.StructureDataProxy;
import kernel360.techpick.feature.structure.model.StructureMapper;
import kernel360.techpick.feature.structure.model.StructureJsonProvider;
import kernel360.techpick.feature.structure.service.dto.StructureDeleteRequest;
import kernel360.techpick.feature.structure.service.dto.StructureMoveRequest;
import kernel360.techpick.feature.structure.service.node.client.ClientNode;
import kernel360.techpick.feature.structure.service.node.server.ServerNode;
import kernel360.techpick.feature.structure.validator.StructureValidator;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StructureService {

	private final FolderStructureService folderStructureService;
	private final PickStructureService pickStructureService;

	private final StructureJsonProvider structureJsonProvider;
	private final StructureValidator structureValidator;
	private final StructureMapper structureMapper;

    @Transactional(readOnly = true)
	public Structure<ClientNode> getClientStructure(User user) {
		Structure<ServerNode> serverStructure
			= structureJsonProvider.findStructure(user).getStructure();

		StructureDataProxy dataProxy
			= StructureDataProxy.fromStructureService(user, pickStructureService, folderStructureService);

		return structureMapper.toClientStructure(serverStructure, dataProxy);
	}

	@Transactional
	public void saveInitialStructure(User user) {
		// TODO: 이미 Structure가 있는 경우 예외 처리가 필요한지?
		structureJsonProvider.updateStructureByUser(user, new Structure<ServerNode>());
	}

	@Transactional
	public void moveFolder(User user, StructureMoveRequest request) {
		folderStructureService.moveFolder(user, structureMapper.toFolderMoveDto(request));
		validateStructure(user, request.structure());

		structureJsonProvider.updateStructureByUser(user, request.structure());
	}

	@Transactional
	public void deleteFolder(User user, StructureDeleteRequest request) {
		folderStructureService.deleteFolder(user, structureMapper.toFolderDeleteDto(request));
		validateStructure(user, request.structure());

        structureJsonProvider.updateStructureByUser(user, request.structure());
	}

	@Transactional
	public void movePick(User user, StructureMoveRequest request) {
		pickStructureService.movePick(user, structureMapper.toPickMoveDto(request));
		validateStructure(user, request.structure());

        structureJsonProvider.updateStructureByUser(user, request.structure());
	}

	@Transactional
	public void deletePick(User user, StructureDeleteRequest request) {
		pickStructureService.deletePick(user, structureMapper.toPickDeleteDto(request));
		validateStructure(user, request.structure());

        structureJsonProvider.updateStructureByUser(user, request.structure());
	}

	private void validateStructure(User user, Structure<ServerNode> structure) {
		Folder root = folderStructureService.getRootByUser(user);
		Folder recycleBin = folderStructureService.getRecycleBinByUser(user);
		StructureDataProxy dataProxy
			= StructureDataProxy.fromStructureService(user, pickStructureService, folderStructureService);

		structureValidator.validate(structure, dataProxy, root.getId(), recycleBin.getId());
	}
}
