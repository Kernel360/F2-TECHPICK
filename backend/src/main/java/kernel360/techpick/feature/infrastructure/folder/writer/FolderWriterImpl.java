package kernel360.techpick.feature.infrastructure.folder.writer;

import org.springframework.stereotype.Component;

import kernel360.techpick.core.model.folder.Folder;
import kernel360.techpick.core.model.folder.FolderRepository;
import kernel360.techpick.feature.domain.folder.exception.ApiFolderException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FolderWriterImpl implements FolderWriter {

	private final FolderRepository folderRepository;

	@Override
	public Folder writeFolder(Folder folder) throws ApiFolderException {
		return folderRepository.save(folder);
	}

	@Override
	public void removeFolder(Folder folder) {
		folderRepository.delete(folder);
	}
}
