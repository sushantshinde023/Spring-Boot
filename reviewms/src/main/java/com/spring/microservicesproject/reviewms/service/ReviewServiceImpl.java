package com.spring.microservicesproject.reviewms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.microservicesproject.reviewms.entity.Review;
import com.spring.microservicesproject.reviewms.repository.ReviewRepository;

import jakarta.transaction.Transactional;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	private ReviewRepository reviewRepository;
	

	@Override
	public List<Review> getAllReviews(Long companyId) {
		// TODO Auto-generated method stub
		List<Review> review=reviewRepository.findByCompanyId(companyId);
		return review;
	}

	@Override
	@Transactional
	public boolean createReview(Long companyId,Review review) {
		// TODO Auto-generated method stub
		
		if(companyId!=null && review!=null) {
			review.setCompanyId(companyId);
			reviewRepository.save(review);
			return true;
		}else {
			return false;
		}

	}

	@Override
	public Review findById(Long reviewId) {
		// TODO Auto-generated method stub
		Review review=reviewRepository.findById(reviewId).orElse(null);
		
		return review;
	}

	@Override
	public boolean deleteById(Long reviewId) {
		// TODO Auto-generated method stub
		Review  review =reviewRepository.findById(reviewId).orElse(null);
		if(review!=null ) {
			
			reviewRepository.deleteById(reviewId);
			return true;
		}else {
			return false;
		}
	}
	

	@Override
	public boolean updateReview(Long reviewId, Review updateReview) {
		// TODO Auto-generated method stub
		Review review=reviewRepository.findById(reviewId).orElse(null);
		if(review!=null) {
			
			review.setTitle(updateReview.getTitle());
			review.setDescription(updateReview.getDescription());
			review.setRating(updateReview.getRating());
			review.setCompanyId(updateReview.getCompanyId());
			reviewRepository.save(review);
			return true;
		}else {
			return false;
		}
		
	}



}
