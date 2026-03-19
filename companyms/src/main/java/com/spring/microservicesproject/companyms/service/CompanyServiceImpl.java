package com.spring.microservicesproject.companyms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.microservicesproject.companyms.client.ReviewClient;
import com.spring.microservicesproject.companyms.entity.Company;
import com.spring.microservicesproject.companyms.messaging.dto.ReviewMessage;
import com.spring.microservicesproject.companyms.repository.CompanyRepository;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@Service
public class CompanyServiceImpl implements CompanyService{
	
	
	private CompanyRepository companyRepository;
	
	private ReviewClient reviewClient;
	
	

	public CompanyServiceImpl(CompanyRepository companyRepository, ReviewClient reviewClient) {
		super();
		this.companyRepository = companyRepository;
		this.reviewClient = reviewClient;
	}

	@Override
	public List<Company> findAll() {
		// TODO Auto-generated method stub
		return companyRepository.findAll();
	}

	@Override
	@Transactional
	public void addCompany(Company company) {
		// TODO Auto-generated method stub
		companyRepository.save(company);
		
	}

	@Override
	public Optional<Company> findById(Long id) {
		// TODO Auto-generated method stub
		return companyRepository.findById(id);
	}

	@Override
	public boolean deleteById(Long id) {
		if(companyRepository.existsById(id)) {
			companyRepository.deleteById(id);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean updateById(Long id, Company company) {
		Optional<Company> retrievedCompany= companyRepository.findById(id);
		 if(retrievedCompany.isPresent()) {
			 Company companyToBeUpdated=retrievedCompany.get();
			 companyToBeUpdated.setDescription(company.getDescription());
			 companyToBeUpdated.setName(company.getName());
			 companyRepository.save(companyToBeUpdated);
			 return true;
		 }else
			 return false;
	}

	@Override
	public void updateCompanyRating(ReviewMessage reviewMessage) {
		// TODO Auto-generated method stub
		
		//To calculate Review we don't have all the reviews so will call get reviews for the company and calculate
		Company company=companyRepository.findById(reviewMessage.getCompanyId()).orElseThrow(()-> new NotFoundException("Company Not found"+reviewMessage.getCompanyId()));
		
		double avgRating=reviewClient.getAverageReview(company.getCompanyId());
		company.setRating(avgRating);
		companyRepository.save(company);
	
		
	}

}
