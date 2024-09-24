package kernel360.techpick.core.exception.feature.oauth2;

import kernel360.techpick.core.exception.base.ApiErrorCode;
import kernel360.techpick.core.exception.base.ApiException;

public class ApiOAuth2Exception extends ApiException {

	private ApiOAuth2Exception(ApiErrorCode errorCode) {
		super(errorCode);
	}

	public static ApiOAuth2Exception SOCIAL_TYPE_INVALID() {
		return new ApiOAuth2Exception(ApiOAuth2ErrorCode.SOCIAL_TYPE_INVALID);
	}
    
}
