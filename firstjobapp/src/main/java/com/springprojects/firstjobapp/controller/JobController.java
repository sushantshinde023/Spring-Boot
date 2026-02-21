package com.springprojects.firstjobapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springprojects.firstjobapp.entity.Job;
import com.springprojects.firstjobapp.service.JobService;

@RestController
public class JobController {
	
	@Autowired
	private JobService jobService ;
	
	@GetMapping("/jobs")
	public ResponseEntity<List<Job>> findAll(){
		
		
		return new ResponseEntity<>( jobService.findAll(),HttpStatus.OK);
	}
	
	@PostMapping("/jobs")
	public ResponseEntity<String> addJob(@RequestBody Job newJob) {
		jobService.createJob(newJob);
		return new ResponseEntity<>("Job Added Successfully",HttpStatus.CREATED);
	}
	
	@GetMapping("/jobs/{id}")
	public  ResponseEntity<Job> findById(@PathVariable Long id) {
		 Optional<Job> job= jobService.findById( id);
		 if(job.isPresent())
			 return ResponseEntity.ok(job.get());
		 else
			 return ResponseEntity.notFound().build();
	}

}
