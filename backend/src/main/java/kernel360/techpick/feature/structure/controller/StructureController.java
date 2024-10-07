package kernel360.techpick.feature.structure.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kernel360.techpick.feature.structure.service.StructureService;
import kernel360.techpick.feature.structure.service.dto.StructureDeleteRequest;
import kernel360.techpick.feature.structure.service.dto.StructureMoveRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/structures")
public class StructureController implements StructureApi {

	private final StructureService structureService;

	@Override
	@GetMapping
	public ResponseEntity<String> getStructureByUserId(Authentication auth) {

		return ResponseEntity.ok(structureService.getStructureByUserId((Long)auth.getPrincipal()));
	}

	@Override
	@PutMapping("/folders/{folderId}")
	public ResponseEntity<Void> moveFolder(Authentication auth, @PathVariable Long folderId,
		@RequestBody StructureMoveRequest request) {

		request.updateUserIdAndTargetId(auth, folderId);
		structureService.moveFolder(request);

		return ResponseEntity.noContent().build();
	}

	@Override
	@DeleteMapping("/folders/{folderId}")
	public ResponseEntity<Void> deleteFolder(Authentication auth, @PathVariable Long folderId,
		@RequestBody StructureDeleteRequest request) {

		request.updateUserIdAndTargetId(auth, folderId);
		structureService.deleteFolder(request);

		return ResponseEntity.noContent().build();
	}

	@Override
	@PutMapping("/picks/{pickId}")
	public ResponseEntity<Void> movePick(Authentication auth, @PathVariable Long pickId,
		@RequestBody StructureMoveRequest request) {

		request.updateUserIdAndTargetId(auth, pickId);
		structureService.movePick(request);
		return ResponseEntity.noContent().build();
	}

	@Override
	@DeleteMapping("/picks/{pickId}")
	public ResponseEntity<Void> deletePick(Authentication auth, @PathVariable Long pickId,
		@RequestBody StructureDeleteRequest request) {

		request.updateUserIdAndTargetId(auth, pickId);
		structureService.deletePick(request);
		return ResponseEntity.noContent().build();
	}
}
