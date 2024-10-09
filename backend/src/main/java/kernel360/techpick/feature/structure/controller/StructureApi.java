package kernel360.techpick.feature.structure.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kernel360.techpick.feature.structure.service.dto.StructureDeleteRequest;
import kernel360.techpick.feature.structure.service.dto.StructureMoveRequest;

@Tag(name = "구조 API", description = "구조 API")
public interface StructureApi {

	@Operation(
		summary = "구조 json 조회",
		description = "현재 로그인된 유저의 db에 저장되어있는 json 조회"
	)
	@ApiResponses(
		@ApiResponse(
			responseCode = "200",
			description = "구조 json을 정상적으로 조회했습니다."
		)
	)
	ResponseEntity<String> getStructure();

	@Operation(
		summary = "폴더 이동",
		description = "폴더를 특정 폴더 안으로 이동"
	)
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "폴더를 성공적으로 이동했습니다."
		),
		@ApiResponse(
			responseCode = "400",
			description = "기본폴더는 수정/이동이 불가능합니다."
		),
		@ApiResponse(
			responseCode = "403",
			description = "접근할 수 없는 폴더입니다."
		)
	})
	ResponseEntity<Void> moveFolder(StructureMoveRequest request);

	@Operation(
		summary = "휴지통에 있는 폴더 삭제",
		description = "휴지통에 있는 폴더 삭제, 휴지통이 아닌 다른곳에 있는 폴더일 경우 예외발생"
	)
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "폴더를 성공적으로 삭제했습니다."
		),
		@ApiResponse(
			responseCode = "400",
			description = "휴지통에 있는 폴더만 삭제 가능합니다."
		),
		@ApiResponse(
			responseCode = "403",
			description = "접근할 수 없는 폴더입니다."
		)
	})
	ResponseEntity<Void> deleteFolder(StructureDeleteRequest request);

	@Operation(
		summary = "픽 이동",
		description = "픽을 특정 폴더 안으로 이동"
	)
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "픽을 성공적으로 이동했습니다."
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
	ResponseEntity<Void> movePick(StructureMoveRequest request);

	@Operation(
		summary = "휴지통에 있는 픽 삭제",
		description = "휴지통에 있는 픽 삭제, 휴지통이 아닌 다른곳에 있는 픽일 경우 예외발생"
	)
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "픽을 성공적으로 삭제했습니다."
		),
		@ApiResponse(
			responseCode = "400",
			description = "휴지통에 있는 픽만 삭제 가능합니다."
		),
		@ApiResponse(
			responseCode = "403",
			description = "접근할 수 없는 폴더입니다."
		)
	})
	ResponseEntity<Void> deletePick(StructureDeleteRequest request);
}
