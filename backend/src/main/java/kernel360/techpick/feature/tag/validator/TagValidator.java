package kernel360.techpick.feature.tag.validator;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

import kernel360.techpick.core.exception.feature.tag.ApiTagException;
import kernel360.techpick.core.model.tag.Tag;
import kernel360.techpick.feature.tag.model.TagProvider;
import kernel360.techpick.feature.tag.model.dto.TagUpdateRequest;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TagValidator {

	private final TagProvider tagProvider;

	public void validateTagAccess(Long userId, Tag tag) throws ApiTagException {

		if (tag == null || !Objects.equals(userId, tag.getUser().getId())) {
			throw ApiTagException.UNAUTHORIZED_TAG_ACCESS();
		}
	}

	public void validateTagNameExists(Long userId, String name) throws ApiTagException {

		if (tagProvider.existsByUserIdAndName(userId, name)) {
			throw ApiTagException.TAG_ALREADY_EXIST();
		}
	}

	// 업데이트 대상이 되는 태그의 userTagList에서의 index 반환
	// 만약 userTagList에 업데이트 하려는 태그가 존재하지 않으면,
	// 본인이 등록하지 않은 태그에 접근하려는 것이므로 ApiTagException.UNAUTHORIZED_TAG_ACCESS() 발생
	public int findUpdateTagIdx(int startIdx, TagUpdateRequest req, List<Tag> userTagList) throws ApiTagException {

		for (int i = startIdx; i < userTagList.size(); i++) {
			if (req.id().equals(userTagList.get(i).getId())) {
				return i;
			}
		}
		throw ApiTagException.UNAUTHORIZED_TAG_ACCESS();
	}

}

