package kernel360.techpick.feature.domain.tag.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kernel360.techpick.core.model.tag.Tag;
import kernel360.techpick.core.model.user.User;
import kernel360.techpick.feature.domain.tag.dto.TagCommand;
import kernel360.techpick.feature.domain.tag.dto.TagMapper;
import kernel360.techpick.feature.domain.tag.dto.TagResult;
import kernel360.techpick.feature.domain.tag.exception.ApiTagException;
import kernel360.techpick.feature.infrastructure.tag.reader.TagReader;
import kernel360.techpick.feature.infrastructure.tag.writer.TagWriter;
import kernel360.techpick.feature.infrastructure.user.reader.UserReader;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

	private final TagReader tagReader;
	private final TagWriter tagWriter;
	private final TagMapper tagMapper;
	private final UserReader userReader;

	@Override
	@Transactional(readOnly = true)
	public TagResult.Read readTag(TagCommand.Read command) {
		Long userId = userReader.readCurrentUserId();
		Tag tag = tagReader.readTag(userId, command.tagId());
		return tagMapper.toRead(tag);
	}

	@Override
	@Transactional(readOnly = true)
	public List<TagResult.Read> readUserTagList() {
		Long userId = userReader.readCurrentUserId();
		List<Tag> tagList = tagReader.readTagList(userId);
		return tagList.stream()
			.map(tagMapper::toRead)
			.toList();
	}

	@Override
	@Transactional
	public TagResult.Create createTag(TagCommand.Create command) {
		validateDuplicateTagName(command.name());

		User user = userReader.readCurrentUser();
		Tag tag = tagMapper.createToEntity(command, user);
		tagWriter.writeTag(tag);
		return tagMapper.toCreate(tag);
	}

	@Override
	@Transactional
	public TagResult.Update updateTag(TagCommand.Update command) {
		Long userId = userReader.readCurrentUserId();
		Tag tag = tagReader.readTag(userId, command.tagId());
		tag.updateTagName(command.name());
		tag.updateColorNumber(command.colorNumber());
		return tagMapper.toUpdate(tag);
	}

	@Override
	@Transactional
	public void deleteTag(TagCommand.Delete command) {
		tagWriter.removeTag(command.tagId());
	}

	private void validateDuplicateTagName(String name) throws ApiTagException {
		Long userId = userReader.readCurrentUserId();
		if (tagReader.checkDuplicateTagName(userId, name)) {
			throw ApiTagException.TAG_ALREADY_EXIST();
		}
	}
}
