package kernel360.techpick.feature.link.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kernel360.techpick.core.exception.feature.link.ApiLinkException;
import kernel360.techpick.feature.link.model.dto.LinkResponse;
import kernel360.techpick.feature.link.service.LinkService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/link")
public class LinkController implements LinkApi {

	private final LinkService linkService;

	@Override
	@GetMapping("/{linkId}")
	public ResponseEntity<LinkResponse> getById(@PathVariable Long linkId) throws ApiLinkException {

		LinkResponse linkResponse = linkService.getById(linkId);
		return ResponseEntity.ok(linkResponse);
	}

	@Override
	@GetMapping("")
	public ResponseEntity<LinkResponse> getByUrl(@RequestParam String url) throws ApiLinkException {

		LinkResponse linkResponse = linkService.getByUrl(url);
		return ResponseEntity.ok(linkResponse);
	}

	@Override
	@GetMapping("/all")
	public ResponseEntity<List<LinkResponse>> getAll() {

		List<LinkResponse> linkResponses = linkService.getAll();
		return ResponseEntity.ok(linkResponses);
	}

	@Override
	@DeleteMapping("/{linkId}")
	public ResponseEntity<Void> deleteById(Long linkId) throws ApiLinkException {

		linkService.deleteById(linkId);
		return ResponseEntity.ok().build();
	}
}
