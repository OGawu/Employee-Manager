package com.gawuobvious.controller;

import com.gawuobvious.model.Employee;
import com.gawuobvious.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;


@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private  final EmployeeService employeeService;


 
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/{employeeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> getEmployeeById(@PathVariable String employeeId) {

        Employee employee1 = employeeService.getEmployeeById(employeeId);

        if (employee1 == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


        return ResponseEntity.ok(employee1);



    }
//    private Employee getDemoEmployee() {
//
//        return Employee.builder()
//                .employeeId("1")
//                .employeeUserName("OG")
//                .employeeFirstName("Obvious")
//                .employeeLastName("Gawu")
//                .employeeAge(24)
//                .roles(Arrays.asList("employee","developer", "manager"))
//                .build();
//    }



    @GetMapping("/search-by-employee-name")
    public ResponseEntity<List<Employee>> getEmployeeByByFirstName(@RequestParam String employeeFirstName, @RequestHeader("X-API-KEY") String apiKey) {



        return ResponseEntity.ok(employeeService.findEmployeeByFirstName(employeeFirstName));

    }



    @DeleteMapping(value = "/{employeeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> deleteEmployeeById(@PathVariable String employeeId) {

        employeeService.deleteEmployeeById(employeeId);

        return ResponseEntity.status(HttpStatus.OK).build();

    }


    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {

        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.createEmployee(employee));
    }

    @PutMapping("{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable String employeeId, @RequestBody Employee employee) {


        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.updateEmployee(employeeId, employee));
    }


//    @GetMapping(value = "/search-by-first-name", produces = {MediaType.APPLICATION_JSON_VALUE} )
//    public ResponseEntity<List<Employee>> getEmployeeByEmployeesFirstName(@RequestParam String employeeFirstName) {
//
//        List<Employee> employeeList = getEmployees().stream().filter(employee -> employee.getEmployeeFirstName().equalsIgnoreCase(employeeFirstName)).collect(Collectors.toList());
//
//        return ResponseEntity.ok((employeeList));
//    }


    
}
