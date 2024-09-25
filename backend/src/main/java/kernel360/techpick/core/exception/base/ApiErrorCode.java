package kernel360.techpick.core.exception.base;

import org.springframework.http.HttpStatus;

public interface ApiErrorCode {

	String getCode();

	String getMessage();

	HttpStatus getHttpStatus();

	default String convertCodeToString(ApiErrorCode errorCode) {
		return String.format("[ 에러 코드 %s : %s ]", errorCode.getCode(), errorCode.getMessage());
	}
}
