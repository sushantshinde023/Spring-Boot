package com.springprojects.firstjobapp.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springprojects.firstjobapp.entity.Review;
import com.springprojects.firstjobapp.service.ReviewService;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewsController {
	
	@Autowired
	private ReviewService reviewService;
	
	@GetMapping("/reviews")
	public ResponseEntity<List<Review>> getReviews(@PathVariable Long companyId){
		
		return new ResponseEntity<>(reviewService.getAllReviews(companyId),HttpStatus.OK);
		
	}
	
	@PostMapping("/reviews")
	public ResponseEntity<String> addReview(@PathVariable Long companyId,@RequestBody Review review) {
		boolean isReviewAdded=reviewService.createReview(companyId,review);
		if(isReviewAdded)
			return new ResponseEntity<>("Review Added Successfully",HttpStatus.CREATED);
		else
			return  new ResponseEntity<>("Review Not Added ",HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/reviews/{reviewId}")
	public ResponseEntity<Review> getReview(@PathVariable Long companyId,@PathVariable Long reviewId){
		
		return new ResponseEntity<>(reviewService.findById(companyId,reviewId),HttpStatus.OK);
		
	}
	@PutMapping("/reviews/{reviewId}")
	public ResponseEntity<String> updateReview(@PathVariable Long companyId,@PathVariable Long reviewId,@RequestBody Review review) {
		boolean isReviewUpdated=reviewService.updateReview(companyId,reviewId,review);
		if(isReviewUpdated)
			return new ResponseEntity<>("Review Updated Successfully",HttpStatus.CREATED);
		else
			return  new ResponseEntity<>("Review Not Updated ",HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/reviews/{reviewId}")
	public ResponseEntity<String> deleteReview(@PathVariable Long companyId,@PathVariable Long reviewId){
		
		boolean isReviewDeleted=reviewService.deleteById(companyId,reviewId);
		if(isReviewDeleted)
			return new ResponseEntity<>("Review Deleted Successfully",HttpStatus.CREATED);
		else
			return  new ResponseEntity<>("Review Not Deleted ",HttpStatus.NOT_FOUND);
		
		
	}

}
