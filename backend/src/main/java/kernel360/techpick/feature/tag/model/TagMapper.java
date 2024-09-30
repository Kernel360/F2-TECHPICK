package kernel360.techpick.feature.tag.model;

import org.springframework.stereotype.Component;

import kernel360.techpick.core.exception.feature.user.ApiUserException;
import kernel360.techpick.core.model.tag.Tag;
import kernel360.techpick.core.model.user.User;
import kernel360.techpick.feature.tag.model.dto.TagCreateRequest;
import kernel360.techpick.feature.tag.model.dto.TagResponse;
import kernel360.techpick.feature.user.UserRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TagMapper {

	private final UserRepository userRepository;

	public Tag createTag(TagCreateRequest request, int order, Long userId) throws ApiUserException {

		User user = userRepository.findById(userId).orElseThrow(ApiUserException::USER_NOT_FOUND);
		return Tag.createTag(request.name(), order, user);
	}

	public TagResponse createTagResponse(Tag tag) {
		return new TagResponse(
			tag.getId(),
			tag.getName(),
			tag.getTagOrder(),
			tag.getUser().getId()
		);
	}
}
