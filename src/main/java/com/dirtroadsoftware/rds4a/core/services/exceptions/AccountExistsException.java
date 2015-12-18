package com.dirtroadsoftware.rds4a.core.services.exceptions;

/**
 *
 */
public class AccountExistsException extends RuntimeException {
	private static final long serialVersionUID = -695952249244012616L;

	public AccountExistsException(Throwable cause) {
        super(cause);
    }

    public AccountExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountExistsException(String message) {
        super(message);
    }

    public AccountExistsException() {
    }

}
