package kernel360.techpick.feature.tag.model;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

import kernel360.techpick.core.model.pick.Pick;
import kernel360.techpick.core.model.pick.PickTag;
import kernel360.techpick.core.model.tag.Tag;
import kernel360.techpick.core.model.user.User;
import kernel360.techpick.feature.pick.model.PickTagProvider;
import kernel360.techpick.feature.tag.service.dto.TagCreateRequest;
import kernel360.techpick.feature.tag.service.dto.TagResponse;

@Component
public class TagMapper {

	public Tag toTagEntity(TagCreateRequest request, Integer order, User user) {
		return Tag.createTag(request.tagName(), order, request.colorNumber(), user);
	}

	public TagResponse toTagResponse(Tag tag) {
		return new TagResponse(
			tag.getId(),
			tag.getName(),
			tag.getTagOrder(),
			tag.getColorNumber(),
			tag.getUser().getId()
		);
	}

	public List<TagResponse> toTagResponse(List<PickTag> pickTags) {
		return pickTags.stream()
			.map(pickTag -> {
				Tag tag = pickTag.getTag();
				return new TagResponse(tag.getId(), tag.getName(), tag.getTagOrder(), tag.getColorNumber(),
					tag.getUser().getId());
			})
			.toList();
	}

	public List<TagResponse> toTagResponseList(Pick pick, List<Long> tagIdList, List<PickTag> pickTagList,
		PickTagProvider pickTagProvider, TagProvider tagProvider) {

		return tagIdList.stream()
			.map(tagId -> createTagResponse(pick, tagId, pickTagList, tagProvider, pickTagProvider))
			.toList();
	}

	// Tag 유무 확인 후 없으면 생성, 있으면 기존 것 반환
	private TagResponse createTagResponse(Pick pick, Long tagId, List<PickTag> pickTagList, TagProvider tagProvider,
		PickTagProvider pickTagProvider) {
		// 기존 태그가 존재하는지 확인
		PickTag existingPickTag = pickTagList.stream()
			.filter(pickTag -> pickTag.getTag().getId().equals(tagId))
			.findFirst()
			.orElse(null);

		if (Objects.isNull(existingPickTag)) {
			Tag tag = tagProvider.findById(tagId);
			pickTagProvider.save(PickTag.create(pick, tag));
			return toTagResponse(tag);
		}

		return toTagResponse(existingPickTag.getTag());
	}
}
