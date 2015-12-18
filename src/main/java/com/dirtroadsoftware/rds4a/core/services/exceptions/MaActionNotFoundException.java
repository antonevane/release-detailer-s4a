package com.dirtroadsoftware.rds4a.core.services.exceptions;

/**
 *
 */
public class MaActionNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1971236499137416183L;

	public MaActionNotFoundException() {
		super();
	}

	public MaActionNotFoundException(Throwable cause) {
		super(cause);
	}

	public MaActionNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
