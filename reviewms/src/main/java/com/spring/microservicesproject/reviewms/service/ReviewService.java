package com.spring.microservicesproject.reviewms.service;

import java.util.List;
import java.util.Optional;

import com.spring.microservicesproject.reviewms.entity.Review;



public interface ReviewService {
	

	Review findById(Long reviewId);
	boolean deleteById(Long reviewId);
	boolean updateReview(Long reviewId, Review review);
	List<Review> getAllReviews(Long companyId);
	boolean createReview(Long companyId,Review review);
}
