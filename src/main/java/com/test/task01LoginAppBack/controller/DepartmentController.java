package com.test.task01LoginAppBack.controller;

import com.test.task01LoginAppBack.model.Department;
import com.test.task01LoginAppBack.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public Department create(@RequestBody Department department) {
        return departmentService.create(department);
    }

    @PutMapping
    public Department update(@RequestBody Department department) {
        System.out.print("update service");
        return departmentService.update(department);
    }

    @GetMapping
    public List<Department> findAll() {
        return departmentService.findAll();
    }

    @DeleteMapping
    public String deleteEmployee(@RequestBody Department department) {
        return departmentService.deleteEmployee(department);
    }

    @GetMapping("{id}")
    public Department findById(@PathVariable Long id) {
        return departmentService.findById(id);
    }
}
