package kernel360.techpick.feature.domain.folder.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kernel360.techpick.core.model.folder.Folder;
import kernel360.techpick.core.model.user.User;
import kernel360.techpick.feature.domain.folder.dto.FolderCommand;
import kernel360.techpick.feature.domain.folder.dto.FolderMapper;
import kernel360.techpick.feature.domain.folder.dto.FolderResult;
import kernel360.techpick.feature.infrastructure.folder.reader.FolderReader;
import kernel360.techpick.feature.infrastructure.folder.writer.FolderWriter;
import kernel360.techpick.feature.infrastructure.user.reader.UserReader;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FolderServiceImpl implements FolderService {

	private final FolderReader folderReader;
	private final FolderWriter folderWriter;
	private final FolderMapper folderMapper;
	private final UserReader userReader;

	@Override
	@Transactional(readOnly = true)
	public FolderResult getFolder(FolderCommand.Read command) {
		User user = userReader.readCurrentUser();
		return folderMapper.toResult(folderReader.readFolder(user, command.folderId()));
	}

	@Override
	@Transactional(readOnly = true)
	public List<FolderResult> getFolderListByParentFolderId(FolderCommand.Read command) {

		User user = userReader.readCurrentUser();
		Folder parentFolder = folderReader.readFolder(user, command.folderId());

		return folderReader.readFolderList(user, parentFolder)
			.stream()
			.map(folderMapper::toResult)
			.toList();
	}

	@Override
	@Transactional
	public FolderResult saveNewFolder(FolderCommand.Create command) {

		User user = userReader.readCurrentUser();
		Folder parentFolder = folderReader.readFolder(user, command.parentFolderId());
		Folder folder = folderMapper.toEntity(command, user, parentFolder);

		return folderMapper.toResult(folderWriter.writeFolder(folder));
	}

	@Override
	@Transactional
	public FolderResult updateFolder(FolderCommand.Update command) {

		User user = userReader.readCurrentUser();
		Folder targetFolder = folderReader.readFolder(user, command.folderId());
		targetFolder.updateFolderName(command.name());

		return folderMapper.toResult(targetFolder);
	}

	@Override
	@Transactional
	public FolderResult moveFolder(FolderCommand.Move command) {
		User user = userReader.readCurrentUser();
		Folder targetFolder = folderReader.readFolder(user, command.folderId());
		Folder originalParentFolder = targetFolder.getParentFolder();

		if (isParentFolderNotChanged(command, originalParentFolder)) {
			originalParentFolder.updateChildFolderOrder(command.folderId(), command.orderIdx());
			return folderMapper.toResult(targetFolder);
		}

		originalParentFolder.removeChildFolderOrder(command.folderId());
		Folder newParentFolder = folderReader.readFolder(user, command.parentFolderId());
		newParentFolder.updateChildFolderOrder(command.folderId(), command.orderIdx());
		targetFolder.updateParentFolder(newParentFolder);

		return folderMapper.toResult(targetFolder);
	}

	@Override
	@Transactional
	public void deleteFolder(FolderCommand.Delete command) {
		User user = userReader.readCurrentUser();
		Folder targetFolder = folderReader.readFolder(user, command.folderId());
		targetFolder.getParentFolder().removeChildFolderOrder(command.folderId());
		folderWriter.removeFolder(targetFolder);
	}

	private boolean isParentFolderNotChanged(FolderCommand.Move command, Folder originalFolder) {
		return (command.parentFolderId() == null || originalFolder.getId().equals(command.parentFolderId()));
	}
}
