package com.springprojects.firstjobapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springprojects.firstjobapp.entity.Company;
import com.springprojects.firstjobapp.entity.Review;
import com.springprojects.firstjobapp.repository.ReviewRepository;

import jakarta.transaction.Transactional;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private CompanyService companyService;

	@Override
	public List<Review> getAllReviews(Long companyId) {
		// TODO Auto-generated method stub
		List<Review> review=reviewRepository.findByCompanyCompanyId(companyId);
		return review;
	}

	@Override
	@Transactional
	public boolean createReview(Long companyId,Review review) {
		// TODO Auto-generated method stub
		Optional<Company> company=companyService.findById(companyId);
		if(company.isPresent()) {
			review.setCompany(company.get());
			reviewRepository.save(review);
			return true;
		}else {
			return false;
		}

	}

	@Override
	public Review findById(Long companyId,Long reviewId) {
		// TODO Auto-generated method stub
		List<Review> reviewsForCompany=reviewRepository.findByCompanyCompanyId(companyId);
		
		return reviewsForCompany.stream().filter(review->review.getId().equals(reviewId)).findFirst().orElse(null);
	}

	@Override
	public boolean deleteById(Long companyId,Long reviewId) {
		// TODO Auto-generated method stub
		if(companyService.findById(companyId)!=null && reviewRepository.existsById(reviewId)) {
			
			Review review = reviewRepository.findById(reviewId).orElse(null);
			Company company=review.getCompany();
			company.getReview().remove(review);
			companyService.updateById(companyId,company);
			
			reviewRepository.deleteById(reviewId);
			return true;
		}else {
			return false;
		}
	}
	

	@Override
	public boolean updateReview(Long companyId, Long reviewId, Review updateReview) {
		// TODO Auto-generated method stub
		if(companyService.findById(companyId)!=null) {
			updateReview.setCompany(companyService.findById(companyId).get());
			updateReview.setId(reviewId);
			reviewRepository.save(updateReview);
			return true;
		}else {
			return false;
		}
		
	}



}
