package com.dirtroadsoftware.rds4a.core.services.exceptions;

/**
 *
 */
public class ReleaseDashboardNotFoundException extends RuntimeException {
    public ReleaseDashboardNotFoundException(Throwable cause) {
        super(cause);
    }

    public ReleaseDashboardNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReleaseDashboardNotFoundException(String message) {
        super(message);
    }

    public ReleaseDashboardNotFoundException() {
    }
}
