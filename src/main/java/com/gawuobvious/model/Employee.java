package com.gawuobvious.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;


@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Employee {



    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    private String employeeId;

    @Column(unique = true, nullable = false)
    private String employeeUserName;

    @Column(nullable = false)
    private String employeeFirstName;

    @Column(nullable = false)
    private String employeeLastName;
    private Integer employeeAge;

    @ElementCollection
    private List<String> roles;


}
