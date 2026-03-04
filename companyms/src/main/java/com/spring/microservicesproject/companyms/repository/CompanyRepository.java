package com.spring.microservicesproject.companyms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.microservicesproject.companyms.entity.Company;



@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

}
