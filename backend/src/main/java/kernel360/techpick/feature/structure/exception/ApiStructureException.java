package kernel360.techpick.feature.structure.exception;

import kernel360.techpick.core.exception.base.ApiErrorCode;
import kernel360.techpick.core.exception.base.ApiException;

public class ApiStructureException extends ApiException {

	private ApiStructureException(ApiErrorCode errorCode) {
		super(errorCode);
	}

	/**
	 * TODO: Implement static factory method
	 */
	public static ApiStructureException INVALID_JSON_STRUCTURE() {
		return new ApiStructureException((ApiStructureErrorCode.INVALID_JSON_STRUCTURE));
	}

	public static ApiStructureException USER_STRUCTURE_JSON_NOT_FOUND() {
		return new ApiStructureException((ApiStructureErrorCode.USER_STRUCTURE_JSON_NOT_FOUND));
	}
}
