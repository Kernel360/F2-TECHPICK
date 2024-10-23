package kernel360.techpick.feature.domain.tag.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kernel360.techpick.core.model.tag.Tag;
import kernel360.techpick.core.model.user.User;
import kernel360.techpick.feature.domain.tag.dto.TagCommand;
import kernel360.techpick.feature.domain.tag.dto.TagMapper;
import kernel360.techpick.feature.domain.tag.dto.TagResult;
import kernel360.techpick.feature.infrastructure.pick.PickAdaptor;
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
	private final PickAdaptor pickAdaptor;
	private final UserReader userReader;

	@Override
	@Transactional(readOnly = true)
	public TagResult readTag(TagCommand.Read command) {
		Long userId = userReader.readCurrentUserId();
		Tag tag = tagReader.readTag(userId, command.tagId());
		return tagMapper.toResult(tag);
	}

	@Override
	@Transactional(readOnly = true)
	public List<TagResult> readUserTagList() {
		Long userId = userReader.readCurrentUserId();
		List<Tag> tagList = tagReader.readTagList(userId);
		return tagList.stream().map(tagMapper::toResult).toList();
	}

	@Override
	@Transactional
	public TagResult createTag(TagCommand.Create command) {
		User user = userReader.readCurrentUser();
		Tag tag = tagMapper.toEntity(command, user);
		tagWriter.writeTag(tag);
		return tagMapper.toResult(tag);
	}

	@Override
	@Transactional
	public TagResult updateTag(TagCommand.Update command) {
		Long userId = userReader.readCurrentUserId();
		Tag tag = tagReader.readTag(userId, command.tagId());
		tag.updateTagName(command.name());
		tag.updateColorNumber(command.colorNumber());
		return tagMapper.toResult(tag);
	}

	@Override
	@Transactional
	public void moveUserTag(TagCommand.Move command) {
		User user = userReader.readCurrentUser();
		List<Long> userTagOrderList = user.getTagOrderList();

		userTagOrderList.remove(command.tagId());
		userTagOrderList.add(command.orderIdx(), command.tagId());
		user.updateTagOrderList(userTagOrderList);
	}

	@Override
	@Transactional
	public void deleteTag(TagCommand.Delete command) {
		tagWriter.removeTag(command.tagId());
		pickAdaptor.detachTagFromEveryPick(command.tagId());
	}
}
