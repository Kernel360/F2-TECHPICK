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

	/**
	 * 필요할 경우, 아래 함수를 상속 받아 오버라이딩 하세요.
	 * 구현한 메소드는 `ApiExceptionHandler`에서 일괄 호출 됩니다.
	 * @see ApiExceptionHandler
	 *
	 * @author Minkyeu Kim
	 * */
	public void handleError(HttpServletRequest req, HttpServletResponse res) {
		// ... does nothing by default.
	}
}
