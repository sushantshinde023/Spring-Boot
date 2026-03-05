package com.spring.microservicesproject.jobms.dto;

import com.spring.microservicesproject.jobms.entity.Job;
import com.spring.microservicesproject.jobms.entity.external.Company;

public class JobWithCompanyDTO {
	private Job job;
	private Company company;
	
	
	public JobWithCompanyDTO(Job job, Company company) {
		super();
		this.job = job;
		this.company = company;
	}
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	

}
