package com.springprojects.firstjobapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springprojects.firstjobapp.entity.Job;

public interface JobRepository extends JpaRepository<Job, Long> {

}
