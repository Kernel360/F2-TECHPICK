package kernel360.techpick.feature.domain.tag.service;

import java.util.List;

import kernel360.techpick.feature.domain.tag.dto.TagCommand;
import kernel360.techpick.feature.domain.tag.dto.TagResult;

public interface TagService {

	TagResult readTag(TagCommand.Read command);

	List<TagResult> readUserTagList();

	TagResult createTag(TagCommand.Create command);

	TagResult updateTag(TagCommand.Update command);

	void moveUserTag(TagCommand.Move command);

	void deleteTag(TagCommand.Delete command);
}
