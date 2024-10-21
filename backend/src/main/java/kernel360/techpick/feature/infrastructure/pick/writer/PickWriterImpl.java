package kernel360.techpick.feature.infrastructure.pick.writer;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kernel360.techpick.core.model.pick.Pick;
import kernel360.techpick.core.model.pick.PickRepository;
import kernel360.techpick.core.model.pick.PickTag;
import kernel360.techpick.core.model.pick.PickTagRepository;
import kernel360.techpick.core.model.tag.Tag;
import kernel360.techpick.core.model.tag.TagRepository;
import kernel360.techpick.feature.domain.pick.exception.ApiPickException;
import kernel360.techpick.feature.domain.tag.exception.ApiTagException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PickWriterImpl implements PickWriter {
	private final PickRepository pickRepository;
	private final PickTagRepository pickTagRepository;
	private final TagRepository tagRepository;

	@Override
	public Pick writePick(Pick pick) throws ApiPickException {
		pickRepository.findByUserAndLink(pick.getUser(), pick.getLink())
			.ifPresent((__) -> {
				throw ApiPickException.PICK_MUST_BE_UNIQUE_FOR_A_URL();
			});
		Pick savedPick = pickRepository.save(pick);

		for (Long tagId : pick.getTagOrder()) {
			Tag tag = tagRepository.findById(tagId).orElseThrow(ApiPickException::PICK_SET_WITH_UNEXISTING_TAG);
			pickTagRepository.save(PickTag.of(savedPick, tag));
		}
		return savedPick;
	}

	@Override
	public void removePick(Pick pick) {
		pickRepository.delete(pick);
		pickTagRepository.deleteByPick(pick);
	}

	@Override
	public void removePickTag(Long tagId) {
		pickTagRepository.deleteByTagId(tagId);
	}
}
