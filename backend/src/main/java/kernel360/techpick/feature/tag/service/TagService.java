package kernel360.techpick.feature.tag.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kernel360.techpick.core.model.tag.Tag;
import kernel360.techpick.core.model.user.User;
import kernel360.techpick.feature.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TagService {

	private final TagRepository tagRepository;

	public List<Tag> getTagByUser(User user) {
		return tagRepository.findAllByUser(user);
	}

	/**
	 * - 한 사용자의 모든 태그명은 유일해야 하며, 어길 경우 예외를 발생시킵니다.
	 */
	public void createTagByName(String name) {}
}
