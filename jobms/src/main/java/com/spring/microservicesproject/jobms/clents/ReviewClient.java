package com.spring.microservicesproject.jobms.clents;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.microservicesproject.jobms.entity.external.Review;

@FeignClient("REVIEW-SERVICE")
public interface ReviewClient {
	
	@GetMapping("/reviews")
	List<Review> getAllReviews(@RequestParam("companyId") Long companyId);

}
