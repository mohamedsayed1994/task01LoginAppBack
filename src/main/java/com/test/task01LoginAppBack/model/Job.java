package com.test.task01LoginAppBack.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "JOBS")
@Getter
@Setter
@NoArgsConstructor
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "JOB_ID")
    private Long id;
    @Column(name = "JOB_TITLE")
    private String jobTitle;

    @OneToMany(mappedBy = "job")
    @JsonIgnore
    private List<Employee> employees;

    public Job(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}
