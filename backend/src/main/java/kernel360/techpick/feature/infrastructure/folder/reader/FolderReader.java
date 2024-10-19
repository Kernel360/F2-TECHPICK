package kernel360.techpick.feature.infrastructure.folder.reader;

import kernel360.techpick.core.model.folder.Folder;
import kernel360.techpick.core.model.user.User;

public interface FolderReader {

    Folder read(User user, Long folderId);
}
