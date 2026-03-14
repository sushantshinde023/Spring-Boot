package com.sushant.asyncordersystem.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sushant.asyncordersystem.dto.OrderRequest;
import com.sushant.asyncordersystem.dto.OrderResponse;
import com.sushant.asyncordersystem.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	private final OrderService orderService;
	
	public OrderController(OrderService orderService) {
		this.orderService=orderService;
	}
	
	@PostMapping
	public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest){
		
		OrderResponse orderResponse=orderService.createOrder(orderRequest);
		
		return ResponseEntity.ok(orderResponse);
		
	}
	
	@GetMapping
	public ResponseEntity<List<OrderResponse>> getAllOrders(){
		List<OrderResponse> allOrders=orderService.getAllOrders();
		return ResponseEntity.ok(allOrders);
	}
	
	@GetMapping("/{orderId}")
	public ResponseEntity<OrderResponse> getOrder(@PathVariable Long orderId){
		OrderResponse order= orderService.getOrder(orderId);
		if(order!=null) {
			return ResponseEntity.ok(order);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{orderId}")
	public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId){
		
		orderService.deleteOrder(orderId);
		return ResponseEntity.noContent().build();
		
	}

}
