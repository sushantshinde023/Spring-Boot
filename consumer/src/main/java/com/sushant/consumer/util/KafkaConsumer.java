package com.sushant.consumer.util;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

	@KafkaListener(topics = "my-topic-1", groupId="my-group")
	public void listen(String msg) {
		System.out.println(" Received message is : "+msg);
	}
	@KafkaListener(topics = "my-topic-1", groupId="my-group")
	public void consumerListen2(String msg) {
		System.out.println(" Received message is : "+msg);
	}
	@KafkaListener(topics = "rider", groupId="rider-group")
	public void listenRiderLocation(RiderLocation location) {
		System.out.println(" Received Location is : "+location.getRiderId()+" "+location.getLatitude()+":"+location.getLongitude());
	}
}
