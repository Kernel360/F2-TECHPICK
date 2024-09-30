package kernel360.techpick.core.exception.feature.rss;

import kernel360.techpick.core.exception.base.ApiErrorCode;
import kernel360.techpick.core.exception.base.ApiException;

public class ApiRssException extends ApiException {

	private ApiRssException(ApiErrorCode errorCode) {
		super(errorCode);
	}

	public static ApiRssException RSS_NOT_FOUND() {
		return new ApiRssException(ApiRssErrorCode.RSS_NOT_FOUND);
	}

}
