package com.spring.microservicesproject.reviewms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.microservicesproject.reviewms.entity.Review;
import com.spring.microservicesproject.reviewms.messaging.ReviewMessageProducer;
import com.spring.microservicesproject.reviewms.service.ReviewService;



@RestController
public class ReviewsController {
	
	
	private ReviewService reviewService;
	private ReviewMessageProducer reviewMessageProducer;
	
	
	
	public ReviewsController(ReviewService reviewService, ReviewMessageProducer reviewMessageProducer) {
		super();
		this.reviewService = reviewService;
		this.reviewMessageProducer = reviewMessageProducer;
	}

	@GetMapping("/reviews")
	public ResponseEntity<List<Review>> getReviews(@RequestParam Long companyId){
		
		return new ResponseEntity<>(reviewService.getAllReviews(companyId),HttpStatus.OK);
		
	}
	
	@PostMapping("/reviews")
	public ResponseEntity<String> addReview(@RequestParam Long companyId,@RequestBody Review review) {
		boolean isReviewAdded=reviewService.createReview(companyId,review);
		if(isReviewAdded) {
			reviewMessageProducer.sendMessage(review);
			return new ResponseEntity<>("Review Added Successfully",HttpStatus.CREATED);
		}else {
			return  new ResponseEntity<>("Review Not Added ",HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/reviews/{reviewId}")
	public ResponseEntity<Review> getReview(@PathVariable Long reviewId){
		
		return new ResponseEntity<>(reviewService.findById(reviewId),HttpStatus.OK);
		
	}
	@PutMapping("/reviews/{reviewId}")
	public ResponseEntity<String> updateReview(@PathVariable Long reviewId,@RequestBody Review review) {
		boolean isReviewUpdated=reviewService.updateReview(reviewId,review);
		if(isReviewUpdated)
			return new ResponseEntity<>("Review Updated Successfully",HttpStatus.CREATED);
		else
			return  new ResponseEntity<>("Review Not Updated ",HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/reviews/{reviewId}")
	public ResponseEntity<String> deleteReview(@PathVariable Long reviewId){
		
		boolean isReviewDeleted=reviewService.deleteById(reviewId);
		if(isReviewDeleted)
			return new ResponseEntity<>("Review Deleted Successfully",HttpStatus.CREATED);
		else
			return  new ResponseEntity<>("Review Not Deleted ",HttpStatus.NOT_FOUND);
		
		
	}
	
	@GetMapping("/averageRating")
	public Double getAverageReview(@RequestParam Long companyId) {
		List<Review> reviewList=reviewService.getAllReviews(companyId);
		return reviewList.stream().mapToDouble(Review::getRating).average().orElse(0.0);
	}

}
