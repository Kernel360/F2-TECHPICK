package kernel360.techpick.feature.directory.exception;

import org.springframework.http.HttpStatus;

import kernel360.techpick.core.exception.base.ApiErrorCode;
import kernel360.techpick.core.exception.level.ErrorLevel;

public enum ApiDirectoryErrorCode implements ApiErrorCode {

	/**
	 * Directory Error Code (DIR)
	 */
	DIRECTORY_INVALID_JSON_STRUCTURE
		("DIR-000", HttpStatus.BAD_REQUEST, "클라이언트 요청의 디렉토리 JSON 데이터가 올바르지 않음", ErrorLevel.SHOULD_NOT_HAPPEN()),

	;

	private final String code;

	private final HttpStatus httpStatus;

	private final String errorMessage;

	private final ErrorLevel errorLevel;

	ApiDirectoryErrorCode(String code, HttpStatus status, String message, ErrorLevel errorLevel) {
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
