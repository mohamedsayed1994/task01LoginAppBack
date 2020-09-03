package com.test.task01LoginAppBack.controller;

import com.test.task01LoginAppBack.model.Job;
import com.test.task01LoginAppBack.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {
    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    public Job create(@RequestBody Job job) {
        return jobService.create(job);
    }

    @PutMapping
    public Job update(@RequestBody Job job) {
        System.out.print("update service");
        return jobService.update(job);
    }

    @GetMapping
    public List<Job> findAll() {
        return jobService.findAll();
    }

    @DeleteMapping
    public String deleteEmployee(@RequestBody Job job) {
        return jobService.deleteEmployee(job);
    }

    @GetMapping("{id}")
    public Job findById(@PathVariable Long id) {
        return jobService.findById(id);
    }
}
