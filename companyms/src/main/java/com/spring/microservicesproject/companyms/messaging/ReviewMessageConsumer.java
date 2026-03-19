package com.spring.microservicesproject.companyms.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.spring.microservicesproject.companyms.messaging.dto.ReviewMessage;
import com.spring.microservicesproject.companyms.service.CompanyService;

@Service
public class ReviewMessageConsumer {
	
	private final CompanyService companyService;

	public ReviewMessageConsumer(CompanyService companyService) {
		super();
		this.companyService = companyService;
	}
	
	
	@RabbitListener(queues = "companyRatingQueue")
	public void consumeMessage(ReviewMessage reviewMessage) {
		companyService.updateCompanyRating(reviewMessage);
	}

}
