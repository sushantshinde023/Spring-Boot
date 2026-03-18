package com.sushant.consumer.util;

import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConsumerNew {
	@Bean
	public Consumer<RiderLocation> processRiderLocation(){
		return location -> {
			System.out.println("Received Location :: "+location.getRiderId() +"@"+location.getLatitude()+","+location.getLongitude());
		};
	}
}
