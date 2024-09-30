package kernel360.techpick.feature.tag.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import kernel360.techpick.core.exception.feature.tag.ApiTagException;
import kernel360.techpick.core.model.tag.Tag;
import kernel360.techpick.feature.tag.model.dto.TagUpdateRequest;

public class TagUpdater {

	private final Map<Long, Tag> tagMap;

	public static TagUpdater fromTagList(List<Tag> tagList) {

		Map<Long, Tag> tagMap = new HashMap<>();
		tagList.forEach(tag -> tagMap.put(tag.getId(), tag));
		return new TagUpdater(tagMap);
	}

	public void updateTag(TagUpdateRequest request) {

		Tag target = getTagById(request.id());
		target.updateTag(request.name(), request.order());
	}

	public void validateUpdateTag() throws ApiTagException {
		validateTagOrder();
		validateTagName();
	}

	public Collection<Tag> getTags() {

		return tagMap.values();
	}

	private TagUpdater(Map<Long, Tag> tagMap) {

		this.tagMap = tagMap;
	}

	private Tag getTagById(Long tagId) {

		if (tagMap.containsKey(tagId)) {
			return tagMap.get(tagId);
		}
		throw ApiTagException.UNAUTHORIZED_TAG_ACCESS();
	}

	private void validateTagOrder() throws ApiTagException {

		Set<Integer> orderSet = new HashSet<>();

		for (Tag tag : tagMap.values()) {
			// 중복되거나 음수면 유효하지 않은 tag order
			if (!orderSet.add(tag.getTagOrder()) || tag.getTagOrder() < 0) {
				throw ApiTagException.TAG_INVALID_ORDER();
			}
		}
	}

	private void validateTagName() throws ApiTagException {
		Set<String> nameSet = new HashSet<>();

		for (Tag tag : tagMap.values()) {
			if (!nameSet.add(tag.getName())) {
				throw ApiTagException.TAG_INVALID_NAME();
			}
		}
	}

}
