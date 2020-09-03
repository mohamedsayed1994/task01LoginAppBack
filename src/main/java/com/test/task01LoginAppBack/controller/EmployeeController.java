package com.test.task01LoginAppBack.controller;

import com.test.task01LoginAppBack.model.Employee;
import com.test.task01LoginAppBack.service.EmployeeService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        return employeeService.create(employee);
    }

    @PutMapping
    public Employee update(@RequestBody Employee employee) {
        System.out.print("update service");
        return employeeService.update(employee);
    }

    @GetMapping
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @DeleteMapping
    public String deleteEmployee(@RequestBody Employee employee) {
        return employeeService.deleteEmployee(employee);
    }

    @GetMapping("{id}")
    public Employee findById(@PathVariable Long id) {
        return employeeService.findById(id);
    }


}
