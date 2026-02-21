package com.springprojects.firstjobapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springprojects.firstjobapp.entity.Job;

@RestController
public class JobController {
	
	private List<Job> jobs=new ArrayList<>(List.of(new Job(111L,"Java Developer","Should have Java Hands on experience","8L","12L","Pune")));
	
	@GetMapping("/jobs")
	public List<Job> findAll(){
		
		
		return jobs;
	}
	
	@PostMapping("/jobs")
	public String addJob(@RequestBody Job newJob) {
		jobs.add(newJob);
		return "Job Added Successfully";
	}

}
