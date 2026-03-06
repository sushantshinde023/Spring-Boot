package com.spring.microservicesproject.jobms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.spring.microservicesproject.jobms.dto.JobWithCompanyDTO;
import com.spring.microservicesproject.jobms.entity.Job;
import com.spring.microservicesproject.jobms.entity.external.Company;
import com.spring.microservicesproject.jobms.mapper.JobMapper;
import com.spring.microservicesproject.jobms.repository.JobRepository;

import jakarta.transaction.Transactional;

@Service
public class JobServiceImpl implements JobService {
	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public List<JobWithCompanyDTO> findAll() {
		// TODO Auto-generated method stub
		List<Job> jobs=jobRepository.findAll();
		
		
		List<JobWithCompanyDTO> jobWithCompanyDto=jobs.stream().map(job->JobMapper.mapToJobWithCompanyDto(job,restTemplate.getForObject("http://COMPANYMS/companies/"+job.getCompanyId(), Company.class))).collect(Collectors.toList());
		//Company company=restTemplate.getForObject("http://localhost:8081/companies/1", Company.class);
		//System.out.println("++++++++++++++++++++++++++++++++++++"+company.getName());
		return jobWithCompanyDto;
	}

	@Override
	@Transactional
	public void createJob(Job job) {
		// TODO Auto-generated method stub
		jobRepository.save(job);
		
	}
	@Override
	public JobWithCompanyDTO findById(Long id) {
		
		// TODO Auto-generated method stub
		Job job=jobRepository.findById(id).orElse(null);
		Company company=restTemplate.getForObject("http://COMPANYMS/companies/"+job.getCompanyId(), Company.class);
		JobWithCompanyDTO jobWithCompanyDto=JobMapper.mapToJobWithCompanyDto(job, company);
		return jobWithCompanyDto;
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
