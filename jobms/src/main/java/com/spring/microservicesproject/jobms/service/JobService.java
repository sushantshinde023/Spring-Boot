package com.spring.microservicesproject.jobms.service;

import java.util.List;
import java.util.Optional;

import com.spring.microservicesproject.jobms.dto.JobWithCompanyDTO;
import com.spring.microservicesproject.jobms.entity.Job;



public interface JobService {
	
	List<JobWithCompanyDTO> findAll();
	void createJob(Job job);
	Optional<Job> findById(Long id);
	boolean deleteById(Long id);
	boolean updateById(Long id, Job newJob);

}
