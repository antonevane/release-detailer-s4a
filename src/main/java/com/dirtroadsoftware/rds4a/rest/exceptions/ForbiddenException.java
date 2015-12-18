package com.dirtroadsoftware.rds4a.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException {
	private static final long serialVersionUID = 8756542709498274143L;
}
