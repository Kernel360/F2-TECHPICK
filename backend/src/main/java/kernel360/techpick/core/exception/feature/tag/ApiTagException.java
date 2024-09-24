package kernel360.techpick.core.exception.feature.tag;

import kernel360.techpick.core.exception.base.ApiException;

public class ApiTagException extends ApiException {

	private ApiTagException(ApiTagErrorCode errorCode) {
		super(errorCode);
	}

	/**
	 * TODO: Implement static factory method
	 * */
	public static ApiTagException TAG_DUPLICATE() {
		return new ApiTagException(ApiTagErrorCode.TAG_DUPLICATION);
	}
}
