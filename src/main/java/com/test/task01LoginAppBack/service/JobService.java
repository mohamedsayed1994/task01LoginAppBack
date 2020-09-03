package com.test.task01LoginAppBack.service;

import com.test.task01LoginAppBack.model.Job;

import java.util.List;

public interface JobService {
    Job create(Job job);

    Job update(Job job);

    List<Job> findAll();

    Job findById(Long id);

    String deleteEmployee(Job job);
}
