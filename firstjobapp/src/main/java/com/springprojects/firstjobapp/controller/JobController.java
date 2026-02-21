package com.springprojects.firstjobapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springprojects.firstjobapp.entity.Job;
import com.springprojects.firstjobapp.service.JobService;

@RestController
public class JobController {
	
	@Autowired
	private JobService jobService ;
	
	@GetMapping("/jobs")
	public List<Job> findAll(){
		
		
		return jobService.findAll();
	}
	
	@PostMapping("/jobs")
	public String addJob(@RequestBody Job newJob) {
		jobService.createJob(newJob);
		return "Job Added Successfully";
	}
	
	@GetMapping("/jobs/{id}")
	public  Optional<Job> findById(@PathVariable Long id) {
		return jobService.findById( id);
	}

}
