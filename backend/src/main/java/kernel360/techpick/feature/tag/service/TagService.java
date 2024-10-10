package kernel360.techpick.feature.tag.service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kernel360.techpick.feature.tag.exception.ApiTagException;
import kernel360.techpick.feature.user.exception.ApiUserException;
import kernel360.techpick.core.model.tag.Tag;
import kernel360.techpick.feature.pick.repository.PickTagRepository;
import kernel360.techpick.feature.tag.model.TagMapper;
import kernel360.techpick.feature.tag.model.TagProvider;
import kernel360.techpick.feature.tag.model.TagUpdater;
import kernel360.techpick.feature.tag.service.dto.TagCreateRequest;
import kernel360.techpick.feature.tag.service.dto.TagResponse;
import kernel360.techpick.feature.tag.service.dto.TagUpdateRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TagService {

	private final TagMapper tagMapper;
	private final TagProvider tagProvider;
	private final PickTagRepository pickTagRepository;

	@Transactional
	public TagResponse createTag(Long userId, TagCreateRequest request) throws ApiTagException, ApiUserException {

		validateTagNameExists(userId, request.name());

		int lastOrder = tagProvider.getNextOrderByUserId(userId);
		Tag tag = tagProvider.save(tagMapper.createTag(request, lastOrder, userId));

		return tagMapper.createTagResponse(tag);
	}

	@Transactional(readOnly = true)
	public List<TagResponse> getTagListByUser(Long userId) {

		List<Tag> tagList = tagProvider.findAllByUserIdOrderByTagOrder(userId);

		return tagList.stream()
			.map(tagMapper::createTagResponse)
			.toList();
	}

	@Transactional
	public List<TagResponse> updateTagList(Long userId, List<TagUpdateRequest> tagUpdateRequests) throws
		ApiTagException {

		TagUpdater tagUpdater = tagProvider.getUserTag(userId);

		for (var req : tagUpdateRequests) {
			tagUpdater.updateTag(req);
		}
		tagUpdater.validateUpdateTag();

		return tagProvider.saveAll(tagUpdater.getTags())
			.stream()
			.map(tagMapper::createTagResponse)
			.sorted(Comparator.comparingInt(TagResponse::tagOrder))
			.toList();
	}

	@Transactional
	public void deleteById(Long userId, Long tagId) throws ApiTagException {

		Tag targetTag = tagProvider.findById(tagId);
		validateTagAccess(userId, targetTag);
		// 해당 태그를 등록한 픽에서 해당 태그를 모두 삭제
		// TODO: PickTagProvider 구현되면 PickTagProvider를 의존하도록 리팩토링 필요
		pickTagRepository.deleteByTagId(tagId);
		tagProvider.deleteById(tagId);
	}

	private void validateTagAccess(Long userId, Tag tag) throws ApiTagException {

		if (tag == null || !Objects.equals(userId, tag.getUser().getId())) {
			throw ApiTagException.UNAUTHORIZED_TAG_ACCESS();
		}
	}

	private void validateTagNameExists(Long userId, String name) throws ApiTagException {

		if (tagProvider.existsByUserIdAndName(userId, name)) {
			throw ApiTagException.TAG_ALREADY_EXIST();
		}
	}

}
