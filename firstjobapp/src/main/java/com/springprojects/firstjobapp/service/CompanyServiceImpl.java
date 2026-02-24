package com.springprojects.firstjobapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springprojects.firstjobapp.entity.Company;
import com.springprojects.firstjobapp.repository.CompanyRepository;

import jakarta.transaction.Transactional;

@Service
public class CompanyServiceImpl implements CompanyService{
	
	@Autowired
	private CompanyRepository companyRepository;

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
			 companyToBeUpdated.setJobs(company.getJobs());
			 companyRepository.save(companyToBeUpdated);
			 return true;
		 }else
			 return false;
	}

}
