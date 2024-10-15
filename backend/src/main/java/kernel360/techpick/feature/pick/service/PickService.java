package kernel360.techpick.feature.pick.service;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kernel360.techpick.core.model.folder.Folder;
import kernel360.techpick.core.model.link.Link;
import kernel360.techpick.core.model.pick.Pick;
import kernel360.techpick.core.model.pick.PickTag;
import kernel360.techpick.core.model.tag.Tag;
import kernel360.techpick.core.model.user.User;
import kernel360.techpick.feature.folder.model.FolderProvider;
import kernel360.techpick.feature.link.model.LinkMapper;
import kernel360.techpick.feature.link.service.LinkService;
import kernel360.techpick.feature.link.service.dto.LinkRequest;
import kernel360.techpick.feature.link.service.dto.LinkUrlResponse;
import kernel360.techpick.feature.pick.exception.ApiPickException;
import kernel360.techpick.feature.pick.model.PickMapper;
import kernel360.techpick.feature.pick.model.PickProvider;
import kernel360.techpick.feature.pick.model.PickTagProvider;
import kernel360.techpick.feature.pick.service.dto.PickCreateRequest;
import kernel360.techpick.feature.pick.service.dto.PickResponse;
import kernel360.techpick.feature.pick.service.dto.PickUpdateRequest;
import kernel360.techpick.feature.pick.validator.PickValidator;
import kernel360.techpick.feature.tag.model.TagMapper;
import kernel360.techpick.feature.tag.model.TagProvider;
import kernel360.techpick.feature.tag.service.dto.TagResponse;
import kernel360.techpick.feature.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PickService {

	private final PickMapper pickMapper;
	private final PickProvider pickProvider;
	private final PickTagProvider pickTagProvider;
	private final PickValidator pickValidator;
	private final FolderProvider folderProvider;
	private final LinkMapper linkMapper;
	private final LinkService linkService;
	private final TagMapper tagMapper;
	private final TagProvider tagProvider;
	private final UserService userService;

	// 픽 상세 조회
	@Transactional(readOnly = true)
	public PickResponse getPickById(Long pickId) {
		Pick pick = pickProvider.findById(pickId);

		// 본인 픽인지 검증 (pickId)
		pickValidator.validatePickAccess(userService.getCurrentUser(), pick);

		List<PickTag> pickTagList = pickTagProvider.findAllPickTagByPickId(pickId);
		LinkUrlResponse linkUrlResponse = linkMapper.toLinkUrlResponse(pick.getLink());
		List<TagResponse> tagResponseList = tagMapper.toTagResponse(pickTagList);

		return pickMapper.toPickResponse(pick, tagResponseList, linkUrlResponse);
	}

	// 사용자의 픽 리스트 조회
	@Transactional(readOnly = true)
	public List<PickResponse> getPickListByUser() {
		List<Pick> picks = pickProvider.findAllByUser(userService.getCurrentUser());
		return pickMapper.toPickResponseList(picks, pickTagProvider, tagMapper, linkMapper);
	}

	// 폴더에 있는 픽 리스트 조회
	@Transactional(readOnly = true)
	public List<PickResponse> getPickListByParentFolderId(Long parentFolderId) {
		List<Pick> picks = pickProvider.findAllByParentFolderId(userService.getCurrentUser(), parentFolderId);
		return pickMapper.toPickResponseList(picks, pickTagProvider, tagMapper, linkMapper);
	}

	// Url로 픽 상세 조회
	@Transactional(readOnly = true)
	public PickResponse getPickIdByUrl(String url) {
		User user = userService.getCurrentUser();
		Pick pick = pickProvider.getByUserAndLinkUrl(user, url);

		// 본인 픽인지 검증 (pickId)
		pickValidator.validatePickAccess(userService.getCurrentUser(), pick);

		List<PickTag> pickTagList = pickTagProvider.findAllPickTagByPickId(pick.getId());
		LinkUrlResponse linkUrlResponse = linkMapper.toLinkUrlResponse(pick.getLink());
		List<TagResponse> tagResponseList = tagMapper.toTagResponse(pickTagList);

		return pickMapper.toPickResponse(pick, tagResponseList, linkUrlResponse);
	}

	// 픽 생성
	@Transactional
	public PickResponse createPick(PickCreateRequest pickCreateRequest) {

		User user = userService.getCurrentUser();

		if (pickProvider.existsByUserAndLinkUrl(user, pickCreateRequest.linkRequest().url())) {
			ApiPickException.PICK_ALREADY_EXIST();
		}

		Folder folder = folderProvider.findUnclassified(user);

		LinkRequest linkRequest = pickCreateRequest.linkRequest();
		Link link = linkService.saveOrUpdateLink(linkRequest);
		LinkUrlResponse linkUrlResponse = linkMapper.toLinkUrlResponse(link);

		Pick savedPick = pickProvider.save(pickMapper.toPickEntity(user, link, folder, pickCreateRequest));

		List<Long> tagIdList = pickCreateRequest.tagIdList();
		List<PickTag> pickTagList = pickTagProvider.findAllPickTagByPickId(savedPick.getId());
		List<TagResponse> tagResponseList = tagMapper.toTagResponseList(savedPick, tagIdList, pickTagList,
			pickTagProvider,
			tagProvider);
		return pickMapper.toPickResponse(savedPick, tagResponseList, linkUrlResponse);
	}

	// 픽 수정(제목, 내용, 태그)
	@Transactional
	public PickResponse updatePick(PickUpdateRequest pickUpdateRequest) {
		Pick pick = pickProvider.findById(pickUpdateRequest.id());
		pickValidator.validatePickAccess(userService.getCurrentUser(), pick);

		List<Long> tagIdList = pickUpdateRequest.tagIdList();
		List<PickTag> pickTagList = pickTagProvider.findAllPickTagByPickId(pick.getId());

		deleteUnusedPickTagList(tagIdList, pickTagList);
		List<TagResponse> tagResponseList = tagMapper.toTagResponseList(pick, tagIdList, pickTagList, pickTagProvider,
			tagProvider);

		LinkUrlResponse linkUrlResponse = linkMapper.toLinkUrlResponse(pick.getLink());

		pick.updatePick(pickUpdateRequest.title(), pickUpdateRequest.memo());
		return pickMapper.toPickResponse(pick, tagResponseList, linkUrlResponse);
	}

	@Transactional
	public void deletePick(Long pickId) {
		pickTagProvider.deleteByPickId(pickId);
		pickProvider.deleteById(pickId);
	}

	@Transactional
	public void releaseTagFromEveryPick(Tag tag) {
		pickTagProvider.deletePickTagRelationByTagId(tag.getId());
	}

	// tagIdList로 넘어온 태그들만 디비에 저장하기 위해 넘어오지 않은 태그들은 삭제
	private void deleteUnusedPickTagList(List<Long> tagIdList, List<PickTag> pickTagList) {
		pickTagList.stream()
			.filter(pickTag -> !tagIdList.contains(pickTag.getTag().getId()))
			.forEach(
				pickTag -> pickTagProvider.deleteByPickIdAndTagId(pickTag.getPick().getId(), pickTag.getTag().getId()));
	}
}
