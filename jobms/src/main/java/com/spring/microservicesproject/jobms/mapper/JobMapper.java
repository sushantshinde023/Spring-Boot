package com.spring.microservicesproject.jobms.mapper;

import java.util.List;

import com.spring.microservicesproject.jobms.dto.JobDTO;
import com.spring.microservicesproject.jobms.entity.Job;
import com.spring.microservicesproject.jobms.entity.external.Company;
import com.spring.microservicesproject.jobms.entity.external.Review;

public class JobMapper {
	public static JobDTO mapToJobWithCompanyDto(Job job,Company company,List<Review> review) {
		
		JobDTO jobWithCompanyDto=new JobDTO();
		jobWithCompanyDto.setId(job.getId());
		jobWithCompanyDto.setTitle(job.getTitle());
		jobWithCompanyDto.setLocation(job.getLocation());
		jobWithCompanyDto.setDescription(job.getDescription());
		jobWithCompanyDto.setMaxSalary(job.getMaxSalary());
		jobWithCompanyDto.setMinSalary(job.getMinSalary());
		jobWithCompanyDto.setCompany(company);
		jobWithCompanyDto.setReviews(review);
		return jobWithCompanyDto;
		
	}
}
