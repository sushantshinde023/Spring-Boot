package com.spring.microservicesproject.jobms.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.spring.microservicesproject.jobms.clents.CompanyClient;
import com.spring.microservicesproject.jobms.clents.ReviewClient;
import com.spring.microservicesproject.jobms.dto.JobDTO;
import com.spring.microservicesproject.jobms.entity.Job;
import com.spring.microservicesproject.jobms.entity.external.Company;
import com.spring.microservicesproject.jobms.entity.external.Review;
import com.spring.microservicesproject.jobms.mapper.JobMapper;
import com.spring.microservicesproject.jobms.repository.JobRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.transaction.Transactional;

@Service
public class JobServiceImpl implements JobService {
	@Autowired
	private JobRepository jobRepository;

	
	@Autowired
	private CompanyClient companyClient;
	@Autowired
	private ReviewClient reviewClient;
	
	@Override
	@CircuitBreaker(name="companyBreaker")
	public List<JobDTO> findAll() {
		// TODO Auto-generated method stub
		List<Job> jobs=jobRepository.findAll();
		
		
		List<JobDTO> jobDto=jobs.stream().map(job->JobMapper.mapToJobWithCompanyDto(job,companyClient.getCompany(job.getCompanyId()),reviewClient.getAllReviews(job.getCompanyId()))).collect(Collectors.toList());
		//Company company=restTemplate.getForObject("http://localhost:8081/companies/1", Company.class);
		//System.out.println("++++++++++++++++++++++++++++++++++++"+company.getName());
		return jobDto;
	}

	@Override
	@Transactional
	public void createJob(Job job) {
		// TODO Auto-generated method stub
		jobRepository.save(job);
		
	}
	@Override
	public JobDTO findById(Long id) {
		
		// TODO Auto-generated method stub
		Job job=jobRepository.findById(id).orElse(null);
		Company company=companyClient.getCompany(job.getCompanyId());
		List<Review> reviewResponse=reviewClient.getAllReviews(job.getCompanyId());
		JobDTO jobDto=JobMapper.mapToJobWithCompanyDto(job, company,reviewResponse);
		return jobDto;
	}

	@Override
	public boolean deleteById(Long id) {
		try {
			jobRepository.deleteById(id);
			return true;
		}catch(Exception e) {
			return false;
		}
		 
	}

	@Override
	@Transactional
	public boolean updateById(Long id, Job newJob) {
		// TODO Auto-generated method stub
		
		Optional<Job> job= jobRepository.findById(id);
		 if(job.isPresent()) {
			 Job jobToBeUpdated=job.get();
			 jobToBeUpdated.setDescription(newJob.getDescription());
			 jobToBeUpdated.setLocation(newJob.getLocation());
			 jobToBeUpdated.setMaxSalary(newJob.getMaxSalary());
			 jobToBeUpdated.setMinSalary(newJob.getMinSalary());
			 jobToBeUpdated.setTitle(newJob.getTitle());
			 jobToBeUpdated.setCompanyId(newJob.getCompanyId());
			 jobRepository.save(jobToBeUpdated);
			 return true;
		 }else
			 return false;
	}


}
