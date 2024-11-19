package com.gawuobvious.service.employee;

import com.gawuobvious.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee getEmployeeById(String employeeId);
    void deleteEmployeeById(String employeeId);
    Employee createEmployee(Employee employee);
    Employee updateEmployee(String employeeId, Employee employee);
    List<Employee> findEmployeeByFirstName(String firstName);

}
