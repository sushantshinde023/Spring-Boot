package com.springprojects.firstjobapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springprojects.firstjobapp.entity.Job;
import com.springprojects.firstjobapp.repository.JobRepository;

import jakarta.transaction.Transactional;

@Service
public class JobServiceImpl implements JobService {
	@Autowired
	private JobRepository jobRepository;
	
	@Override
	public List<Job> findAll() {
		// TODO Auto-generated method stub
		return jobRepository.findAll();
	}

	@Override
	@Transactional
	public void createJob(Job job) {
		// TODO Auto-generated method stub
		jobRepository.save(job);
		
	}
	@Override
	public Optional<Job> findById(Long id) {
		
		// TODO Auto-generated method stub
		return jobRepository.findById(id) ;
	}

	@Override
	public boolean deleteById(Long id) {
		try {
			jobRepository.deleteById(id);
			return true;
		}catch(Exception e) {
			return false;
		}
		 
	}

	@Override
	@Transactional
	public boolean updateById(Long id, Job newJob) {
		// TODO Auto-generated method stub
		
		Optional<Job> job= jobRepository.findById(id);
		 if(job.isPresent()) {
			 Job jobToBeUpdated=job.get();
			 jobToBeUpdated.setDescription(newJob.getDescription());
			 jobToBeUpdated.setLocation(newJob.getLocation());
			 jobToBeUpdated.setMaxSalary(newJob.getMaxSalary());
			 jobToBeUpdated.setMinSalary(newJob.getMinSalary());
			 jobToBeUpdated.setTitle(newJob.getTitle());
			 jobToBeUpdated.setCompany(newJob.getCompany());
			 jobRepository.save(jobToBeUpdated);
			 return true;
		 }else
			 return false;
	}


}
