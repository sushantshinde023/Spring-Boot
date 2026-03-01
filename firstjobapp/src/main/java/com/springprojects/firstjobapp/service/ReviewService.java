package com.springprojects.firstjobapp.service;

import java.util.List;
import java.util.Optional;

import com.springprojects.firstjobapp.entity.Review;

public interface ReviewService {
	

	Review findById(Long companyId, Long reviewId);
	boolean deleteById(Long companyId, Long reviewId);
	boolean updateReview(Long companyId,Long reviewId, Review review);
	List<Review> getAllReviews(Long companyId);
	boolean createReview(Long companyId, Review review);
}
