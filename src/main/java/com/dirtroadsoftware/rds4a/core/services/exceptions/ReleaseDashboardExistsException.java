package com.dirtroadsoftware.rds4a.core.services.exceptions;

/**
 *
 */
public class ReleaseDashboardExistsException extends RuntimeException {
	private static final long serialVersionUID = 1323023622060601405L;

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
