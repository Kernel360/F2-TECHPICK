package kernel360.techpick.feature.infrastructure.folder;

import java.util.List;

import kernel360.techpick.core.model.folder.Folder;
import kernel360.techpick.core.model.user.User;
import kernel360.techpick.feature.domain.folder.exception.ApiFolderException;

public interface FolderAdapter {

	Folder readFolder(User user, Long folderId);

	List<Folder> readFolderList(User user, Folder parentFolder);

	Folder writeFolder(Folder folder) throws ApiFolderException;

	void removeFolder(Folder folder);
}
