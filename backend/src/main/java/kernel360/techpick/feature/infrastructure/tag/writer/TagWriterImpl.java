package kernel360.techpick.feature.infrastructure.tag.writer;

import org.springframework.stereotype.Component;

import kernel360.techpick.core.model.tag.Tag;
import kernel360.techpick.core.model.tag.TagRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TagWriterImpl implements TagWriter {

	private final TagRepository tagRepository;

	@Override
	public Tag writeTag(Tag tag) {
		return tagRepository.save(tag);
	}

	@Override
	public void removeTag(Long tagId) {
		tagRepository.deleteById(tagId);
	}
}
