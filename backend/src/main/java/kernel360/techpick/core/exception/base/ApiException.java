package kernel360.techpick.core.exception.base;

public abstract class ApiException extends RuntimeException {

	private final ApiErrorCode errorCode;

	protected ApiException(ApiErrorCode errorCode) {
		super(errorCode.toString());
		this.errorCode = errorCode;
	}

	public ApiErrorCode getApiErrorCode() {
		return errorCode;
	}
}
