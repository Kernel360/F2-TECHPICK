package kernel360.techpick.feature.pick.model;

import java.util.List;

import org.springframework.stereotype.Component;

import kernel360.techpick.core.model.pick.PickTag;
import kernel360.techpick.feature.pick.repository.PickTagRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PickTagProvider {

	private final PickTagRepository pickTagRepository;

	public List<PickTag> findAllPickTagByPickId(Long pickId) {
		return pickTagRepository.findAllByPickId(pickId);
	}

	public PickTag save(PickTag pickTag) {
		return pickTagRepository.save(pickTag);
	}

	public void deleteByPickId(Long pickId) {
		pickTagRepository.deleteByPickId(pickId);
	}

	public void deleteByPickIdAndTagId(Long pickId, Long tagId) {
		pickTagRepository.deleteByPickIdAndTagId(pickId, tagId);
	}

	public void deletePickTagRelationByTagId(Long tagId) {
		pickTagRepository.deleteByTagId(tagId);
	}
}
