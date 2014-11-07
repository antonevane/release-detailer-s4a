package com.dirtroadsoftware.rds4a.core.services.exceptions;

/**
 *
 */
public class ReleaseSiteNotFoundException extends RuntimeException {
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
