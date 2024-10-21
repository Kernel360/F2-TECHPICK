package kernel360.techpick.feature.infrastructure.tag.reader;

import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import kernel360.techpick.core.model.tag.Tag;
import kernel360.techpick.core.model.tag.TagRepository;
import kernel360.techpick.feature.domain.tag.exception.ApiTagException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TagReaderImpl implements TagReader {

	private TagRepository tagRepository;

	@Override
	public Tag readTag(Long userId, Long tagId) {
		Tag tag = tagRepository.findById(tagId).orElseThrow(ApiTagException::TAG_NOT_FOUND);

		if (ObjectUtils.notEqual(tag.getUser().getId(), userId)) {
			ApiTagException.UNAUTHORIZED_TAG_ACCESS();
		}

		return tag;
	}

	@Override
	public List<Tag> readTagList(Long userId) {
		return tagRepository.findAllByUserId(userId);
	}

	@Override
	public boolean checkDuplicateTagName(Long userId, String name) {
		return tagRepository.existsByUserIdAndName(userId, name);
	}
}
