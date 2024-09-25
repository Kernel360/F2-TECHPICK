package kernel360.techpick.core.exception.feature.tag;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kernel360.techpick.core.exception.base.ApiException;

public class ApiTagException extends ApiException {

	private ApiTagException(ApiTagErrorCode errorCode) {
		super(errorCode);
	}

	/**
	 * TODO: Implement handler
	 * */
	@Override
	public void handleError(HttpServletRequest req, HttpServletResponse res) {
	}

	/**
	 * TODO: Implement static factory method
	 * */
	public static ApiTagException TAG_DUPLICATE() {
		return new ApiTagException(ApiTagErrorCode.TAG_DUPLICATION);
	}
}
