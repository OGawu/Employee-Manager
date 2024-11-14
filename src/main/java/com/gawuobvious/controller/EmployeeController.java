package com.gawuobvious.controller;

import com.gawuobvious.model.Employee;
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

    @GetMapping(value = "/{employeeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> getEmployeeById(@PathVariable String employeeId) {

        // first find the record/employee by id

        Employee employee1 = getDemoEmployees().stream().filter(employee -> employee.getEmployeeId().equals(employeeId)).findFirst().orElse(null);

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


    @GetMapping("/search-by-employee-name")
    public ResponseEntity<List<Employee>> getEmployeeByByFirstName(@RequestParam String employeeFirstName) {

        List<Employee> employeeList = getDemoEmployees().stream().filter(employee -> employee.getEmployeeFirstName().equalsIgnoreCase(employeeFirstName)).collect(Collectors.toList());

        return ResponseEntity.ok(employeeList);

    }



    @DeleteMapping(value = "/{employeeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> deleteEmployeeById(@PathVariable String employeeId) {

        // first find the record/employee by id, if it exists then delete the record

        Employee employee1 = getDemoEmployees().stream().filter(employee -> employee.getEmployeeId().equals(employeeId)).findFirst().orElse(null);

        if (employee1 == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();

    }


    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {

        /* check if the username exists
        throw an exception that the employee already exist

        or create a new employee
         */

        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }

    @PutMapping("{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable String employeeId, @RequestBody Employee employee) {

        // first find the record/employee by id, if it exists then update the record

        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }


//    @GetMapping(value = "/search-by-first-name", produces = {MediaType.APPLICATION_JSON_VALUE} )
//    public ResponseEntity<List<Employee>> getEmployeeByEmployeesFirstName(@RequestParam String employeeFirstName) {
//
//        List<Employee> employeeList = getEmployees().stream().filter(employee -> employee.getEmployeeFirstName().equalsIgnoreCase(employeeFirstName)).collect(Collectors.toList());
//
//        return ResponseEntity.ok((employeeList));
//    }


    
}
