package kernel360.techpick.feature.rss.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kernel360.techpick.feature.rss.service.RssService;
import kernel360.techpick.feature.rss.service.dto.RssResponse;
import lombok.RequiredArgsConstructor;

/**
 * RSS 테스트용 컨트롤러
 */
@RestController
@RequestMapping("/rss")
@RequiredArgsConstructor
public class RssController {

	private final RssService rssService;

	@GetMapping
	public ResponseEntity<List<RssResponse.Channel>> getNewRss() {
		List<RssResponse.Channel> rss = rssService.getNewRss();
		return ResponseEntity.ok(rss);
	}

}
