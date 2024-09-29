package kernel360.techpick.feature.tag.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public List<Tag> findAllByUserId(Long userId) {
		return tagRepository.findAllByUserIdOrderByOrder(userId);
	}

	public Map<Long, Tag> getTagMapByUserId(Long userId) {

		Map<Long, Tag> tagMap = new HashMap<>();
		tagRepository.findAllByUserId(userId)
			.forEach(tag -> tagMap.put(tag.getId(), tag));
		return tagMap;
	}

	public boolean existsByUserIdAndName(Long id, String name) throws ApiTagException {
		return tagRepository.existsByUserIdAndName(id, name);
	}

	public void deleteById(Long id) throws ApiTagException {
		tagRepository.deleteById(id);
	}

	public int getLastOrderByUserId(Long userId) {

		var tag = tagRepository.findFirstByUserIdOrderByOrderDesc(userId);

		// 순서는 0부터 시작
		return tag.map(value -> value.getOrder() + 1).orElseGet(() -> 0);
	}

}
