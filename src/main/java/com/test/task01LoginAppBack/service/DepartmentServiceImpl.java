package com.test.task01LoginAppBack.service;

import com.test.task01LoginAppBack.model.Department;
import com.test.task01LoginAppBack.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department create(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department update(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department findById(Long id) {
        return departmentRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Invalid entity id: " + id));
    }

    @Override
    public String deleteEmployee(Department department) {
        departmentRepository.deleteById(department.getId());
        String msg = "Employee => ID: " + department.getId() + " name: " + department.getDepartmentName() + " has been deleted";
        return msg;
    }
}
