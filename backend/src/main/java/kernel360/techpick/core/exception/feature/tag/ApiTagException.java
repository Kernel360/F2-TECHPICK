package kernel360.techpick.core.exception.feature.tag;

import kernel360.techpick.core.exception.base.ApiErrorCode;
import kernel360.techpick.core.exception.base.internal.ApiException;

public class ApiTagException extends ApiException {

	private ApiTagException(ApiErrorCode errorCode) {
		super(errorCode);
	}

	/**
	 * TODO: Static Factory Method
	 * */
}
