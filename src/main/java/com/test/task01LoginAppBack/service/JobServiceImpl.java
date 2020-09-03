package com.test.task01LoginAppBack.service;

import com.test.task01LoginAppBack.model.Job;
import com.test.task01LoginAppBack.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class JobServiceImpl implements JobService {
    JobRepository jobRepository;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public Job create(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public Job update(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public Job findById(Long id) {
        return jobRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Invalid entity id: " + id));
    }

    @Override
    public String deleteEmployee(Job job) {
        jobRepository.deleteById(job.getId());
        String msg = "Employee => ID: " + job.getId() + " name: " + job.getJobTitle() + " has been deleted";
        return msg;
    }
}
