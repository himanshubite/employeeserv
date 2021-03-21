package com.paypal.bfs.test.employeeserv.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class EmployeeDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final String GET_EMPLOYEE_BY_ID = "select e.id, e.first_name, e.last_name, e.date_of_birth, a.state, a.line1, a.line2, a.city, a.zipp_code from Employee e, Address a where e.id = ? and e.address_id = a.address_id";
	
	private static final String STORE_ADDRESS = "Insert into ADDRESS (line1, line2, city, state, zipp_code) values (?, ?, ?, ?, ?)";
	private static final String STORE_EMPLOYEE = "Insert into Employee (first_name, last_name, date_of_birth, address_id) values (?, ?, ?, ?)";
	
	/**
	 * @param Employee id
	 * @return Employee if found otherwise return an empty Employee Object
	 */
	public Employee getEmployeeById(int id) {
		log.info("Querying data for employeeId {}", id);
		
		Employee employee = new Employee();
		jdbcTemplate.query(GET_EMPLOYEE_BY_ID, new Object[]{id}, rs -> {
			Address address = new Address();
			address.setCity(rs.getString("city"));
			address.setLine1(rs.getString("line1"));
			address.setLine2(rs.getString("line2"));
			address.setState(rs.getString("state"));
			address.setZippCode(rs.getInt("zipp_code"));
			employee.setId(rs.getInt("id"));
			employee.setFirstName(rs.getString("first_name"));
			employee.setLastName(rs.getString("last_name"));
			employee.setDateOfBirth(rs.getString("date_of_birth"));
			employee.setAddress(address);
		});
		
		return employee;
	}
	
	/**
	 * This method will accept Employee Object, Employee Id is Auto incremented field. So assumption is that user will submit all Employee Attributes
	 *  except ID and after Employee is stored another Employee Object will be returned with Employee Id.  
	 *  Here we are using 2 separate query for Address and Employee, In actual application we can use an stored proc for better performance. 
	 * @param employee
	 * @return Employee
	 * @throws SQLException
	 */
	public Employee storeEmployee(Employee employee) throws SQLException {
	
			PreparedStatement preparedStatement = jdbcTemplate.getDataSource().getConnection()
					.prepareStatement(STORE_ADDRESS, Statement.RETURN_GENERATED_KEYS);
			Address address = employee.getAddress();
			preparedStatement.setString(1, address.getLine1());
			preparedStatement.setString(2, address.getLine2());
			preparedStatement.setString(3, address.getCity());
			preparedStatement.setString(4, address.getState());
			preparedStatement.setInt(5, address.getZippCode());
			preparedStatement.executeUpdate();
			ResultSet keys = preparedStatement.getGeneratedKeys();
			if (keys.next()) {
				preparedStatement = jdbcTemplate.getDataSource().getConnection().prepareStatement(STORE_EMPLOYEE,
						Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, employee.getFirstName());
				preparedStatement.setString(2, employee.getLastName());
				preparedStatement.setString(3, employee.getDateOfBirth());
				preparedStatement.setInt(4, keys.getInt(1));
				preparedStatement.execute();
				ResultSet empIdResultSet = preparedStatement.getGeneratedKeys();
				if (empIdResultSet.next()) {
					employee.setId(empIdResultSet.getInt(1));
				}
			}
		return employee;
	}
}
