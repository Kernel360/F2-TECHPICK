package kernel360.techpick.feature.directory.exception;

import kernel360.techpick.core.exception.base.ApiErrorCode;
import kernel360.techpick.core.exception.base.ApiException;

public class ApiDirectoryException extends ApiException {

	private ApiDirectoryException(ApiErrorCode errorCode) {
		super(errorCode);
	}

	/**
	 * TODO: Implement static factory method
	 */
	public static ApiDirectoryException DIRECTORY_INVALID_JSON_STRUCTURE() {
		return new ApiDirectoryException((ApiDirectoryErrorCode.DIRECTORY_INVALID_JSON_STRUCTURE));
	}
}
