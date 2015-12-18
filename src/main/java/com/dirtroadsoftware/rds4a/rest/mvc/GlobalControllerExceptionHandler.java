package com.dirtroadsoftware.rds4a.rest.mvc;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dirtroadsoftware.rds4a.core.services.exceptions.AccountDoesNotExistException;
import com.dirtroadsoftware.rds4a.core.services.exceptions.AccountExistsException;
import com.dirtroadsoftware.rds4a.core.services.exceptions.ReleaseDashboardExistsException;
import com.dirtroadsoftware.rds4a.rest.exceptions.NotFoundException;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
	@ResponseBody
	@ExceptionHandler(AccountDoesNotExistException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	VndErrors accountDoesNotExistExceptionHandler(AccountDoesNotExistException ex) {
		return new VndErrors("error", ex.getMessage());
	}

	@ResponseBody
	@ExceptionHandler(ReleaseDashboardExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	VndErrors releaseDashboardExistExceptionHandler(ReleaseDashboardExistsException ex) {
		return new VndErrors("error", ex.getMessage());
	}

	@ResponseBody
	@ExceptionHandler(AccountExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	VndErrors accountExistExceptionHandler(AccountExistsException ex) {
		return new VndErrors("error", ex.getMessage());
	}

	@ResponseBody
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	VndErrors userNotFoundExceptionHandler(NotFoundException ex) {
		return new VndErrors("error", ex.getMessage());
	}

}
