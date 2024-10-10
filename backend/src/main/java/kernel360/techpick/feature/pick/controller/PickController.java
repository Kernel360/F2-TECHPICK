package kernel360.techpick.feature.pick.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kernel360.techpick.core.model.folder.Folder;
import kernel360.techpick.core.model.folder.FolderType;
import kernel360.techpick.feature.pick.service.PickService;
import kernel360.techpick.feature.pick.service.dto.PickCreateRequest;
import kernel360.techpick.feature.pick.service.dto.PickIdDto;
import kernel360.techpick.feature.pick.service.dto.PickResponse;
import kernel360.techpick.feature.pick.service.dto.PickUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/picks")
@Slf4j
public class PickController implements PickApi {

	private final PickService pickService;

	@Override
	@GetMapping
	public ResponseEntity<List<PickResponse>> getPickListByUserId(Authentication auth) {
		return ResponseEntity.ok(pickService.getPickListByUserId((Long)auth.getPrincipal()));
	}

	@Override
	@GetMapping("/{pickId}")
	public ResponseEntity<PickResponse> getPickById(Authentication auth, @PathVariable Long pickId) {
		return ResponseEntity.ok(pickService.getPickById(PickIdDto.create(auth, pickId)));
	}

	@Override
	@GetMapping(params = "parentFolder")
	public ResponseEntity<List<PickResponse>> getPickListByParentFolderId(Authentication auth,
		@RequestParam(value = "parentFolder", required = false) Long parentFolderId) {
		return ResponseEntity.ok(pickService.getPickListByParentFolderId((Long)auth.getPrincipal(), parentFolderId));
	}

	@Override
	@GetMapping(params = "folderType")
	public ResponseEntity<List<PickResponse>> getPickListByUnclassified(Authentication auth,
		@RequestParam(required = false) FolderType folderType) {
		if (FolderType.getUnclassifiedFolderTypes().contains(folderType)) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(pickService.getPickListByUnclassified((Long)auth.getPrincipal()));
	}

	@Override
	@PostMapping
	public ResponseEntity<PickResponse> createPick(Authentication auth,
		@RequestBody PickCreateRequest pickCreateRequest) {
		return ResponseEntity.ok(pickService.createPick((Long)auth.getPrincipal(), pickCreateRequest));
	}

	@Override
	@PutMapping
	public ResponseEntity<PickResponse> updatePick(Authentication auth,
		@RequestBody PickUpdateRequest pickUpdateRequest) {
		return ResponseEntity.ok(pickService.updatePick((Long)auth.getPrincipal(), pickUpdateRequest));
	}
}
