package com.dirtroadsoftware.rds4a.core.services.exceptions;

/**
 *
 */
public class AccountDoesNotExistException extends RuntimeException {
	private static final long serialVersionUID = 9174870795418428620L;

	public AccountDoesNotExistException(Throwable cause) {
        super(cause);
    }

    public AccountDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountDoesNotExistException(String message) {
        super(message);
    }

    public AccountDoesNotExistException() {
    }
}
