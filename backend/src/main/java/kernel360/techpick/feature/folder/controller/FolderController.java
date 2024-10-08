package kernel360.techpick.feature.folder.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kernel360.techpick.feature.folder.service.FolderService;
import kernel360.techpick.feature.folder.service.dto.FolderCreateRequest;
import kernel360.techpick.feature.folder.service.dto.FolderIdDto;
import kernel360.techpick.feature.folder.service.dto.FolderResponse;
import kernel360.techpick.feature.folder.service.dto.FolderUpdateRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/folders")
public class FolderController implements FolderApi {

	private final FolderService folderService;

	@Override
	@PostMapping
	public ResponseEntity<FolderResponse> createFolder(Authentication auth, @RequestBody FolderCreateRequest request) {

		request.updateUserId(auth);
		return ResponseEntity.ok(folderService.createFolder(request));
	}

	@Override
	@GetMapping
	public ResponseEntity<Map<String, Long>> getBasicFolderIdMap(Authentication auth) {

		return ResponseEntity.ok(folderService.getBasicFolderIdMap((Long)auth.getPrincipal()));
	}

	@Override
	@GetMapping("/parent/{folderId}")
	public ResponseEntity<List<FolderResponse>> getFolderListByParentFolderId(Authentication auth,
		@PathVariable Long folderId) {

		return ResponseEntity.ok(
			folderService.getFolderListByParentFolderId(FolderIdDto.of(auth, folderId)));
	}

	@Override
	@PutMapping("/{folderId}")
	public ResponseEntity<Void> updateFolderName(Authentication auth, @PathVariable Long folderId,
		@RequestBody FolderUpdateRequest request) {

		request.updateUserIdAndFolderId(auth, folderId);
		folderService.updateName(request);
		return ResponseEntity.noContent().build();
	}

}
