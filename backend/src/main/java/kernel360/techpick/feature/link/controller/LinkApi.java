package kernel360.techpick.feature.link.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kernel360.techpick.core.exception.base.ApiErrorBody;
import kernel360.techpick.feature.link.model.dto.LinkResponse;

@Tag(name = "링크", description = "링크 CRUD API")
public interface LinkApi {

	@Operation(summary = "", description = "링크 ID로 링크를 조회하는 API")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공",
			content = @Content(schema = @Schema(implementation = LinkResponse.class))
		),
		@ApiResponse(
			responseCode = "404",
			description = "[LI-000] 존재하지 않는 링크",
			content = @Content(schema = @Schema(implementation = ApiErrorBody.class))
		),
	})
	ResponseEntity<LinkResponse> getById(
		@Parameter(description = "링크 ID", required = true)
		Long linkId
	);

	@Operation(summary = "", description = "링크 URL로 링크를 조회하는 API")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공",
			content = @Content(schema = @Schema(implementation = LinkResponse.class))
		),
		@ApiResponse(
			responseCode = "404",
			description = "[LI-000] 존재하지 않는 링크",
			content = @Content(schema = @Schema(implementation = ApiErrorBody.class))
		),
	})
	ResponseEntity<LinkResponse> getByUrl(
		@Parameter(description = "링크 URL", required = true)
		String url
	);

	@Operation(summary = "", description = "모든 링크를 조회하는 API")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공",
			content = @Content(schema = @Schema(implementation = List.class))
		),
	})
	ResponseEntity<List<LinkResponse>> getAll();

	@Operation(summary = "", description = "링크 ID로 링크를 삭제하는 API")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공"
		),
		@ApiResponse(
			responseCode = "400",
			description = "[LI-001] 링크를 픽한 사람이 존재",
			content = @Content(schema = @Schema(implementation = ApiErrorBody.class))
		),
	})
	ResponseEntity<Void> deleteById(
		@Parameter(description = "링크 ID", required = true)
		Long linkId
	);
}
