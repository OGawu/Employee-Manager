package com.gawuobvious.service.employee;

import com.gawuobvious.model.Employee;
import com.gawuobvious.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee getEmployeeById(String employeeId) {

        // first find the record/employee by id

        //return getDemoEmployees().stream().filter(employee -> employee.getEmployeeId().equals(employeeId)).findFirst().orElse(null);

        return employeeRepository.findById(employeeId).orElse(null);

    }

    @Override
    public void deleteEmployeeById(String employeeId) {
        // first find the record/employee by id, if it exists then delete the record

        Employee employee1 = getEmployeeById(employeeId);

        if (employee1 == null) {
            throw new RuntimeException(String.format("Employee with id %s not found", employeeId));
        }

        // logic to delete

        employeeRepository.delete(employee1);


    }

    @Override
    public Employee createEmployee(Employee employee) {

        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(String employeeId, Employee employee) {

        // first find the record/employee by id, if it exists then update the record
        return employee;
    }

    @Override
    public List<Employee> findEmployeeByFirstName(String firstName) {
        return getDemoEmployees().stream().filter(employee -> employee.getEmployeeFirstName().equalsIgnoreCase(firstName)).collect(Collectors.toList());
    }


    private Employee demoEmployee(String employeeId, String employeeUserName,
                                  String employeeFirstName,
                                  String employeeLastName,
                                  Integer employeeAge) {
        return Employee.builder()
                .employeeId(employeeId)
                .employeeUserName(employeeUserName)
                .employeeFirstName(employeeFirstName)
                .employeeLastName(employeeLastName)
                .employeeAge(employeeAge)
                .roles(Arrays.asList("developer","manager"))
                .build();
    }

    private List<Employee> getDemoEmployees() {

        List<Employee> demoEmployees = new ArrayList<>();
        demoEmployees.add(demoEmployee("1","OG","Obvious","Gawu",24));
        demoEmployees.add(demoEmployee("2","BM","Brian","Musina",25));
        demoEmployees.add(demoEmployee("3","PM","Pride","Madondo",26));
        demoEmployees.add(demoEmployee("4","RM","Rudo","Mahona",20));
        demoEmployees.add(demoEmployee("5","JB","Joachim","Baradze",27));

        return demoEmployees;
    }
}
