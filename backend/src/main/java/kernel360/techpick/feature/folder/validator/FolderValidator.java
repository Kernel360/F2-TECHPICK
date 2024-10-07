package kernel360.techpick.feature.folder.validator;

import java.util.Objects;

import org.springframework.stereotype.Component;

import kernel360.techpick.core.model.folder.Folder;
import kernel360.techpick.core.model.folder.FolderType;
import kernel360.techpick.feature.folder.exception.ApiFolderException;
import kernel360.techpick.feature.folder.model.FolderProvider;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FolderValidator {

	private final FolderProvider folderProvider;

	public void validateFolderAccess(Long userId, Folder folder) throws ApiFolderException {

		if (folder == null || !Objects.equals(userId, folder.getUser().getId())) {
			throw ApiFolderException.FOLDER_ACCESS_DENIED();
		}
	}

	public void validateDuplicateFolderName(Long userId, String name) throws ApiFolderException {

		if (folderProvider.existsByUserIdAndName(userId, name)) {
			throw ApiFolderException.FOLDER_ALREADY_EXIST();
		}
	}

	public void validateChangeBasicFolder(Folder folder) {

		if (FolderType.getBasicFolderTypes().contains(folder.getFolderType())) {
			throw ApiFolderException.BASIC_FOLDER_CANNOT_CHANGED();
		}
	}

	public void validateFolderInRecycleBin(Folder folder) throws ApiFolderException {

		if (folderProvider.findParentBasicFolder(folder.getId()) != FolderType.RECYCLE_BIN) {
			throw ApiFolderException.CANNOT_DELETE_FOLDER_NOT_IN_RECYCLE_BIN();
		}
	}

	public void validateFolderNotUnclassified(Long userId, Folder folder) throws ApiFolderException {

		if (folder.getFolderType() == FolderType.UNCLASSIFIED) {
			throw ApiFolderException.FOLDER_ACCESS_DENIED();
		}
	}
}
