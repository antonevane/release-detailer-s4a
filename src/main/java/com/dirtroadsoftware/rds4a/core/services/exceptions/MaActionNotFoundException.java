package com.dirtroadsoftware.rds4a.core.services.exceptions;

/**
 *
 */
public class MaActionNotFoundException extends RuntimeException {
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
