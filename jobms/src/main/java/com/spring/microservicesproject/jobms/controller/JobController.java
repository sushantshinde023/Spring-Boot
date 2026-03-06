package com.spring.microservicesproject.jobms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.microservicesproject.jobms.dto.JobWithCompanyDTO;
import com.spring.microservicesproject.jobms.entity.Job;
import com.spring.microservicesproject.jobms.service.JobService;



@RestController
public class JobController {
	
	@Autowired
	private JobService jobService ;
	
	@GetMapping("/jobs")
	public ResponseEntity<List<JobWithCompanyDTO>> findAll(){
		List<JobWithCompanyDTO> jobs=jobService.findAll();
		return new ResponseEntity<>( jobService.findAll(),HttpStatus.OK);
	}
	
	@PostMapping("/jobs")
	public ResponseEntity<String> addJob(@RequestBody Job newJob) {
		jobService.createJob(newJob);
		return new ResponseEntity<>("Job Added Successfully",HttpStatus.CREATED);
	}
	
	@GetMapping("/jobs/{id}")
	public  ResponseEntity<JobWithCompanyDTO> findById(@PathVariable Long id) {
		JobWithCompanyDTO jobWithCompanyDTO= jobService.findById(id);
		 if(jobWithCompanyDTO!=null)
			 return ResponseEntity.ok(jobWithCompanyDTO);
		 else
			 return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/jobs/{id}")
	public  ResponseEntity<Long> deleteById(@PathVariable Long id) {
		boolean deleted=jobService.deleteById(id);
		
		if(deleted)
			return ResponseEntity.ok(id);
		else
			return ResponseEntity.notFound().build();
		
	}
	
	@PutMapping("/jobs/{id}")
	public  ResponseEntity<String> updateById(@PathVariable Long id,@RequestBody Job updatedJob) {
		boolean updated= jobService.updateById(id,updatedJob);
		if(updated)
			return ResponseEntity.ok("Updated Successfully");
		else
			return ResponseEntity.notFound().build();
	}
	

}
