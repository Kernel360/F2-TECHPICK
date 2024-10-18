package kernel360.techpick.feature.tag.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import kernel360.techpick.feature.tag.exception.ApiTagException;
import kernel360.techpick.core.model.tag.Tag;
import kernel360.techpick.feature.tag.service.dto.TagUpdateRequest;

/**
 * 명명 규칙: DB에서 캐싱하는 1급 컬렉션은, Proxy라는 이름을 사용합니다.
 * Tag 별로 검증 후 쿼리를 날리지 않고, 한번에 검증 하기 위한 프록시 컬렉션
 */
public class TagListProxy {

	private final Map<Long, Tag> tagMap;

	public static TagListProxy fromTagList(List<Tag> tagList) {

		Map<Long, Tag> tagMap = new HashMap<>();
		tagList.forEach(tag -> tagMap.put(tag.getId(), tag));
		return new TagListProxy(tagMap);
	}

	public void updateTag(TagUpdateRequest request) {

		Tag target = getTagById(request.tagId());
		target.updateTag(request.tagName(), request.tagOrder());
	}

	public void validateTags() throws ApiTagException {
		validateTagOrder();
		validateTagName();
	}

	public Collection<Tag> getTags() {
		return tagMap.values();
	}

	private TagListProxy(Map<Long, Tag> tagMap) {
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
