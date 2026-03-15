package com.sushant.consumer.util;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

	@KafkaListener(topics = "my-topic", groupId="my-group")
	public void listen(String msg) {
		System.out.println(" Received message is : "+msg);
	}
}
