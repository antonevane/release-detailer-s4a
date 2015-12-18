package com.dirtroadsoftware.rds4a.core.services.exceptions;

/**
 *
 */
public class ReleaseSiteNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -9147519191072268490L;

	public ReleaseSiteNotFoundException() {
		super();
	}

	public ReleaseSiteNotFoundException(Throwable cause) {
		super(cause);
	}

	public ReleaseSiteNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
