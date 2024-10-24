package kernel360.techpick.feature.domain.folder.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kernel360.techpick.core.model.folder.Folder;
import kernel360.techpick.core.model.user.User;
import kernel360.techpick.feature.domain.folder.dto.FolderCommand;
import kernel360.techpick.feature.domain.folder.dto.FolderMapper;
import kernel360.techpick.feature.domain.folder.dto.FolderResult;
import kernel360.techpick.feature.infrastructure.folder.FolderAdapter;
import kernel360.techpick.feature.infrastructure.user.UserAdaptor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FolderServiceImpl implements FolderService {

	private final FolderAdapter folderAdapter;
	private final FolderMapper folderMapper;
	private final UserAdaptor userAdaptor;

	@Override
	@Transactional(readOnly = true)
	public FolderResult getFolder(FolderCommand.Read command) {
		User user = userAdaptor.getUser(command.userId());
		return folderMapper.toResult(folderAdapter.readFolder(user, command.folderId()));
	}

	@Override
	@Transactional(readOnly = true)
	public List<FolderResult> getFolderListByParentFolderId(FolderCommand.Read command) {

		User user = userAdaptor.getUser(command.userId());
		Folder parentFolder = folderAdapter.readFolder(user, command.folderId());

		return folderAdapter.readFolderList(user, parentFolder)
			.stream()
			.map(folderMapper::toResult)
			.toList();
	}

	@Override
	@Transactional
	public FolderResult saveNewFolder(FolderCommand.Create command) {

		User user = userAdaptor.getUser(command.userId());
		Folder parentFolder = folderAdapter.readFolder(user, command.parentFolderId());
		Folder folder = folderMapper.toEntity(command, user, parentFolder);

		return folderMapper.toResult(folderAdapter.writeFolder(folder));
	}

	@Override
	@Transactional
	public FolderResult updateFolder(FolderCommand.Update command) {

		User user = userAdaptor.getUser(command.userId());
		Folder targetFolder = folderAdapter.readFolder(user, command.folderId());
		targetFolder.updateFolderName(command.name());

		return folderMapper.toResult(targetFolder);
	}

	@Override
	@Transactional
	public FolderResult moveFolder(FolderCommand.Move command) {
		User user = userAdaptor.getUser(command.userId());
		Folder targetFolder = folderAdapter.readFolder(user, command.folderId());
		Folder originalParentFolder = targetFolder.getParentFolder();

		if (isParentFolderNotChanged(command, originalParentFolder)) {
			originalParentFolder.updateChildFolderOrder(command.folderId(), command.orderIdx());
			return folderMapper.toResult(targetFolder);
		}

		originalParentFolder.removeChildFolderOrder(command.folderId());
		Folder newParentFolder = folderAdapter.readFolder(user, command.parentFolderId());
		newParentFolder.updateChildFolderOrder(command.folderId(), command.orderIdx());
		targetFolder.updateParentFolder(newParentFolder);

		return folderMapper.toResult(targetFolder);
	}

	@Override
	@Transactional
	public void deleteFolder(FolderCommand.Delete command) {
		User user = userAdaptor.getUser(command.userId());
		Folder targetFolder = folderAdapter.readFolder(user, command.folderId());
		targetFolder.getParentFolder().removeChildFolderOrder(command.folderId());
		folderAdapter.removeFolder(targetFolder);
	}

	private boolean isParentFolderNotChanged(FolderCommand.Move command, Folder originalFolder) {
		return (command.parentFolderId() == null || originalFolder.getId().equals(command.parentFolderId()));
	}
}
