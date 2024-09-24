package kernel360.techpick.feature.tag.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import kernel360.techpick.feature.tag.model.dto.TagCreateRequest;
import kernel360.techpick.feature.tag.service.TagService;
import lombok.RequiredArgsConstructor;

/**
 * User Login이 필요한 기능은 Private prefix를 붙여보면 어떨까요?
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tag")
public class TagPrivateController {

	private final TagService tagService;

	@Operation(
		summary = "사용자 정의 태그 생성 API", description = "사용자가 태그를 생성합니다."
		// TODO: 추가 적인 작업 진행
	)
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "태그 생성 완료"),
		// TODO: Response 추가
	})
	@PostMapping
	public ResponseEntity<?> createTag(@RequestBody TagCreateRequest request) {

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
