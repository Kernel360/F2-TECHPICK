package kernel360.techpick.feature.infrastructure.folder.writer;

import kernel360.techpick.core.model.folder.Folder;
import kernel360.techpick.feature.domain.folder.exception.ApiFolderException;

public interface FolderWriter {

	Folder writeFolder(Folder folder) throws ApiFolderException;

	void removeFolder(Folder folder);
}
