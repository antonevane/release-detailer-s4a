package com.dirtroadsoftware.rds4a.core.services.exceptions;

/**
 *
 */
public class ReleaseDashboardExistsException extends RuntimeException {
    public ReleaseDashboardExistsException(Throwable cause) {
        super(cause);
    }

    public ReleaseDashboardExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReleaseDashboardExistsException(String message) {
        super(message);
    }

    public ReleaseDashboardExistsException() {
    }

}
