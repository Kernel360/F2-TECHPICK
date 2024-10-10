package kernel360.techpick.feature.tag.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kernel360.techpick.feature.tag.service.dto.TagCreateRequest;
import kernel360.techpick.feature.tag.service.dto.TagResponse;
import kernel360.techpick.feature.tag.service.dto.TagUpdateRequest;

@Tag(name = "태그 API", description = "태그 API")
public interface TagApi {

	@Operation(
		summary = "태그 생성",
		description = "태그를 생성합니다. 생성된 태그는 가장 마지막 순서에 위치합니다."
	)
	@ApiResponses(
		@ApiResponse(
			responseCode = "200",
			description = "태그를 정상적으로 생성했습니다."
		)
	)
	ResponseEntity<TagResponse> createTag(
		@RequestBody(description = "태그 생성 정보", required = true)
		TagCreateRequest request
	);

	@Operation(
		summary = "태그 리스트 조회",
		description = "자신이 등록한 태그 리스트를 조회한다. 태그는 order 오름차순으로 정렬되어있음."
	)
	@ApiResponses(
		@ApiResponse(
			responseCode = "200",
			description = "태그 리스트를 정상적으로 조회했습니다."
		)
	)
	ResponseEntity<List<TagResponse>> getTagListByUser();

	@Operation(
		summary = "태그 수정",
		description = "자신이 등록한 태그 리스트를 수정한다."
	)
	@ApiResponses(
		@ApiResponse(
			responseCode = "200",
			description = "태그 리스트를 정상적으로 수정했습니다."
		)
	)
	ResponseEntity<List<TagResponse>> updateTagList(
		@RequestBody(description = "태그 수정 정보", required = true)
		List<TagUpdateRequest> tagUpdateRequests
	);

	@Operation(
		summary = "태그 삭제",
		description = "자신이 등록한 태그를 삭제한다."
	)
	@ApiResponses(
		@ApiResponse(
			responseCode = "200",
			description = "태그를 정상적으로 삭제했습니다."
		)
	)
	ResponseEntity<Void> deleteTagById(Long tagId);
}
