package kernel360.techpick.feature.infrastructure.tag;

import java.util.List;

import kernel360.techpick.core.model.tag.Tag;

public interface TagAdaptor {

	// Read
	Tag getTag(Long userId, Long tagId);

	List<Tag> getTagList(Long userId);

	boolean checkDuplicateTagName(Long userId, String name);

	// Write
	Tag saveTag(Tag tag);

	void deleteTag(Long tagId, Long userId);
}
