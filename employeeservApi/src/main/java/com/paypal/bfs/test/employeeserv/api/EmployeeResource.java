package com.paypal.bfs.test.employeeserv.api;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paypal.bfs.test.employeeserv.api.model.Employee;

/**
 * Interface for employee resource operations.
 */
public interface EmployeeResource {

    /**
     * Retrieves the {@link Employee} resource by id.
     *
     * @param id employee id.
     * @return {@link Employee} resource.
     */
    @RequestMapping("/v1/bfs/employees/{id}")
    ResponseEntity<Employee> employeeGetById(@PathVariable("id") @NotBlank String id);

    /**
     * Store Employee information and return newly created {@link Employee}.
     * @param employee
     * @return {@link Employee} resource.
     */
    @RequestMapping("/v1/bfs/employee")
    ResponseEntity<Employee> storeEmployee(@Valid @RequestBody Employee employee);
}
