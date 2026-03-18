package com.sushant.producer.controller;

//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api")
public class KafkaProducer {
	
//	private final KafkaTemplate<String, RiderLocation> kafkaTemplate;
//	public KafkaProducer(KafkaTemplate<String, RiderLocation> kafkaTemplate) {
//		this.kafkaTemplate=kafkaTemplate;
//		
//	}
// 
//	
//	/*@PostMapping("/send")
//	public String sendMessage(@RequestParam String msg) {
//		kafkaTemplate.send("my-topic-1",msg);
//		return "Message-sent: "+msg;
//		
//	}*/
//	
//	@PostMapping("/rider")
//	public String sendLocation() {
//		
//		RiderLocation location=new RiderLocation("rider1",28.61,77.23);
//		kafkaTemplate.send("rider",location);
//		return "Rider id is : "+location.getRiderId();
//		
//	}


}
