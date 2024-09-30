package kernel360.techpick.feature.link.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kernel360.techpick.feature.link.exception.ApiLinkException;
import kernel360.techpick.feature.link.service.dto.LinkResponse;
import kernel360.techpick.feature.link.service.LinkService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/links")
public class LinkController implements LinkApi {

	private final LinkService linkService;

	@Override
	@GetMapping("/") // "/" 안붙이니 getByUrl하고 충돌남
	public ResponseEntity<List<LinkResponse>> getAll() {

		return ResponseEntity.ok(linkService.getLinkAll());
	}

	@Override
	@GetMapping("/{linkId}")
	public ResponseEntity<LinkResponse> getById(@PathVariable Long linkId) throws ApiLinkException {

		return ResponseEntity.ok(linkService.getLinkById(linkId));
	}

	@Override
	@GetMapping
	public ResponseEntity<LinkResponse> getByUrl(@RequestParam String url) throws ApiLinkException {

		return ResponseEntity.ok(linkService.getLinkByUrl(url));
	}

	@Override
	@DeleteMapping("/{linkId}")
	public ResponseEntity<Void> deleteById(Long linkId) throws ApiLinkException {

		linkService.deleteLinkById(linkId);
		return ResponseEntity.noContent().build();
	}
}
