package kernel360.techpick.feature.infrastructure.folder.reader;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import kernel360.techpick.core.model.folder.Folder;
import kernel360.techpick.core.model.folder.FolderRepository;
import kernel360.techpick.core.model.user.User;
import kernel360.techpick.feature.domain.folder.exception.ApiFolderException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FolderReaderImpl implements FolderReader {
    FolderRepository folderRepository;

    @Override
    public Folder read(User user, Long folderId) {
        Folder folder = folderRepository.findById(folderId).orElseThrow(ApiFolderException::FOLDER_NOT_FOUND);
        if (ObjectUtils.notEqual(folder.getUser(), user)) {
            throw ApiFolderException.FOLDER_ACCESS_DENIED();
        }
        return folder;
    }
}
