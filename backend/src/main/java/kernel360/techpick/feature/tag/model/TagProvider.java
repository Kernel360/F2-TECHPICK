package kernel360.techpick.feature.tag.model;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import kernel360.techpick.core.exception.feature.tag.ApiTagException;
import kernel360.techpick.core.model.tag.Tag;
import kernel360.techpick.feature.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TagProvider {

	private final TagRepository tagRepository;

	public Tag save(Tag tag) {
		return tagRepository.save(tag);
	}

	public List<Tag> saveAll(Collection<Tag> tags) {
		return tagRepository.saveAll(tags);
	}

	public Tag findById(Long id) throws ApiTagException {
		return tagRepository.findById(id).orElseThrow(ApiTagException::TAG_NOT_FOUND);
	}

	public List<Tag> findAllByUserIdOrderByTagOrder(Long userId) {
		return tagRepository.findAllByUserIdOrderByTagOrder(userId);
	}

	public boolean existsByUserIdAndName(Long id, String name) throws ApiTagException {
		return tagRepository.existsByUserIdAndName(id, name);
	}

	public void deleteById(Long id) throws ApiTagException {
		tagRepository.deleteById(id);
	}

	public int getLastOrderByUserId(Long userId) {

		var tag = tagRepository.findFirstByUserIdOrderByTagOrderDesc(userId);

		// 순서는 0부터 시작
		return tag.map(value -> value.getTagOrder() + 1).orElseGet(() -> 0);
	}

	public TagUpdater getUserTag(Long userId) {
		return TagUpdater.fromTagList(tagRepository.findAllByUserId(userId));
	}

}
