package kernel360.techpick.feature.domain.tag.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kernel360.techpick.core.model.tag.Tag;
import kernel360.techpick.feature.domain.tag.dto.TagCommand;
import kernel360.techpick.feature.domain.tag.dto.TagMapper;
import kernel360.techpick.feature.domain.tag.dto.TagResult;
import kernel360.techpick.feature.domain.tag.exception.ApiTagException;
import kernel360.techpick.feature.infrastructure.tag.TagAdaptor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

	private final TagAdaptor tagAdaptor;
	private final TagMapper tagMapper;

	@Override
	@Transactional(readOnly = true)
	public TagResult getTag(Long userId, TagCommand.Read command) throws ApiTagException {
		Tag tag = tagAdaptor.getTag(command.tagId());
		if (!userId.equals(tag.getUser().getId())) {
			throw ApiTagException.UNAUTHORIZED_TAG_ACCESS();
		}
		return tagMapper.toResult(tag);
	}

	@Override
	@Transactional(readOnly = true)
	public List<TagResult> getUserTagList(Long userId) {
		return tagAdaptor.getTagList(userId).stream()
			.map(tagMapper::toResult).toList();
	}

	@Override
	@Transactional
	public TagResult saveTag(Long userId, TagCommand.Create command) {
		if (tagAdaptor.checkDuplicateTagName(userId, command.name())) {
			throw ApiTagException.TAG_ALREADY_EXIST();
		}
		return tagMapper.toResult(tagAdaptor.saveTag(userId, command));
	}

	@Override
	@Transactional
	public TagResult updateTag(Long userId, TagCommand.Update command) {
		Tag tag = tagAdaptor.getTag(command.tagId());
		if (!userId.equals(tag.getUser().getId())) {
			throw ApiTagException.UNAUTHORIZED_TAG_ACCESS();
		}
		if (tagAdaptor.checkDuplicateTagName(userId, command.name())) {
			throw ApiTagException.TAG_ALREADY_EXIST();
		}
		return tagMapper.toResult(tagAdaptor.updateTag(command));
	}

	@Override
	@Transactional
	public void moveUserTag(Long userId, TagCommand.Move command) {
		Tag tag = tagAdaptor.getTag(command.tagId());
		if (!userId.equals(tag.getUser().getId())) {
			throw ApiTagException.UNAUTHORIZED_TAG_ACCESS();
		}
		tagAdaptor.moveTag(userId, command);
	}

	@Override
	@Transactional
	public void deleteTag(Long userId, TagCommand.Delete command) {
		Tag tag = tagAdaptor.getTag(command.tagId());
		if (!userId.equals(tag.getUser().getId())) {
			throw ApiTagException.UNAUTHORIZED_TAG_ACCESS();
		}
		tagAdaptor.deleteTag(userId, command);
	}
}
