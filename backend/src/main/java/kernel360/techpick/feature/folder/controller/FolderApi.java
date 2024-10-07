package kernel360.techpick.feature.folder.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kernel360.techpick.feature.folder.service.dto.FolderResponse;
import kernel360.techpick.feature.folder.service.dto.FolderUpdateRequest;

@Tag(name = "폴더 API", description = "폴더 API")
public interface FolderApi {

	@Operation(
		summary = "기본 폴더 id 조회",
		description = "현재 로그인된 사용자의 기본폴더(ROOT, UNCLASSIFIED, RECYCLE_BIN)의 id를 조회합니다."
	)
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "기본폴더 id를 정상적으로 조회했습니다."
		)
	})
	ResponseEntity<Map<String, Long>> getBasicFolderIdMap(Authentication auth);

	@Operation(
		summary = "자식 폴더 조회",
		description = "특정 폴더에 속한 폴더를 조회합니다. 본인의 폴더가 아니면 403예외가 발생합니다."
	)
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "폴더를 정상적으로 조회했습니다."
		),
		@ApiResponse(
			responseCode = "403",
			description = "접근할 수 없는 폴더입니다."
		)
	})
	ResponseEntity<List<FolderResponse>> getFolderListByParentFolderId(Authentication auth, Long folderId);

	@Operation(
		summary = "폴더 이름 변경",
		description = "폴더 이름 변경"
	)
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "폴더 이름은 정상적으로 변경했습니다."
		),
		@ApiResponse(
			responseCode = "400",
			description = ""
		),
		@ApiResponse(
			responseCode = "403",
			description = "접근할 수 없는 폴더입니다."
		)
	})
	ResponseEntity<Void> updateFolderName(Authentication auth, Long folderId, FolderUpdateRequest request);

}
