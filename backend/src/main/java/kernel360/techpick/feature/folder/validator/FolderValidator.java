package kernel360.techpick.feature.folder.validator;

import org.springframework.stereotype.Component;

import kernel360.techpick.core.model.folder.Folder;
import kernel360.techpick.core.model.folder.FolderType;
import kernel360.techpick.core.model.user.User;
import kernel360.techpick.feature.folder.exception.ApiFolderException;
import kernel360.techpick.feature.folder.model.FolderProvider;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FolderValidator {

	private final FolderProvider folderProvider;

	public void validateFolderAccess(User user, Folder folder) throws ApiFolderException {

		if (!user.equals(folder.getUser())) {
			throw ApiFolderException.FOLDER_ACCESS_DENIED();
		}
	}

	public void validateDuplicateFolderName(User user, String name) throws ApiFolderException {

		if (folderProvider.existsByUserAndName(user, name)) {
			throw ApiFolderException.FOLDER_ALREADY_EXIST();
		}
	}

	public void validateChangeBasicFolder(Folder folder) {

		if (FolderType.getBasicFolderTypes().contains(folder.getFolderType())) {
			throw ApiFolderException.BASIC_FOLDER_CANNOT_CHANGED();
		}
	}

	public void validateBasicFolderDoesNotExist(User user) {
		if (FolderType.getBasicFolderTypes().stream()
					   .anyMatch((type) -> folderProvider.existsByUserAndFolderType(user, type))
		) {
			throw ApiFolderException.BASIC_FOLDER_ALREADY_EXISTS();
		}
	}

	public void validateFolderInRecycleBin(Folder folder) throws ApiFolderException {

		if (folderProvider.findParentBasicFolderById(folder.getId()) != FolderType.RECYCLE_BIN) {
			throw ApiFolderException.CANNOT_DELETE_FOLDER_NOT_IN_RECYCLE_BIN();
		}
	}

	public void validateFolderNotUnclassified(Folder folder) throws ApiFolderException {

		if (folder.getFolderType() == FolderType.UNCLASSIFIED) {
			throw ApiFolderException.FOLDER_ACCESS_DENIED();
		}
	}
}
