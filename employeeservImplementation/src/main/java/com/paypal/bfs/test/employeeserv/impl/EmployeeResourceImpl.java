package com.paypal.bfs.test.employeeserv.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.service.EmployeeService;
import com.paypal.bfs.test.employeeserv.service.exception.EmployeeCreationFailedException;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementation class for employee resource.
 */
@Data
@Slf4j
@RestController
public class EmployeeResourceImpl implements EmployeeResource {

	@Autowired
	private EmployeeService employeeService;

	@Override
	public ResponseEntity<Employee> employeeGetById(String id) {
		log.debug("Getting data for id {}", id);

		return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Employee> storeEmployee(Employee employee) throws EmployeeCreationFailedException {
		log.debug("Storing data for {}", employee);

		return new ResponseEntity<>(employeeService.storeEmployee(employee), HttpStatus.CREATED);
	}

}
