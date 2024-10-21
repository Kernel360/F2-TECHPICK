package kernel360.techpick.feature.infrastructure.tag.writer;

import kernel360.techpick.core.model.tag.Tag;

public interface TagWriter {

	Tag writeTag(Tag tag);

	void removeTag(Long tagId);
}
