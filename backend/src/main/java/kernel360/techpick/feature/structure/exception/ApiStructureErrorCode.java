package kernel360.techpick.feature.structure.exception;

import org.springframework.http.HttpStatus;

import kernel360.techpick.core.exception.base.ApiErrorCode;
import kernel360.techpick.core.exception.level.ErrorLevel;

public enum ApiStructureErrorCode implements ApiErrorCode {

	/**
	 * Structure Error Code (ST)
	 */
	INVALID_JSON_STRUCTURE
		("ST-000", HttpStatus.BAD_REQUEST, "클라이언트 요청의 디렉토리 JSON 데이터가 올바르지 않음", ErrorLevel.SHOULD_NOT_HAPPEN()),
	USER_STRUCTURE_JSON_NOT_FOUND
		("ST-001", HttpStatus.BAD_REQUEST, "사용자의 폴더구조 json이 없음", ErrorLevel.MUST_NEVER_HAPPEN()),
	USER_STRUCTURE_ALREADY_EXISTS
		("ST-002", HttpStatus.BAD_REQUEST, "사용자가 이미 JSON 구조를 가지고 있음", ErrorLevel.MUST_NEVER_HAPPEN()),
	;

	private final String code;

	private final HttpStatus httpStatus;

	private final String errorMessage;

	private final ErrorLevel errorLevel;

	ApiStructureErrorCode(String code, HttpStatus status, String message, ErrorLevel errorLevel) {
		this.code = code;
		this.httpStatus = status;
		this.errorMessage = message;
		this.errorLevel = errorLevel;
	}

	@Override
	public String getCode() {
		return this.code;
	}

	@Override
	public String getMessage() {
		return this.errorMessage;
	}

	@Override
	public HttpStatus getHttpStatus() {
		return this.httpStatus;
	}

	@Override
	public ErrorLevel getErrorLevel() {
		return this.errorLevel;
	}
}
