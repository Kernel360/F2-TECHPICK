package kernel360.techpick.feature.domain.tag.service;

import java.util.List;

import kernel360.techpick.core.model.user.User;
import kernel360.techpick.feature.domain.tag.dto.TagCommand;
import kernel360.techpick.feature.domain.tag.dto.TagResult;

public interface TagService {

	TagResult.Read readTag(TagCommand.Read read);

	List<TagResult.Read> readUserTagList();

	TagResult.Create createTag(TagCommand.Create create);

	TagResult.Update updateTag(TagCommand.Update update);

	void deleteTag(TagCommand.Delete delete);
}
