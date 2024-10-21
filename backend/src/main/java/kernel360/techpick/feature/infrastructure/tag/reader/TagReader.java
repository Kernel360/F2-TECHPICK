package kernel360.techpick.feature.infrastructure.tag.reader;

import java.util.List;

import kernel360.techpick.core.model.tag.Tag;

public interface TagReader {

	Tag readTag(Long userId, Long tagId);

	List<Tag> readTagList(Long userId);

	boolean checkDuplicateTagName(Long userId, String name);
}
