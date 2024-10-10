package kernel360.techpick.feature.folder.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kernel360.techpick.feature.folder.service.FolderService;
import kernel360.techpick.feature.folder.service.dto.FolderCreateRequest;
import kernel360.techpick.feature.folder.service.dto.FolderResponse;
import kernel360.techpick.feature.folder.service.dto.FolderUpdateRequest;
import kernel360.techpick.feature.user.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/folders")
public class FolderController implements FolderApi {

	private final FolderService folderService;
	private final UserService userService;

	@Override
	@PostMapping
	public ResponseEntity<FolderResponse> createFolder(
		@RequestBody FolderCreateRequest request
	) {
		FolderResponse res = folderService.createGeneralFolder(
			userService.getCurrentUser(), request
		);
		return ResponseEntity.ok(res);
	}

	@Override
	@GetMapping
	public ResponseEntity<Map<String, Long>> getBasicFolderIdMap() {

		Map<String, Long> res = folderService.getBasicFolderIdMap(
			userService.getCurrentUser()
		);

		return ResponseEntity.ok(res);
	}

	@Override
	@GetMapping("/parent/{folderId}")
	public ResponseEntity<List<FolderResponse>> getFolderListByParentFolderId(
		@PathVariable Long folderId
	) {
		List<FolderResponse> res = folderService.getFolderListByParentFolderId(
			userService.getCurrentUser(),
			folderId
		);

		return ResponseEntity.ok(res);
	}

	@Override
	@PutMapping("/{folderId}")
	public ResponseEntity<Void> updateFolderName(
		@PathVariable Long folderId,
		@RequestBody FolderUpdateRequest request
	) {
		folderService.updateName(
			userService.getCurrentUser(),
			folderId,
			request
		);

		return ResponseEntity.noContent().build();
	}
}
