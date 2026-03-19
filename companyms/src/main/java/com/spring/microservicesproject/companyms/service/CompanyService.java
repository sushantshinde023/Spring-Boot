package com.spring.microservicesproject.companyms.service;

import java.util.List;
import java.util.Optional;

import com.spring.microservicesproject.companyms.entity.Company;
import com.spring.microservicesproject.companyms.messaging.dto.ReviewMessage;



public interface CompanyService {
	
	List<Company> findAll();
	void addCompany(Company company);
	Optional<Company> findById(Long id);
	boolean deleteById(Long id);
	boolean updateById(Long id, Company company);
	public void updateCompanyRating(ReviewMessage reviewMessage);

}
