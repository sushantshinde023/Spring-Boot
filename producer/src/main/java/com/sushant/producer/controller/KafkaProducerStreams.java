package com.sushant.producer.controller;

import java.util.Random;
import java.util.function.Supplier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaProducerStreams {
	@Bean
	public Supplier<RiderLocation> sendRiderLocation()
	{
		Random random= new Random();
		return ()->{
			String riderId="rider "+random.nextInt(30);
			RiderLocation location= new RiderLocation(riderId,16.7,89.3);
			System.out.println("Sending : "+location.getRiderId());
			return location;
		};
	}
}
