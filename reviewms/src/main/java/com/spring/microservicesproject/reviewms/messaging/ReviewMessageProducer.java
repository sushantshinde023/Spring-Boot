package com.spring.microservicesproject.reviewms.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.spring.microservicesproject.reviewms.entity.Review;
import com.spring.microservicesproject.reviewms.messaging.dto.ReviewMessage;

@Service
public class ReviewMessageProducer {
	
	private RabbitTemplate rabbitTemplate;

	public ReviewMessageProducer(RabbitTemplate rabbitTemplate) {
		super();
		this.rabbitTemplate = rabbitTemplate;
	}
	
	public void sendMessage(Review review){
		
		ReviewMessage reviewMessage=new ReviewMessage(
				review.getId(),
				review.getTitle(),
				review.getDescription(),
				review.getRating(),
				review.getCompanyId()
				);
		rabbitTemplate.convertAndSend("companyRatingQueue",reviewMessage);
	}

}
