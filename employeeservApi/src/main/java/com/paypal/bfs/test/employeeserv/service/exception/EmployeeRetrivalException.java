package com.paypal.bfs.test.employeeserv.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class EmployeeRetrivalException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public EmployeeRetrivalException(String msg) {
	   super(msg);
	}
}
