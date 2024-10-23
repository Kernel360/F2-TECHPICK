package kernel360.techpick.feature.infrastructure.tag;

import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import kernel360.techpick.core.model.tag.Tag;
import kernel360.techpick.core.model.tag.TagRepository;
import kernel360.techpick.feature.domain.tag.exception.ApiTagException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TagAdaptorImpl implements TagAdaptor {

	private final TagRepository tagRepository;

	@Override
	public Tag getTag(Long userId, Long tagId) {
		Tag tag = tagRepository.findById(tagId).orElseThrow(ApiTagException::TAG_NOT_FOUND);

		if (ObjectUtils.notEqual(tag.getUser().getId(), userId)) {
			throw ApiTagException.UNAUTHORIZED_TAG_ACCESS();
		}

		return tag;
	}

	@Override
	public List<Tag> getTagList(Long userId) {
		return tagRepository.findAllByUserId(userId);
	}

	@Override
	public boolean checkDuplicateTagName(Long userId, String name) {
		return tagRepository.existsByUserIdAndName(userId, name);
	}

	@Override
	public Tag saveTag(Tag tag) {
		validateDuplicateTagName(tag.getUser().getId(), tag.getName());
		return tagRepository.save(tag);
	}

	@Override
	public void deleteTag(Long tagId, Long userId) {
		tagRepository.deleteByIdAndUserId(tagId, userId);
	}

	private void validateDuplicateTagName(Long userId, String name) throws ApiTagException {
		if (tagRepository.existsByUserIdAndName(userId, name)) {
			throw ApiTagException.TAG_ALREADY_EXIST();
		}
	}
}
