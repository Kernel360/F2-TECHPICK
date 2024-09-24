package kernel360.techpick.feature.tag.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import kernel360.techpick.feature.tag.model.dto.TagCreateRequest;

public interface TagApiV1 {

	@Operation(
		summary = "사용자 정의 태그 생성 API", description = "사용자가 태그를 생성합니다."
		// TODO: 추가 적인 작업 진행
		//       참고: https://github.com/Kernel360/E2E2-TALKKA/blob/develop/server/src/main/java/com/talkka/server/bookmark/controller/BookmarkApi.java
	)
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "태그 생성 완료"),
		// TODO: Response 추가
	})
	@PostMapping
	ResponseEntity<?> createTag(@RequestBody TagCreateRequest request);
}
