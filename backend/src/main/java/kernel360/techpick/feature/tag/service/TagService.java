package kernel360.techpick.feature.tag.service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kernel360.techpick.core.model.user.User;
import kernel360.techpick.feature.pick.service.PickService;
import kernel360.techpick.feature.tag.exception.ApiTagException;
import kernel360.techpick.feature.user.exception.ApiUserException;
import kernel360.techpick.core.model.tag.Tag;
import kernel360.techpick.feature.tag.model.TagMapper;
import kernel360.techpick.feature.tag.model.TagProvider;
import kernel360.techpick.feature.tag.model.TagListProxy;
import kernel360.techpick.feature.tag.service.dto.TagCreateRequest;
import kernel360.techpick.feature.tag.service.dto.TagResponse;
import kernel360.techpick.feature.tag.service.dto.TagUpdateRequest;
import kernel360.techpick.feature.user.service.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TagService {

	private final UserService userService;
	private final PickService pickService;
	private final TagProvider tagProvider;

	@Transactional
	public TagResponse createTag(User user, TagCreateRequest request) throws ApiTagException, ApiUserException {

		validateTagNameExists(user, request.tagName());

		int lastOrder = tagProvider.getNextOrderByUserId(user);
		Tag tag = tagProvider.save(TagMapper.toTagEntity(request, lastOrder, user));

		return TagMapper.toTagResponse(tag);
	}

	@Transactional(readOnly = true)
	public List<TagResponse> getTagList(User user) {

		List<Tag> tagList = tagProvider.findAllByUserOrderByTagOrder(user);

		return tagList.stream()
			.map(TagMapper::toTagResponse)
			.toList();
	}

	@Transactional
	public List<TagResponse> updateTagList(
		User user,
		List<TagUpdateRequest> tagUpdateRequests
	) throws ApiTagException {

		TagListProxy tagListProxy = tagProvider.getUserTagListProxy(user);

		for (var req : tagUpdateRequests) {
			tagListProxy.updateTag(req);
		}
		tagListProxy.validateTags();

		return tagProvider.saveAll(tagListProxy)
			.stream()
			.map(TagMapper::toTagResponse)
			.sorted(Comparator.comparingInt(TagResponse::tagOrder))
			.toList();
	}

	@Transactional
	public void deleteByTagId(User user, Long tagId) throws ApiTagException {

		Tag targetTag = tagProvider.findById(tagId);
		validateTagAccess(user, targetTag);
		// 해당 태그를 등록한 픽에서 해당 태그를 모두 삭제
		// TODO: PickTagProvider 구현되면 PickTagProvider를 의존하도록 리팩토링 필요
		pickService.releaseTagFromEveryPick(targetTag);
		tagProvider.deleteById(tagId);
	}

	private void validateTagAccess(User user, Tag tag) throws ApiTagException {

		if (!Objects.equals(user, tag.getUser())) {
			throw ApiTagException.UNAUTHORIZED_TAG_ACCESS();
		}
	}

	private void validateTagNameExists(User user, String name) throws ApiTagException {

		if (tagProvider.isUserAlreadyHasTagOfName(user, name)) {
			throw ApiTagException.TAG_ALREADY_EXIST();
		}
	}

}
