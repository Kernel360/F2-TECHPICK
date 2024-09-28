package kernel360.techpick.feature.tag.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kernel360.techpick.core.exception.feature.tag.ApiTagException;
import kernel360.techpick.core.exception.feature.user.ApiUserException;
import kernel360.techpick.core.model.tag.Tag;
import kernel360.techpick.feature.pick.repository.PickTagRepository;
import kernel360.techpick.feature.tag.model.dto.TagCreateRequest;
import kernel360.techpick.feature.tag.model.dto.TagResponse;
import kernel360.techpick.feature.tag.model.dto.TagUpdateRequest;
import kernel360.techpick.feature.tag.model.mapper.TagMapper;
import kernel360.techpick.feature.tag.model.provider.TagProvider;
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

		int lastOrder = tagProvider.getLastOrderByUSerId(userId);
		Tag tag = tagProvider.save(tagMapper.createTag(request, lastOrder, userId));

		return tagMapper.createTagResponse(tag);
	}

	@Transactional(readOnly = true)
	public List<TagResponse> getTagListByUser(Long userId) {

		List<Tag> tagList = tagProvider.findAllByUserId(userId);

		return tagList.stream()
			.map(tagMapper::createTagResponse)
			.toList();
	}

	@Transactional
	public List<TagResponse> updateTagList(Long userId, List<TagUpdateRequest> tagUpdateRequests) throws
		ApiTagException {

		List<Tag> targetTagList = new ArrayList<>();
		for (var req : tagUpdateRequests) {
			Tag targetTag = tagProvider.findById(req.id());
			validateTagAccess(userId, targetTag);
			tagMapper.updateTag(req, targetTag);
			targetTagList.add(targetTag);
		}

		return tagProvider.saveAll(targetTagList)
			.stream()
			.map(tagMapper::createTagResponse)
			.toList();
	}

	@Transactional
	public void deleteById(Long userId, Long tagId) throws ApiTagException {

		Tag targetTag = tagProvider.findById(tagId);
		validateTagAccess(userId, targetTag);
		// 해당 태그를 등록한 픽에서 해당 태그를 모두 삭제
		pickTagRepository.deleteById(tagId);
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
