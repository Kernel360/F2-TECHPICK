package kernel360.techpick.feature.infrastructure.folder;

import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kernel360.techpick.core.model.folder.Folder;
import kernel360.techpick.core.model.folder.FolderRepository;
import kernel360.techpick.core.model.user.User;
import kernel360.techpick.feature.domain.folder.exception.ApiFolderException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FolderAdaptorImpl implements FolderAdaptor {

	private final FolderRepository folderRepository;

	@Override
	@Transactional(readOnly = true)
	public Folder readFolder(User user, Long folderId) {
		Folder folder = folderRepository.findById(folderId).orElseThrow(ApiFolderException::FOLDER_NOT_FOUND);
		if (ObjectUtils.notEqual(folder.getUser(), user)) {
			throw ApiFolderException.FOLDER_ACCESS_DENIED();
		}
		return folder;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Folder> readFolderList(User user, Folder parentFolder) {
		if (ObjectUtils.notEqual(parentFolder.getUser(), user)) {
			throw ApiFolderException.FOLDER_ACCESS_DENIED();
		}
		return folderRepository.findByParentFolder(parentFolder);
	}

	@Override
	@Transactional
	public Folder writeFolder(Folder folder) throws ApiFolderException {
		return folderRepository.save(folder);
	}

	@Override
	@Transactional
	public void removeFolder(Folder folder) {
		folderRepository.delete(folder);
	}
}
