package kernel360.techpick.core.exception.base;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public abstract class ApiException extends RuntimeException {

	private final ApiErrorCode errorCode;

	protected ApiException(ApiErrorCode errorCode) {
		super(errorCode.toString());
		this.errorCode = errorCode;
	}

	public ApiErrorCode getApiErrorCode() {
		return errorCode;
	}

	/* Accessor must implement error handler */
	public abstract void handleError(HttpServletRequest req, HttpServletResponse res);
}
