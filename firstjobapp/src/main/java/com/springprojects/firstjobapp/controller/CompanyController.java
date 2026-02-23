package com.springprojects.firstjobapp.controller;

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

import com.springprojects.firstjobapp.entity.Company;
import com.springprojects.firstjobapp.entity.Job;
import com.springprojects.firstjobapp.service.CompanyService;

@RestController
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;
	
	@GetMapping("/companies")
	public ResponseEntity<List<Company>> findAll(){
		return new ResponseEntity<>( companyService.findAll(),HttpStatus.OK);
	}
	
	@PostMapping("/companies")
	public ResponseEntity<String> addCompany(@RequestBody Company company) {
		companyService.addCompany(company);
		return new ResponseEntity<>("Company Added Successfully",HttpStatus.CREATED);
	}
	
	@GetMapping("/companies/{id}")
	public  ResponseEntity<Company> findById(@PathVariable Long id) {
		 Optional<Company> job= companyService.findById(id);
		 if(job.isPresent())
			 return ResponseEntity.ok(job.get());
		 else
			 return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/companies/{id}")
	public  ResponseEntity<Long> deleteById(@PathVariable Long id) {
		boolean deleted=companyService.deleteById(id);
		
		if(deleted)
			return ResponseEntity.ok(id);
		else
			return ResponseEntity.notFound().build();
		
	}
	
	@PutMapping("/companies/{id}")
	public  ResponseEntity<String> updateById(@PathVariable Long id,@RequestBody Company updateCompany) {
		boolean updated= companyService.updateById(id,updateCompany);
		if(updated)
			return ResponseEntity.ok("Updated Successfully");
		else
			return ResponseEntity.notFound().build();
	}

}
