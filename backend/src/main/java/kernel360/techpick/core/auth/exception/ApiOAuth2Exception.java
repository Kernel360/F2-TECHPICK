package kernel360.techpick.core.auth.exception;

import kernel360.techpick.core.exception.base.ApiErrorCode;
import kernel360.techpick.core.exception.base.ApiException;

public class ApiOAuth2Exception extends ApiException {

	private ApiOAuth2Exception(ApiErrorCode errorCode) {
		super(errorCode);
	}

	/**
	 * TODO: Implement static factory method
	 * */
	public static ApiOAuth2Exception SOCIAL_TYPE_INVALID() {
		return new ApiOAuth2Exception(ApiOAuth2ErrorCode.SOCIAL_TYPE_INVALID);
	}
}
