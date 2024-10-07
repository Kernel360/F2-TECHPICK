package kernel360.techpick.feature.structure.service.model;

import java.util.Objects;

import org.springframework.stereotype.Component;

import kernel360.techpick.core.model.pick.Pick;
import kernel360.techpick.feature.folder.model.FolderProvider;
import kernel360.techpick.feature.pick.model.PickProvider;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DefaultNameProvider implements NameProvider {

    private final FolderProvider folderProvider;
    private final PickProvider pickProvider;

    // Pick은 커스텀 제목이 있으면 그걸 가져오고, 없으면 link의 제목으로 가져온다.
    public String findPickNameById(Long id) {
        Pick pick = pickProvider.findById(id);

        if (Objects.isNull(pick.getCustomTitle())) {
            return pick.getLink().getTitle();
        }
        return pick.getCustomTitle();
    }

    // 폴더 이름은 그냥 그대로 가져온다.
    public String findFolderNameById(Long id) {
        return folderProvider.findById(id).getName();
    }
}
