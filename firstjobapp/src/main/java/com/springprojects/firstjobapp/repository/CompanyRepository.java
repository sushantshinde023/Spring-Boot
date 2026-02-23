package com.springprojects.firstjobapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springprojects.firstjobapp.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

}
