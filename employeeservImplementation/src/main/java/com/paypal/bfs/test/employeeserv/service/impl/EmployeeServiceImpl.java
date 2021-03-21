package com.paypal.bfs.test.employeeserv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.dao.EmployeeDao;
import com.paypal.bfs.test.employeeserv.service.EmployeeService;
import com.paypal.bfs.test.employeeserv.service.exception.EmployeeCreationFailedException;
import com.paypal.bfs.test.employeeserv.service.exception.EmployeeRetrivalException;
import com.paypal.bfs.test.employeeserv.service.exception.IllegalEmployeeIdException;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Data
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeDao employeeDao;
	
	@Override
	public Employee getEmployeeById(String id) {
		try {
			return employeeDao.getEmployeeById(Integer.parseInt(id));
		} catch (NumberFormatException nfe) {
			log.error("Error occured due to incorrect id format {}", nfe);
			throw new IllegalEmployeeIdException("Employee id should be a valid Integer value");
		} catch (Exception ex) {
			log.error("Error occured during info extraction {}", ex);
			throw new EmployeeRetrivalException("Not able to retrive employee for " + id);
		}
	}

	@Override
	public Employee storeEmployee(Employee employee) {
		try {
			return employeeDao.storeEmployee(employee);
		} catch (Exception e) {
			log.error("Error occured whilte trying to store Employee {}", e);
			throw new EmployeeCreationFailedException("Employee object not stored");
		}
	}

}
