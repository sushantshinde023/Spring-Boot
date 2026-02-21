package com.springprojects.firstjobapp.service;

import java.util.List;
import java.util.Optional;

import com.springprojects.firstjobapp.entity.Job;

public interface JobService {
	
	List<Job> findAll();
	void createJob(Job job);
	 Optional<Job> findById(Long id);

}
