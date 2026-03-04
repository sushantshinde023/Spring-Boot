package com.spring.microservicesproject.jobms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.microservicesproject.jobms.entity.Job;



public interface JobRepository extends JpaRepository<Job, Long> {

}
