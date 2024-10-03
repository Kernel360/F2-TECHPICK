package kernel360.techpick.feature.folder.exception;

import kernel360.techpick.core.exception.base.ApiErrorCode;
import kernel360.techpick.core.exception.base.ApiException;

public class ApiFolderException extends ApiException {

	private ApiFolderException(ApiErrorCode errorCode) {
		super(errorCode);
	}

	/**
	 * TODO: Implement static factory method
	 */
	public static ApiFolderException FOLDER_NOT_FOUND() {
		return new ApiFolderException(ApiFolderErrorCode.FOLDER_NOT_FOUND);
	}

	public static ApiFolderException FOLDER_ALREADY_EXIST() {
		return new ApiFolderException(ApiFolderErrorCode.FOLDER_ALREADY_EXIST);
	}

	public static ApiFolderException FOLDER_ACCESS_DENIED() {
		return new ApiFolderException(ApiFolderErrorCode.FOLDER_ACCESS_DENIED);
	}

	public static ApiFolderException FOLDER_INVALID_JSON_STRUCTURE() {
		return new ApiFolderException((ApiFolderErrorCode.FOLDER_INVALID_JSON_STRUCTURE));
	}
}
