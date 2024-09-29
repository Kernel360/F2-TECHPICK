package kernel360.techpick.feature.tag.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kernel360.techpick.core.exception.feature.tag.ApiTagException;
import kernel360.techpick.core.exception.feature.user.ApiUserException;
import kernel360.techpick.core.model.tag.Tag;
import kernel360.techpick.feature.pick.repository.PickTagRepository;
import kernel360.techpick.feature.tag.model.TagMapper;
import kernel360.techpick.feature.tag.model.TagProvider;
import kernel360.techpick.feature.tag.model.dto.TagCreateRequest;
import kernel360.techpick.feature.tag.model.dto.TagResponse;
import kernel360.techpick.feature.tag.model.dto.TagUpdateRequest;
import kernel360.techpick.feature.tag.validator.TagValidator;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TagService {

	private final TagMapper tagMapper;
	private final TagProvider tagProvider;
	private final TagValidator tagValidator;
	private final PickTagRepository pickTagRepository;

	@Transactional
	public TagResponse createTag(Long userId, TagCreateRequest request) throws ApiTagException, ApiUserException {

		tagValidator.validateTagNameExists(userId, request.name());

		int lastOrder = tagProvider.getLastOrderByUserId(userId);
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

		// immutable list로 들어올 경우 sort가 불가능해 ArrayList로 변환
		// 항상 mutable list라는것이 보장된다면 삭제해주세요..
		List<TagUpdateRequest> mutableTagUpdateRequests = new ArrayList<>(tagUpdateRequests);
		mutableTagUpdateRequests.sort(Comparator.comparingLong(TagUpdateRequest::id));
		
		List<Tag> userTagList = tagProvider.findAllByUserIdOrderByTagId(userId);

		int idx = 0;
		for (var req : mutableTagUpdateRequests) {
			idx = tagValidator.findUpdateTagIdx(idx, req, userTagList);
			tagMapper.updateTag(req, userTagList.get(idx));
			idx++;
		}

		return tagProvider.saveAll(userTagList)
			.stream()
			.map(tagMapper::createTagResponse)
			.toList();
	}

	@Transactional
	public void deleteById(Long userId, Long tagId) throws ApiTagException {

		Tag targetTag = tagProvider.findById(tagId);
		tagValidator.validateTagAccess(userId, targetTag);
		// 해당 태그를 등록한 픽에서 해당 태그를 모두 삭제
		// TODO: PickTagProvider 구현되면 PickTagProvider를 의존하도록 리팩토링 필요
		pickTagRepository.deleteByTag_Id(tagId);
		tagProvider.deleteById(tagId);
	}

}
