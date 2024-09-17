package kernel360.techpick.rss.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kernel360.techpick.rss.dto.RssResponse;
import kernel360.techpick.rss.service.RssService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rss")
@RequiredArgsConstructor
public class RssController {

	private final RssService rssService;

	@GetMapping
	public ResponseEntity<?> getNewRss() {
		List<RssResponse.Channel> rss = rssService.getNewRss();
		return ResponseEntity.ok(rss);
	}

	@GetMapping("/baeldung")
	public ResponseEntity<?> getBaeldung() {
		RssResponse.Channel rss = rssService.getRssByBaeldung();
		return ResponseEntity.ok(rss);
	}

	@GetMapping("/toss")
	public ResponseEntity<?> getToss() {
		RssResponse.Channel rss = rssService.getRssByToss();
		return ResponseEntity.ok(rss);
	}

	@GetMapping("/woowa")
	public ResponseEntity<?> getWoowa() {
		RssResponse.Channel rss = rssService.getRssByWoowa();
		return ResponseEntity.ok(rss);
	}

	@GetMapping("/kakao")
	public ResponseEntity<?> getKakao() {
		RssResponse.Channel rss = rssService.getRssByKakao();
		return ResponseEntity.ok(rss);
	}

	@GetMapping("/daangn")
	public ResponseEntity<?> getDaangn() {
		RssResponse.Channel rss = rssService.getRssByDaangn();
		return ResponseEntity.ok(rss);
	}

	@GetMapping("/line")
	public ResponseEntity<?> getLine() {
		RssResponse.Channel rss = rssService.getRssByLine();
		return ResponseEntity.ok(rss);
	}

}
