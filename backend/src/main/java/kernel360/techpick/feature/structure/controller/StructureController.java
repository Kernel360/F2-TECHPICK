package kernel360.techpick.feature.structure.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/api/structures")
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
	@PutMapping("/folders")
	public ResponseEntity<Void> moveFolder(
		@RequestBody StructureMoveRequest request
	) {
		structureService.moveFolder(userService.getCurrentUser(), request);

		return ResponseEntity.noContent().build();
	}

	@Override
	@DeleteMapping("/folders")
	public ResponseEntity<Void> deleteFolder(
		@RequestBody StructureDeleteRequest request
	) {
		structureService.deleteFolder(userService.getCurrentUser(), request);

		return ResponseEntity.noContent().build();
	}

	@Override
	@PutMapping("/picks")
	public ResponseEntity<Void> movePick(
		@RequestBody StructureMoveRequest request
	) {
		structureService.movePick(userService.getCurrentUser(), request);

		return ResponseEntity.noContent().build();
	}

	@Override
	@DeleteMapping("/picks")
	public ResponseEntity<Void> deletePick(
		@RequestBody StructureDeleteRequest request
	) {
		structureService.deletePick(userService.getCurrentUser(), request);

		return ResponseEntity.noContent().build();
	}
}
