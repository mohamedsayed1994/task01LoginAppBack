package com.test.task01LoginAppBack.service;

import com.test.task01LoginAppBack.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee create(Employee employee);

    Employee update(Employee employee);

    List<Employee> findAll();

    Employee findById(Long id);

    String deleteEmployee(Employee employee);
}
