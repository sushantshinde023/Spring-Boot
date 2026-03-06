package com.spring.microservicesproject.jobms.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/*
 * If we want to work RestTemplate to work with service name then we have to use LoadBalanced RestTemplate
 * */
@Configuration
public class AppConfig {
	
	@Bean
	@LoadBalanced //
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
