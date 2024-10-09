package kernel360.techpick.feature.pick.service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kernel360.techpick.core.model.folder.Folder;
import kernel360.techpick.core.model.pick.Pick;
import kernel360.techpick.core.model.tag.Tag;
import kernel360.techpick.core.model.user.User;
import kernel360.techpick.feature.pick.model.PickProvider;
import kernel360.techpick.feature.pick.model.PickTagProvider;
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
	private final PickTagProvider pickTagProvider;

	// 픽 상세 조회
	public PickResponse getPickById(PickIdDto pickIdDto) {
		return null;
	}

	// 사용자의 픽 리스트 조회
	public List<PickResponse> getPickListByUserId(Long userId) {
		return null;
	}

	// 폴더에 있는 픽 리스트 조회
	public List<PickResponse> getPickListByParentFolderId(Long userId, Long parentFolderId) {
		return null;
	}

	// 미분류 폴더에 있는 픽 리스트 조회
	public List<PickResponse> getPickListInUnclassified(Long userId) {
		return null;
	}

	// 픽 생성
	public PickResponse createPick(Long userId, PickCreateRequest pickCreateRequest) {
		return null;
	}

	// 픽 수정(제목, 내용, 태그)
	public PickResponse updatePick(Long userId, PickUpdateRequest pickUpdateRequest) {
		return null;
	}

	@Transactional
	public void releaseTagFromEveryPick(Tag tag) {
		pickTagProvider.deletePickTagRelationByTag(tag);
	}
}
