package com.test.task01LoginAppBack.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "DEPARTMENTS")
@Getter
@Setter
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEPARTMENT_ID")
    private Long id;
    @Column(name = "DEPARTMENT_NAME")
    private String departmentName;
    @Column(name = "MANAGER_ID")
    private String managerId;

    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private List<Employee> employee;

    public Department(String departmentName, String managerId, List<Employee> employee) {
        this.departmentName = departmentName;
        this.managerId = managerId;
        this.employee = employee;
    }

    public Department(String departmentName, String managerId) {
        this.departmentName = departmentName;
        this.managerId = managerId;
    }
}
