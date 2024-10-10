package kernel360.techpick.feature.structure.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kernel360.techpick.core.model.folder.Folder;
import kernel360.techpick.core.model.folder.StructureJson;
import kernel360.techpick.core.model.user.User;
import kernel360.techpick.feature.folder.service.FolderStructureService;
import kernel360.techpick.feature.pick.service.PickStructureService;
import kernel360.techpick.feature.structure.exception.ApiStructureException;
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
		validateThatStructureDoesNotExist(user);
		StructureJson initialStructure
			= structureMapper.toStructureJsonEntity(user, new Structure<ServerNode>());

		structureJsonProvider.saveStructure(initialStructure);
	}

	@Transactional
	public void moveFolder(User user, Long folderId, StructureMoveRequest request) {
		folderStructureService.moveFolder(user, structureMapper.toFolderMoveDto(folderId, request.parentFolderId()));
		validateStructure(user, request.structure());

		structureJsonProvider.updateStructureByUser(user, request.structure());
	}

	@Transactional
	public void deleteFolder(User user, Long folderId, StructureDeleteRequest request) {
		folderStructureService.deleteFolder(user, structureMapper.toFolderDeleteDto(folderId));
		validateStructure(user, request.structure());

        structureJsonProvider.updateStructureByUser(user, request.structure());
	}

	@Transactional
	public void movePick(User user, Long pickId, StructureMoveRequest request) {
		pickStructureService.movePick(user, structureMapper.toPickMoveDto(pickId, request.parentFolderId()));
		validateStructure(user, request.structure());

        structureJsonProvider.updateStructureByUser(user, request.structure());
	}

	@Transactional
	public void deletePick(User user, Long pickId, StructureDeleteRequest request) {
		pickStructureService.deletePick(user, structureMapper.toPickDeleteDto(pickId));
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

	private void validateThatStructureDoesNotExist(User user) {
		if (structureJsonProvider.existsByUser(user)) {
			throw ApiStructureException.USER_STRUCTURE_ALREADY_EXISTS();
		};
	}
}
