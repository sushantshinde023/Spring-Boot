package com.springprojects.firstjobapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springprojects.firstjobapp.entity.Job;

@Service
public class JobServiceImpl implements JobService{
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

}
