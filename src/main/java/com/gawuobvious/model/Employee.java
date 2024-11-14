package com.gawuobvious.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Builder
public class Employee {


    private String employeeId;
    private String employeeUserName;
    private String employeeFirstName;
    private String employeeLastName;
    private Integer employeeAge;
    private List<String> roles;


}
