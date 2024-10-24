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
import kernel360.techpick.feature.infrastructure.tag.TagAdaptor;
import kernel360.techpick.feature.infrastructure.user.UserAdaptor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

	private final TagAdaptor tagAdaptor;
	private final TagMapper tagMapper;
	private final PickAdaptor pickAdaptor;
	private final UserAdaptor userAdaptor;

	@Override
	@Transactional(readOnly = true)
	public TagResult getTag(TagCommand.Read command) {
		Tag tag = tagAdaptor.getTag(command.userId(), command.tagId());
		return tagMapper.toResult(tag);
	}

	@Override
	@Transactional(readOnly = true)
	public List<TagResult> getUserTagList(Long userId) {
		List<Tag> tagList = tagAdaptor.getTagList(userId);
		return tagList.stream().map(tagMapper::toResult).toList();
	}

	@Override
	@Transactional
	public TagResult saveTag(TagCommand.Create command) {
		User user = userAdaptor.getUser(command.userId());
		Tag tag = tagMapper.toEntity(command, user);
		tagAdaptor.saveTag(tag);
		return tagMapper.toResult(tag);
	}

	@Override
	@Transactional
	public TagResult updateTag(TagCommand.Update command) {
		Tag tag = tagAdaptor.getTag(command.userId(), command.tagId());
		tag.updateTagName(command.name());
		tag.updateColorNumber(command.colorNumber());
		return tagMapper.toResult(tag);
	}

	@Override
	@Transactional
	public void moveUserTag(TagCommand.Move command) {
		User user = userAdaptor.getUser(command.userId());
		List<Long> userTagOrderList = user.getTagOrderList();

		userTagOrderList.remove(command.tagId());
		userTagOrderList.add(command.orderIdx(), command.tagId());
		user.updateTagOrderList(userTagOrderList);
	}

	@Override
	@Transactional
	public void deleteTag(TagCommand.Delete command) {
		tagAdaptor.deleteTag(command.tagId(), command.userId());
		pickAdaptor.detachTagFromEveryPick(command.tagId());
	}
}
