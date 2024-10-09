package kernel360.techpick.feature.pick.model;

import org.springframework.stereotype.Component;

import kernel360.techpick.core.model.tag.Tag;
import kernel360.techpick.feature.pick.repository.PickTagRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PickTagProvider {

    private final PickTagRepository pickTagRepository;

    public void deletePickTagRelationByTag(Tag Tag) {
        pickTagRepository.deleteByTag(Tag);
    }
}
