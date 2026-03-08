package com.spring.microservicesproject.jobms.clents;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.spring.microservicesproject.jobms.entity.external.Company;

@FeignClient(name="COMPANY-SERVICE")
public interface CompanyClient {
	
	@GetMapping("/companies/{companyId}")
	Company getCompany(@PathVariable("companyId") Long companyId);

}
