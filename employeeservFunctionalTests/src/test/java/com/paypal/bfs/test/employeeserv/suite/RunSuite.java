package com.paypal.bfs.test.employeeserv.suite;

 
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.paypal.bfs.test.employeeserv.test.EmployeeGetTestCase;
import com.paypal.bfs.test.employeeserv.test.EmployeePostTestCase;
 
/**
 * Use this class to run test suite
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	EmployeeGetTestCase.class,
	EmployeePostTestCase.class
})
public class RunSuite {
}
