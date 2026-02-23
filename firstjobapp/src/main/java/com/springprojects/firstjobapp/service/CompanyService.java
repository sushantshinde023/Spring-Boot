package com.springprojects.firstjobapp.service;

import java.util.List;
import java.util.Optional;

import com.springprojects.firstjobapp.entity.Company;


public interface CompanyService {
	
	List<Company> findAll();
	void addCompany(Company company);
	Optional<Company> findById(Long id);
	boolean deleteById(Long id);
	boolean updateById(Long id, Company company);

}
