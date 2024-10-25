package kernel360.techpick.feature.domain.folder.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kernel360.techpick.core.model.folder.Folder;
import kernel360.techpick.feature.domain.folder.dto.FolderCommand;
import kernel360.techpick.feature.domain.folder.dto.FolderMapper;
import kernel360.techpick.feature.domain.folder.dto.FolderResult;
import kernel360.techpick.feature.domain.folder.exception.ApiFolderException;
import kernel360.techpick.feature.infrastructure.folder.FolderAdaptor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FolderServiceImpl implements FolderService {

	private final FolderAdaptor folderAdaptor;
	private final FolderMapper folderMapper;

	@Override
	@Transactional(readOnly = true)
	public FolderResult getFolder(FolderCommand.Read command) {
		Folder folder = folderAdaptor.getFolder(command.folderId());

		if (!command.userId().equals(folder.getUser().getId())) {
			throw ApiFolderException.FOLDER_ACCESS_DENIED();
		}

		return folderMapper.toResult(folder);
	}

	@Override
	@Transactional(readOnly = true)
	public List<FolderResult> getChildFolderList(FolderCommand.Read command) {
		Folder folder = folderAdaptor.getFolder(command.folderId());

		if (!command.userId().equals(folder.getUser().getId())) {
			throw ApiFolderException.FOLDER_ACCESS_DENIED();
		}

		return folderAdaptor.getFolderList(folder.getChildFolderOrderList())
			.stream()
			.map(folderMapper::toResult)
			.toList();
	}

	@Override
	@Transactional(readOnly = true)
	public FolderResult getRootFolder(Long userId) {
		return folderMapper.toResult(folderAdaptor.getRootFolder(userId));
	}

	@Override
	@Transactional(readOnly = true)
	public FolderResult getRecycleBin(Long userId) {
		return folderMapper.toResult(folderAdaptor.getRecycleBin(userId));
	}

	@Override
	@Transactional(readOnly = true)
	public FolderResult getUnclassifiedFolder(Long userId) {
		return folderMapper.toResult(folderAdaptor.getUnclassifiedFolder(userId));
	}

	@Override
	@Transactional
	public FolderResult saveFolder(FolderCommand.Create command) {
		return folderMapper.toResult(folderAdaptor.saveFolder(command));
	}

	@Override
	@Transactional
	public FolderResult updateFolder(FolderCommand.Update command) {

		Folder folder = folderAdaptor.getFolder(command.folderId());

		if (!command.userId().equals(folder.getUser().getId())) {
			throw ApiFolderException.FOLDER_ACCESS_DENIED();
		}

		return folderMapper.toResult(folderAdaptor.updateFolder(command));
	}

	@Override
	@Transactional
	public void moveFolder(FolderCommand.Move command) {

		Folder folder = folderAdaptor.getFolder(command.folderId());

		if (!command.userId().equals(folder.getUser().getId())) {
			throw ApiFolderException.FOLDER_ACCESS_DENIED();
		}

		if (isParentFolderNotChanged(command, folder.getParentFolder())) {
			folderAdaptor.moveFolderWithinParent(command);
		} else {
			folderAdaptor.moveFolderToDifferentParent(command);
		}
	}

	@Override
	@Transactional
	public void deleteFolder(FolderCommand.Delete command) {

		Folder folder = folderAdaptor.getFolder(command.folderId());

		if (!command.userId().equals(folder.getUser().getId())) {
			throw ApiFolderException.FOLDER_ACCESS_DENIED();
		}

		folderAdaptor.deleteFolder(command);
	}

	private boolean isParentFolderNotChanged(FolderCommand.Move command, Folder originalFolder) {
		return (command.parentFolderId() == null || originalFolder.getId().equals(command.parentFolderId()));
	}
}
