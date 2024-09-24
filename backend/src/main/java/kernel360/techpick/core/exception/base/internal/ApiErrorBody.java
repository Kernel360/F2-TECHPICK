package kernel360.techpick.core.exception.base.internal;

public record ApiErrorBody(
	Integer code,
	String message
) {}
