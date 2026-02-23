package com.springprojects.firstjobapp.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long companyId;
	private String name;
	private String description;
	
	@JsonIgnore
	@OneToMany(mappedBy = "company",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Job> jobs;
	
	public Company() {
		
	}

	public Company(Long companyId, String name, String description, List<Job> jobs) {
		super();
		this.companyId = companyId;
		this.name = name;
		this.description = description;
		this.jobs = jobs;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
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
	
	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	@Override
	public String toString() {
		return "Company [companyId=" + companyId + ", name=" + name + ", description=" + description + ", jobs=" + jobs
				+ "]";
	}
	
	

}
