package kernel360.techpick.feature.infrastructure.folder;

import java.util.List;

import kernel360.techpick.core.model.folder.Folder;
import kernel360.techpick.feature.domain.folder.dto.FolderCommand;

public interface FolderAdaptor {

	Folder getFolder(Long folderId);

	List<Folder> getFolderList(List<Long> idList);

	Folder saveFolder(FolderCommand.Create command);

	Folder updateFolder(FolderCommand.Update command);

	List<Long> moveFolderWithinParent(FolderCommand.Move command);

	List<Long> moveFolderToDifferentParent(FolderCommand.Move command);

	void deleteFolder(FolderCommand.Delete command);
}
