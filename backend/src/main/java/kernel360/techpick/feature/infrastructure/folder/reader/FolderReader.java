package kernel360.techpick.feature.infrastructure.folder.reader;

import java.util.List;

import kernel360.techpick.core.model.folder.Folder;
import kernel360.techpick.core.model.user.User;

public interface FolderReader {

	Folder readFolder(User user, Long folderId);

	List<Folder> readFolderList(User user, Folder parentFolder);
}
