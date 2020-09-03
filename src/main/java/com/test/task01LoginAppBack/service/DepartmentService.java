package com.test.task01LoginAppBack.service;

import com.test.task01LoginAppBack.model.Department;

import java.util.List;

public interface DepartmentService {
    Department create(Department department);

    Department update(Department department);

    List<Department> findAll();

    Department findById(Long id);

    String deleteEmployee(Department department);
}
