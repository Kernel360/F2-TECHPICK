package kernel360.techpick.feature.pick.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kernel360.techpick.feature.pick.service.PickService;
import kernel360.techpick.feature.pick.service.dto.PickCreateRequest;
import kernel360.techpick.feature.pick.service.dto.PickDeleteRequest;
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
	public ResponseEntity<List<PickResponse>> getPickListByUser() {
		return ResponseEntity.ok(pickService.getPickListByUser());
	}

	@Override
	@GetMapping("/{pickId}")
	public ResponseEntity<PickResponse> getPickById(@PathVariable Long pickId) {
		return ResponseEntity.ok(pickService.getPickById(pickId));
	}

	@Override
	@GetMapping("/url")
	public ResponseEntity<PickResponse> getPickByUrl(@RequestParam String url) {
		return ResponseEntity.ok(pickService.getPickByUrl(url));
	}

	@Override
	@GetMapping(params = "parentId")
	public ResponseEntity<List<PickResponse>> getPickListByParentFolderId(
		@RequestParam(value = "parentId", required = false) Long parentFolderId) {
		return ResponseEntity.ok(pickService.getPickListByParentFolderId(parentFolderId));
	}

	@Override
	@PostMapping
	public ResponseEntity<PickResponse> createPick(@RequestBody PickCreateRequest pickCreateRequest) {
		return ResponseEntity.ok(pickService.createPick(pickCreateRequest));
	}

	@Override
	@PutMapping
	public ResponseEntity<PickResponse> updatePick(@RequestBody PickUpdateRequest pickUpdateRequest) {
		return ResponseEntity.ok(pickService.updatePick(pickUpdateRequest));
	}

	@Override
	@DeleteMapping
	public ResponseEntity<Void> deletePick(@RequestBody PickDeleteRequest pickDeleteRequest) {
		pickService.deletePick(pickDeleteRequest.id());
		return ResponseEntity.ok().build();
	}
}
