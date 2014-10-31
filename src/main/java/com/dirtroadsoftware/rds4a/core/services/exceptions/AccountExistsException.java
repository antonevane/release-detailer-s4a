package com.dirtroadsoftware.rds4a.core.services.exceptions;

/**
 *
 */
public class AccountExistsException extends RuntimeException {
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
