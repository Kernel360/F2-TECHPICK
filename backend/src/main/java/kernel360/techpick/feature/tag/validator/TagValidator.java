package kernel360.techpick.feature.tag.validator;

import java.util.Objects;

import org.springframework.stereotype.Component;

import kernel360.techpick.core.exception.feature.tag.ApiTagException;
import kernel360.techpick.core.model.tag.Tag;
import kernel360.techpick.feature.tag.model.TagProvider;
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

}

