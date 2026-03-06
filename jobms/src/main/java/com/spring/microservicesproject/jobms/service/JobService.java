package com.spring.microservicesproject.jobms.service;

import java.util.List;
import java.util.Optional;

import com.spring.microservicesproject.jobms.dto.JobDTO;
import com.spring.microservicesproject.jobms.entity.Job;



public interface JobService {
	
	List<JobDTO> findAll();
	void createJob(Job job);
	JobDTO findById(Long id);
	boolean deleteById(Long id);
	boolean updateById(Long id, Job newJob);

}
