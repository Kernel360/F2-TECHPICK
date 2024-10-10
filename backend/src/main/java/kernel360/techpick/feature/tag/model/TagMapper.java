package kernel360.techpick.feature.tag.model;

import org.springframework.stereotype.Component;

import kernel360.techpick.core.model.tag.Tag;
import kernel360.techpick.core.model.user.User;
import kernel360.techpick.feature.tag.service.dto.TagCreateRequest;
import kernel360.techpick.feature.tag.service.dto.TagResponse;

@Component
public class TagMapper {

	public Tag toTagEntity(TagCreateRequest request, int order, User user) {
		return Tag.createTag(request.tagName(), order, user);
	}

	public TagResponse toTagResponse(Tag tag) {
		return new TagResponse(
			tag.getId(),
			tag.getName(),
			tag.getTagOrder(),
			tag.getUser().getId()
		);
	}
}
