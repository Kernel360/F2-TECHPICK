package kernel360.techpick.feature.tag.model;

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

	public List<Tag> saveAll(List<Tag> tags) {
		return tagRepository.saveAll(tags);
	}

	public Tag findById(Long id) throws ApiTagException {
		return tagRepository.findById(id).orElseThrow(ApiTagException::TAG_NOT_FOUND);
	}

	public Tag findByUserIdAndName(Long userId, String name) throws ApiTagException {
		return tagRepository.findByUser_IdAndName(userId, name).orElseThrow(ApiTagException::TAG_NOT_FOUND);
	}

	public List<Tag> findAllByUserId(Long userId) throws ApiTagException {
		return tagRepository.findAllByUser_IdOrderByOrder(userId);
	}

	public boolean existsByUserIdAndName(Long id, String name) throws ApiTagException {
		return tagRepository.existsByUser_IdAndName(id, name);
	}

	public void deleteById(Long id) throws ApiTagException {
		tagRepository.deleteById(id);
	}

	public int getLastOrderByUSerId(Long userId) {

		var tag = tagRepository.findFirstByUser_IdOrderByOrderDesc(userId);

		// 순서는 1부터 시작
		return tag.map(value -> value.getOrder() + 1).orElse(1);
	}

}
