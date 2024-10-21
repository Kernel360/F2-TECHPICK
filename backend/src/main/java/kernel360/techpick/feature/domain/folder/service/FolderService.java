package kernel360.techpick.feature.domain.folder.service;

import java.util.List;

import kernel360.techpick.feature.domain.folder.dto.FolderCommand;
import kernel360.techpick.feature.domain.folder.dto.FolderResult;

public interface FolderService {

	FolderResult getFolder(FolderCommand.Read command);

	List<FolderResult> getFolderListByParentFolderId(FolderCommand.Read command);

	FolderResult saveNewFolder(FolderCommand.Create command);

	FolderResult updateFolder(FolderCommand.Update command);

	FolderResult moveFolder(FolderCommand.Move command);

	void deleteFolder(FolderCommand.Delete command);
}
