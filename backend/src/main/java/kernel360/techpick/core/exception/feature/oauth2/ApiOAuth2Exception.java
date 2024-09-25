package kernel360.techpick.core.exception.feature.oauth2;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kernel360.techpick.core.exception.base.ApiErrorCode;
import kernel360.techpick.core.exception.base.ApiException;

public class ApiOAuth2Exception extends ApiException {

	private ApiOAuth2Exception(ApiErrorCode errorCode) {
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
	public static ApiOAuth2Exception SOCIAL_TYPE_INVALID() {
		return new ApiOAuth2Exception(ApiOAuth2ErrorCode.SOCIAL_TYPE_INVALID);
	}
}
