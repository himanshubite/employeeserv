package com.paypal.bfs.test.employeeserv.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.service.impl.EmployeeServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeResourceImplTest {

	private EmployeeResourceImpl resourceImpl;
	private EmployeeServiceImpl service;
	
	@Before
	public void init() {
		resourceImpl = new EmployeeResourceImpl();
		service = PowerMockito.mock(EmployeeServiceImpl.class);
		resourceImpl.setEmployeeService(service);
	}
	
	@Test
    public void shouldReturnOkForValidGetByIdEmployeeRequest() { 
		Employee e = getDummyEmployee();
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
         
        Mockito.when(service.getEmployeeById("1")).thenReturn(e);
         
        ResponseEntity<Employee> responseEntity = resourceImpl.employeeGetById("1");
         
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(e, responseEntity.getBody());
    }
	
	@Test
    public void shouldReturnCreatedForValidStoreEmployeeRequest() { 
		Employee e = getDummyEmployee();
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
         
        Mockito.when(service.storeEmployee(e)).thenReturn(e);
         
        ResponseEntity<Employee> responseEntity = resourceImpl.storeEmployee(e);
         
        assertEquals(201, responseEntity.getStatusCodeValue());
        assertEquals(e, responseEntity.getBody());
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
