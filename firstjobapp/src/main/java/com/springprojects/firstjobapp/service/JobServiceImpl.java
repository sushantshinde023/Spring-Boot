package com.springprojects.firstjobapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springprojects.firstjobapp.entity.Job;

@Service
public class JobServiceImpl implements JobService {
	private List<Job> jobs=new ArrayList<>(List.of(new Job(111L,"Java Developer","Should have Java Hands on experience","8L","12L","Pune")));
	private Long nextId=2L;
	@Override
	public List<Job> findAll() {
		// TODO Auto-generated method stub
		return jobs;
	}

	@Override
	public void createJob(Job job) {
		// TODO Auto-generated method stub
		job.setId(nextId++);
		jobs.add(job);
		
	}
	@Override
	public Optional<Job> findById(Long id) {
		
		// TODO Auto-generated method stub
		Optional<Job> job=jobs.stream().filter(j -> j.getId().equals(id)).findAny();
		return job ;
	}

	@Override
	public Optional<Job> deleteById(Long id) {
		Optional<Job> job=jobs.stream().filter(j -> j.getId().equals(id)).findAny();
		if(job.isPresent())
			jobs.remove(job.get());
		return job;
	}

	@Override
	public boolean updateById(Long id, Job newJob) {
		// TODO Auto-generated method stub
		
		Optional<Job> job= findById(id);
		 if(job.isPresent()) {
			 Job jobToBeUpdated=job.get();
			 jobToBeUpdated.setDescription(newJob.getDescription());
			 jobToBeUpdated.setLocation(newJob.getLocation());
			 jobToBeUpdated.setMaxSalary(newJob.getMaxSalary());
			 jobToBeUpdated.setMinSalary(newJob.getMinSalary());
			 jobToBeUpdated.setTitle(newJob.getTitle());
			 return true;
		 }else
			 return false;
	}


}
