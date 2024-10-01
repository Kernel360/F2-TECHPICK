package kernel360.techpick.feature.tag.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tag")
public class TagController implements TagApi {

	private final TagService tagService;

	@Override
	@PostMapping
	public ResponseEntity<TagResponse> createTag(Authentication auth, @RequestBody TagCreateRequest request) {

		return ResponseEntity.ok(tagService.createTag(getUserId(auth), request));
	}

	@Override
	@GetMapping
	public ResponseEntity<List<TagResponse>> getTagListByUser(Authentication auth) {

		return ResponseEntity.ok(tagService.getTagListByUser(getUserId(auth)));
	}

	@Override
	@PutMapping
	public ResponseEntity<List<TagResponse>> updateTagList(Authentication auth,
		@RequestBody List<TagUpdateRequest> tagUpdateRequests) {

		return ResponseEntity.ok(tagService.updateTagList(getUserId(auth), tagUpdateRequests));
	}

	@Override
	@DeleteMapping("/{tagId}")
	public ResponseEntity<Void> deleteTagById(Authentication auth, @PathVariable Long tagId) {

		tagService.deleteById(getUserId(auth), tagId);

		return ResponseEntity.noContent().build();
	}

	// TODO: 직접 token에 있는 userId를 주입받을 방법 있는지 확인 후 리팩토링 필요
	private Long getUserId(Authentication authentication) {

		return (Long)authentication.getPrincipal();
	}

}
