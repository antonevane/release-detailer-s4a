package com.dirtroadsoftware.rds4a.core.services.exceptions;

/**
 *
 */
public class TownNotFoundException extends RuntimeException {
    public TownNotFoundException(Throwable cause) {
        super(cause);
    }

    public TownNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TownNotFoundException(String message) {
        super(message);
    }

    public TownNotFoundException() {
    }
}
