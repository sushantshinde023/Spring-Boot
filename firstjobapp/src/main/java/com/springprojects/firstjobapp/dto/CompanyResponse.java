package com.springprojects.firstjobapp.dto;

import java.util.List;
/*
 * TODO
 * */
public class CompanyResponse {
	
	private Long id;
    private String name;
    private String description;
    private List<JobResponse> jobs;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<JobResponse> getJobs() {
		return jobs;
	}
	public void setJobs(List<JobResponse> jobs) {
		this.jobs = jobs;
	}
    
    
    

}
