package kernel360.techpick.core.exception.base.internal;

import org.springframework.http.ResponseEntity;

import kernel360.techpick.core.exception.base.ApiErrorCode;

public class ApiErrorResponse extends ResponseEntity<ApiErrorBody> {

	private ApiErrorResponse(ApiErrorCode apiErrorCode) {
		super(
			new ApiErrorBody(apiErrorCode.getCodeNumber(), apiErrorCode.getMessage()),
			apiErrorCode.getHttpStatus()
		);
	}

	public static ApiErrorResponse of(ApiErrorCode apiErrorCode) {
		return new ApiErrorResponse(apiErrorCode);
	}

	public static ApiErrorResponse UNKNOWN_SERVER_ERROR() {
		return new ApiErrorResponse(ApiErrorCode.UNKNOWN_SERVER_ERROR);
	}
}
