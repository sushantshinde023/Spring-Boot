package com.spring.microservicesproject.jobms.mapper;

import com.spring.microservicesproject.jobms.dto.JobWithCompanyDTO;
import com.spring.microservicesproject.jobms.entity.Job;
import com.spring.microservicesproject.jobms.entity.external.Company;

public class JobMapper {
	public static JobWithCompanyDTO mapToJobWithCompanyDto(Job job,Company company) {
		
		JobWithCompanyDTO jobWithCompanyDto=new JobWithCompanyDTO();
		jobWithCompanyDto.setId(job.getId());
		jobWithCompanyDto.setTitle(job.getTitle());
		jobWithCompanyDto.setLocation(job.getLocation());
		jobWithCompanyDto.setDescription(job.getDescription());
		jobWithCompanyDto.setMaxSalary(job.getMaxSalary());
		jobWithCompanyDto.setMinSalary(job.getMinSalary());
		jobWithCompanyDto.setCompany(company);
		return jobWithCompanyDto;
		
	}
}
