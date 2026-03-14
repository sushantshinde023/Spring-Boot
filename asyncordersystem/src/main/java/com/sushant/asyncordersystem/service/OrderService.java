package com.sushant.asyncordersystem.service;


import java.util.List;
import org.springframework.stereotype.Service;

import com.sushant.asyncordersystem.dto.OrderRequest;
import com.sushant.asyncordersystem.dto.OrderResponse;
import com.sushant.asyncordersystem.entity.Order;
import com.sushant.asyncordersystem.exception.OrderNotFoundException;
import com.sushant.asyncordersystem.repository.OrderRepository;

@Service
public class OrderService {
	private final OrderRepository orderRepository;
    
    public OrderService(OrderRepository orderRepository) {
    	this.orderRepository=orderRepository;
    }
    
    public OrderResponse createOrder(OrderRequest orderRequest) {
    	
    	Order order= new Order(
    			orderRequest.customerName(),
    			orderRequest.product(),
    			orderRequest.price(),
    			"CREATED");
    	Order savedOrder=orderRepository.save(order);
    	return new OrderResponse(
    			savedOrder.getId(),
    			savedOrder.getCustomerName(),
    			savedOrder.getProduct(),
    			savedOrder.getPrice(),
    			savedOrder.getStatus()
    			);
    }
    
    public List<OrderResponse> getAllOrders(){
    	
    	return orderRepository.findAll().stream()
    			.map(order-> new OrderResponse(order.getId(),order.getCustomerName(),order.getProduct(),order.getPrice(),order.getStatus()))
    			.toList();
    }
    
    public OrderResponse getOrder(Long id) {
    	Order order= orderRepository.findById(id).orElseThrow(()->new OrderNotFoundException("Order With id"+id+" not found"));
    
    	return new OrderResponse(order.getId(),order.getCustomerName(),order.getProduct(),order.getPrice(),order.getStatus());
   
    }
    
    public void deleteOrder(Long id) {
    	
    	orderRepository.deleteById(id);
    	
    }
}
