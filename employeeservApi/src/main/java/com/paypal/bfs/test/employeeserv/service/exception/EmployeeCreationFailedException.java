package com.paypal.bfs.test.employeeserv.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class EmployeeCreationFailedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmployeeCreationFailedException(String msg) {
		super(msg);
	}
}
