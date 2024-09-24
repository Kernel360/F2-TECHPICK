package kernel360.techpick.core.exception.base.internal;

import kernel360.techpick.core.exception.base.ApiErrorCode;

public abstract class ApiException extends RuntimeException {

	private final ApiErrorCode errorCode;

	public ApiException(ApiErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}

	public ApiErrorCode getApiErrorCode() {
		return errorCode;
	}
}
