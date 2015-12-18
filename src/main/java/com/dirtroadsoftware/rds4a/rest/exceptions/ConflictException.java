package com.dirtroadsoftware.rds4a.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 */
@ResponseStatus(value = HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException {
	private static final long serialVersionUID = -7176168341239665536L;

	public ConflictException() {
	}

	public ConflictException(Throwable cause) {
		super(cause);
	}
}
