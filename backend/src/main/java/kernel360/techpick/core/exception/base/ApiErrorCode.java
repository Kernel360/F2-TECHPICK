package kernel360.techpick.core.exception.base;

import org.springframework.http.HttpStatus;


public enum ApiErrorCode {

	/**
	 * Critical Server Error ( < 0)
	 */
	UNKNOWN_SERVER_ERROR(-1, HttpStatus.INTERNAL_SERVER_ERROR, "미확인 서버 오류"),

	/**
	 * User Error Code (0 ~ 999)
	 * */
	USER_NOT_FOUND(0, HttpStatus.BAD_REQUEST, "사용자 없음"),

	/**
	 * Pick Error Code (1000 ~ 1999)
	 * */
	PICK_DUPLICATION(1000, HttpStatus.BAD_REQUEST, "중복된 픽 요청"),

	/**
	 * Tag Error Code (2000 ~ 2999)
	 * */
	TAG_DUPLICATION(2000, HttpStatus.BAD_REQUEST, "중복된 태그 생성 요청"),

	;

	// ------------------------------------------------------------

	private final Integer codeNumber;

	private final HttpStatus httpStatus;

	private final String errorMessage;

	ApiErrorCode(Integer codeNumber, HttpStatus status, String message) {
		this.codeNumber = codeNumber;
		this.httpStatus = status;
		this.errorMessage = message;
	}

	public Integer getCodeNumber() {
		return this.codeNumber;
	}

	public String getMessage() {
		return this.errorMessage;
	}

	public HttpStatus getHttpStatus() {
		return this.httpStatus;
	}

	@Override
	public String toString() {
		return String.format("[ 에러 코드 %d - %s ]", this.codeNumber, this.errorMessage);
	}
}
