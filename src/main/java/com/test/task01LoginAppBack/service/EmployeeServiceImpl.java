package com.test.task01LoginAppBack.service;

import com.test.task01LoginAppBack.model.Employee;
import com.test.task01LoginAppBack.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee update(Employee employee) {
        //Long id = employee.getId();
        //Employee updateEmp = employeeRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Invalid entity id: " + id));
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public String deleteEmployee(Employee employee) {
        employeeRepository.deleteById(employee.getId());
        String msg = "Employee => ID: " + employee.getId() + " name: " + employee.getFirstName() + " has been deleted";
        return msg;
    }

    @Override
    public Employee findById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Invalid entity id: " + id));
    }

}
