package kernel360.techpick.core.exception.feature.link;

import org.springframework.http.HttpStatus;

import kernel360.techpick.core.exception.base.ApiErrorCode;

public enum ApiLinkErrorCode implements ApiErrorCode {

	/**
	 * Link Error Code (LI)
	 */
	LINK_NOT_FOUND("LI-000", HttpStatus.NOT_FOUND, "존재하지 않는 링크"),
	LINK_HAS_PICKS("LI-001", HttpStatus.BAD_REQUEST, "링크를 픽한 사람이 존재"),
	LINK_ALREADY_EXIST("LI-002", HttpStatus.BAD_REQUEST, "이미 존재하는 링크(URL)"),

	;

	// ------------------------------------------------------------
	// 하단 코드는 모든 ApiErrorCode 들에 반드시 포함되야 합니다.
	// 새로운 ErrorCode 구현시 복사 붙여넣기 해주세요.

	private final String code;

	private final HttpStatus httpStatus;

	private final String errorMessage;

	ApiLinkErrorCode(String code, HttpStatus status, String message) {
		this.code = code;
		this.httpStatus = status;
		this.errorMessage = message;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return errorMessage;
	}

	@Override
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
