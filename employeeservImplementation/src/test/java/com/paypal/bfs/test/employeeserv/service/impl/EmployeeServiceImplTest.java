package com.paypal.bfs.test.employeeserv.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.dao.EmployeeDao;
import com.paypal.bfs.test.employeeserv.service.exception.EmployeeCreationFailedException;
import com.paypal.bfs.test.employeeserv.service.exception.EmployeeRetrivalException;
import com.paypal.bfs.test.employeeserv.service.exception.IllegalEmployeeIdException;

@RunWith(PowerMockRunner.class)
@PrepareForTest(EmployeeServiceImpl.class)
public class EmployeeServiceImplTest {

	private EmployeeServiceImpl service;
	private EmployeeDao employeeDao;

	@Before
	public void init() {
		service = new EmployeeServiceImpl();
		employeeDao = PowerMockito.mock(EmployeeDao.class);
		service.setEmployeeDao(employeeDao);
	}

	@Test
	public void shouldGetEmployeeByIdForValidId() throws Exception {
		Employee e = getDummyEmployee();
		PowerMockito.doReturn(e).when(employeeDao, "getEmployeeById", 1);
		assertEquals(e, service.getEmployeeById("1"));
		Mockito.verify(employeeDao, times(1)).getEmployeeById(1);
	}

	@Test(expected = IllegalEmployeeIdException.class)
	public void shouldThrowExceptionForInvalidId() throws Exception {
		Employee e = getDummyEmployee();
		PowerMockito.doReturn(e).when(employeeDao, "getEmployeeById", 1);
		service.getEmployeeById("K");
	}

	@Test(expected = EmployeeRetrivalException.class)
	public void shouldThrowExceptionForDaoError() throws Exception {
		PowerMockito.doThrow(new RuntimeException()).when(employeeDao, "getEmployeeById", 1);
		service.getEmployeeById("1");
	}

	@Test
	public void employeeShouldBeCreatedIfRequestIsValid() throws Exception {
		Employee e = getDummyEmployee();
		PowerMockito.doReturn(e).when(employeeDao, "storeEmployee", any());
		assertEquals(e, service.storeEmployee(e));
		Mockito.verify(employeeDao, times(1)).storeEmployee(e);
	}

	@Test(expected = EmployeeCreationFailedException.class)
	public void shouldThrowExceptionForStorageError() throws Exception {
		PowerMockito.doThrow(new RuntimeException()).when(employeeDao, "storeEmployee", any());
		service.storeEmployee(getDummyEmployee());
	}

	private Employee getDummyEmployee() {
		Employee e = new Employee();
		e.setId(1);
		e.setFirstName("RRR");
		e.setLastName("KKK");
		e.setDateOfBirth("1988-03-20");
		e.setAddress(new Address());
		return null;
	}
}
