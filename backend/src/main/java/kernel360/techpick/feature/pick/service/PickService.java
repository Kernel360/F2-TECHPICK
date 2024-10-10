package kernel360.techpick.feature.pick.service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kernel360.techpick.feature.pick.model.PickProvider;
import kernel360.techpick.feature.pick.repository.PickRepository;
import kernel360.techpick.feature.pick.service.dto.PickCreateRequest;
import kernel360.techpick.feature.pick.service.dto.PickIdDto;
import kernel360.techpick.feature.pick.service.dto.PickResponse;
import kernel360.techpick.feature.pick.service.dto.PickUpdateRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PickService {

	private final PickProvider pickProvider;

	// 픽 상세 조회
	public PickResponse getPickById(PickIdDto pickIdDto) {
		Pick pick = pickProvider.findById(pickIdDto.getPickId());

		// 본인 픽인지 검증 (pickId)
		pickValidator.validatePickAccess(pickIdDto.getUserId(), pick);

		List<PickTag> pickTags = pickTagProvider.findAllPickTagByPickId(pickIdDto.getPickId());
		LinkUrlResponse linkUrlResponse = linkMapper.createLinkUrlResponse(pick.getLink());
		List<TagResponse> tagResponseList = pickTagMapper.toTagResponse(pickTags);

		return pickMapper.toPickResponse(pick, tagResponseList, linkUrlResponse);
	}

	// 사용자의 픽 리스트 조회
	public List<PickResponse> getPickListByUserId(Long userId) {
		List<Pick> picks = pickProvider.findAllByUserId(userId);
		return pickMapper.toPickResponseList(picks, pickTagProvider, pickTagMapper, linkMapper);
	}

	// 폴더에 있는 픽 리스트 조회
	public List<PickResponse> getPickListByParentFolderId(Long userId, Long parentFolderId) {
		List<Pick> picks = pickProvider.findAllByParentFolderId(userId, parentFolderId);
		return pickMapper.toPickResponseList(picks, pickTagProvider, pickTagMapper, linkMapper);
	}

	// 미분류 폴더에 있는 픽 리스트 조회
	public List<PickResponse> getPickListByUnclassified(Long userId) {
		List<Pick> picks = pickProvider.findAllByUnclassified(userId);
		return pickMapper.toPickResponseList(picks, pickTagProvider, pickTagMapper, linkMapper);
	}

	// 픽 생성
	@Transactional
	public PickResponse createPick(Long userId, PickCreateRequest pickCreateRequest) {
		User user = userProvider.findById(userId);
		Folder folder = folderProvider.findUnclassified(userId);

		LinkRequest linkRequest = pickCreateRequest.linkRequest();
		Link link = linkService.saveOrUpdateLink(linkRequest);
		LinkUrlResponse linkUrlResponse = linkMapper.createLinkUrlResponse(link);

		Pick savedPick = pickProvider.save(pickMapper.toPickEntity(user, link, folder, pickCreateRequest));

		List<Long> tagIdList = pickCreateRequest.tagIdList();
		List<PickTag> pickTagList = pickTagProvider.findAllPickTagByPickId(savedPick.getId());
		List<TagResponse> tagResponseList = pickMapper.toTagResponseList(savedPick, tagIdList, pickTagList,
			pickTagProvider,
			tagProvider,
			tagMapper);
		return pickMapper.toPickResponse(savedPick, tagResponseList, linkUrlResponse);
	}

	// 픽 수정(제목, 내용, 태그)
	@Transactional
	public PickResponse updatePick(Long userId, PickUpdateRequest pickUpdateRequest) {
		Pick pick = pickProvider.findById(pickUpdateRequest.id());
		pickValidator.validatePickAccess(userId, pick);

		List<Long> tagIdList = pickUpdateRequest.tagIdList();
		List<PickTag> pickTagList = pickTagProvider.findAllPickTagByPickId(pick.getId());

		deleteUnusedPickTagList(tagIdList, pickTagList);
		List<TagResponse> tagResponseList = pickMapper.toTagResponseList(pick, tagIdList, pickTagList, pickTagProvider,
			tagProvider,
			tagMapper);

		LinkUrlResponse linkUrlResponse = linkMapper.createLinkUrlResponse(pick.getLink());

		pick.updatePick(pickUpdateRequest.title(), pickUpdateRequest.memo());
		return pickMapper.toPickResponse(pick, tagResponseList, linkUrlResponse);
	}

	// tagIdList로 넘어온 태그들만 디비에 저장하기 위해 넘어오지 않은 태그들은 삭제
	private void deleteUnusedPickTagList(List<Long> tagIdList, List<PickTag> pickTagList) {
		pickTagList.stream()
			.filter(pickTag -> !tagIdList.contains(pickTag.getTag().getId()))
			.forEach(
				pickTag -> pickTagProvider.deleteByPickIdAndTagId(pickTag.getPick().getId(), pickTag.getTag().getId()));
	}

	@Transactional
	public void releaseTagFromEveryPick(Tag tag) {
		pickTagProvider.deletePickTagRelationByTag(tag);
	}
}
