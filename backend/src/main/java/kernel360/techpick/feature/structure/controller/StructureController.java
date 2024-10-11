package kernel360.techpick.feature.structure.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kernel360.techpick.feature.structure.service.Structure;
import kernel360.techpick.feature.structure.service.StructureService;
import kernel360.techpick.feature.structure.service.dto.StructureDeleteRequest;
import kernel360.techpick.feature.structure.service.dto.StructureMoveRequest;
import kernel360.techpick.feature.structure.service.node.client.ClientNode;
import kernel360.techpick.feature.user.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/${api.version}/structures")
public class StructureController implements StructureApi {

	private final UserService userService;
	private final StructureService structureService;

	@Override
	@GetMapping
	public ResponseEntity<String> getStructure() {
		Structure<ClientNode> structure
			= structureService.getClientStructure(userService.getCurrentUser());

		return ResponseEntity.ok(structure.serialize());
	}

	@Override
	@PutMapping("/folders/{folderId}")
	public ResponseEntity<Void> moveFolder(
		@PathVariable Long folderId,
		@RequestBody StructureMoveRequest request
	) {
		structureService.moveFolder(userService.getCurrentUser(), folderId, request);

		return ResponseEntity.noContent().build();
	}

	@Override
	@DeleteMapping("/folders/{folderId}")
	public ResponseEntity<Void> deleteFolder(
		@PathVariable Long folderId,
		@RequestBody StructureDeleteRequest request
	) {
		structureService.deleteFolder(userService.getCurrentUser(), folderId, request);

		return ResponseEntity.noContent().build();
	}

	@Override
	@PutMapping("/picks/{pickId}")
	public ResponseEntity<Void> movePick(
		@PathVariable Long pickId,
		@RequestBody StructureMoveRequest request
	) {
		structureService.movePick(userService.getCurrentUser(), pickId, request);

		return ResponseEntity.noContent().build();
	}

	@Override
	@DeleteMapping("/picks/{pickId}")
	public ResponseEntity<Void> deletePick(
		@PathVariable Long pickId,
		@RequestBody StructureDeleteRequest request
	) {
		structureService.deletePick(userService.getCurrentUser(), pickId, request);

		return ResponseEntity.noContent().build();
	}
}
