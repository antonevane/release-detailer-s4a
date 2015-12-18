package com.dirtroadsoftware.rds4a.core.services.exceptions;

/**
 *
 */
public class MaReleaseNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1460311989974250729L;
	
	public MaReleaseNotFoundException() {
        super();
    }
    public MaReleaseNotFoundException(Throwable cause) {
        super(cause);
    }
    public MaReleaseNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
