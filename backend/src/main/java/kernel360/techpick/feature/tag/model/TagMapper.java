package kernel360.techpick.feature.tag.model;

import kernel360.techpick.core.model.tag.Tag;
import kernel360.techpick.core.model.user.User;
import kernel360.techpick.feature.tag.service.dto.TagCreateRequest;
import kernel360.techpick.feature.tag.service.dto.TagResponse;

public class TagMapper {

	public static Tag toTagEntity(TagCreateRequest request, int order, User user) {
		return Tag.createTag(request.tagName(), order, user);
	}

	public static TagResponse toTagResponse(Tag tag) {
		return new TagResponse(
			tag.getId(),
			tag.getName(),
			tag.getTagOrder(),
			tag.getUser().getId()
		);
	}
}
