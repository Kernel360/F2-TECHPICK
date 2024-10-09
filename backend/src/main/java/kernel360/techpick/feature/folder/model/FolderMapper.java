package kernel360.techpick.feature.folder.model;

import java.util.List;

import kernel360.techpick.core.model.folder.Folder;
import kernel360.techpick.core.model.folder.FolderType;
import kernel360.techpick.core.model.user.User;
import kernel360.techpick.feature.folder.exception.ApiFolderException;
import kernel360.techpick.feature.folder.service.dto.FolderCreateRequest;
import kernel360.techpick.feature.folder.service.dto.FolderResponse;

public class FolderMapper {

	public static Folder toFolderEntity(User user, FolderCreateRequest request, FolderType folderType) {
		switch (folderType) {
			case ROOT -> Folder.rootFolder(request.name(), user);
			case RECYCLE_BIN -> Folder.recycleBinFolder(request.name(), user);
            case UNCLASSIFIED -> Folder.unclassifedFolder(request.name(), user);

			case GENERAL -> Folder.generalFolder(request.name(), user);

			default -> throw ApiFolderException.INVALID_FOLDER_TYPE();
        }
		return Folder.generalFolder(request.name(), user);
	}

	public static FolderResponse toFolderResponse(Folder folder) {
		return new FolderResponse(
			folder.getId(),
			folder.getName(),
			folder.findParentFolderId(),
			folder.getUser().getId()
		);
	}

	public static List<FolderResponse> toFolderResponseList(List<Folder> folders) {
		return folders.stream().map(FolderMapper::toFolderResponse).toList();
	}
}
