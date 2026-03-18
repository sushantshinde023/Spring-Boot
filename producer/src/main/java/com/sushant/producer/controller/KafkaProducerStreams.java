package com.sushant.producer.controller;

import java.util.function.Supplier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaProducerStreams {
	@Bean
	public Supplier<RiderLocation> sendRiderLocation()
	{
		return ()->{
			RiderLocation location= new RiderLocation("ider234",16.7,89.3);
			System.out.println("Sending : "+location.getRiderId());
			return location;
		};
	}
}
