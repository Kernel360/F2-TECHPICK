package kernel360.techpick.feature.tag.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kernel360.techpick.feature.tag.service.dto.TagCreateRequest;
import kernel360.techpick.feature.tag.service.dto.TagResponse;
import kernel360.techpick.feature.tag.service.dto.TagUpdateRequest;
import kernel360.techpick.feature.tag.service.TagService;
import kernel360.techpick.feature.user.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tag")
public class TagController implements TagApi {

	private final UserService userService;
	private final TagService tagService;

	@Override
	@PostMapping
	public ResponseEntity<TagResponse> createTag(
		@RequestBody TagCreateRequest request
	) {
		return ResponseEntity.ok(
			tagService.createTag(userService.getCurrentUser(), request)
		);
	}

	@Override
	@GetMapping
	public ResponseEntity<List<TagResponse>> getTagListByUser() {
		return ResponseEntity.ok(
			tagService.getTagList(userService.getCurrentUser())
		);
	}

	@Override
	@PutMapping
	public ResponseEntity<List<TagResponse>> updateTagList(
		@RequestBody List<TagUpdateRequest> tagUpdateRequests
	) {
		return ResponseEntity.ok(
			tagService.updateTagList(userService.getCurrentUser(), tagUpdateRequests)
		);
	}

	@Override
	@DeleteMapping("/{tagId}")
	public ResponseEntity<Void> deleteTagById(
		@PathVariable Long tagId
	) {
		tagService.deleteByTagId(userService.getCurrentUser(), tagId);
		return ResponseEntity.noContent().build();
	}
}
