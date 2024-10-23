package kernel360.techpick.feature.domain.tag.service;

import java.util.List;

import kernel360.techpick.feature.domain.tag.dto.TagCommand;
import kernel360.techpick.feature.domain.tag.dto.TagResult;

public interface TagService {

	TagResult getTag(Long userId, TagCommand.Read command);

	List<TagResult> getUserTagList(Long userId);

	TagResult saveTag(Long userId, TagCommand.Create command);

	TagResult updateTag(Long userId, TagCommand.Update command);

	void moveUserTag(Long userId, TagCommand.Move command);

	void deleteTag(Long userId, TagCommand.Delete command);
}
