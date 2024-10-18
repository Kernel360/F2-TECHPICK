package kernel360.techpick.feature.tag.model;

import java.util.List;

import org.springframework.stereotype.Component;

import kernel360.techpick.core.model.user.User;
import kernel360.techpick.feature.tag.exception.ApiTagException;
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

	public List<Tag> saveAll(TagListProxy tagListProxy) {
		return tagRepository.saveAll(tagListProxy.getTags());
	}

	public Tag findById(Long id) throws ApiTagException {
		return tagRepository.findById(id).orElseThrow(ApiTagException::TAG_NOT_FOUND);
	}

	public List<Tag> findAllByUserOrderByTagOrder(User user) {
		return tagRepository.findAllByUserOrderByTagOrder(user);
	}

	public boolean isUserAlreadyHasTagOfName(User user, String name) throws ApiTagException {
		return tagRepository.existsByUserAndName(user, name);
	}

	public void deleteById(Long id) throws ApiTagException {
		tagRepository.deleteById(id);
	}

	public int getNextOrderByUserId(User user) {
		// 순서는 0부터 시작
		return tagRepository.findFirstByUserOrderByTagOrderDesc(user)
							.map((tag) -> tag.getTagOrder() + 1)
							.orElse(0);
	}

	public TagListProxy getUserTagListProxy(User user) {
		return TagListProxy.fromTagList(tagRepository.findAllByUser(user));
	}
}
