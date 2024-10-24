package kernel360.techpick.feature.domain.folder.service;

import java.util.List;

import kernel360.techpick.feature.domain.folder.dto.FolderCommand;
import kernel360.techpick.feature.domain.folder.dto.FolderResult;

public interface FolderService {

	FolderResult getFolder(FolderCommand.Read command);

	List<FolderResult> getChildFolderList(FolderCommand.Read command);

	FolderResult saveFolder(FolderCommand.Create command);

	FolderResult updateFolder(FolderCommand.Update command);

	void moveFolder(FolderCommand.Move command);

	void deleteFolder(FolderCommand.Delete command);
}
