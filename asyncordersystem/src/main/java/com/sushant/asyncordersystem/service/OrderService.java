package com.sushant.asyncordersystem.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.sushant.asyncordersystem.dto.OrderRequest;
import com.sushant.asyncordersystem.dto.OrderResponse;
import com.sushant.asyncordersystem.entity.Order;

@Service
public class OrderService {
	private final Map<Long, Order> orders = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);
    
    public OrderResponse createOrder(OrderRequest orderRequest) {
    	Long id=idGenerator.getAndIncrement();
    	
    	Order order= new Order(id,
    			orderRequest.customerName(),
    			orderRequest.product(),
    			orderRequest.price(),
    			"CREATED");
    	orders.put(id,order);
    	
    	return new OrderResponse(
    			order.getId(),
    			order.getCustomerName(),
    			order.getProduct(),
    			order.getPrice(),
    			order.getStatus()
    			);
    }
    
    public List<OrderResponse> getAllOrders(){
    	return orders.values().stream()
    			.map(order-> new OrderResponse(order.getId(),order.getCustomerName(),order.getProduct(),order.getPrice(),order.getStatus()))
    			.toList();
    }
    
    public OrderResponse getOrder(Long id) {
    	Order order= orders.get(id);
    	if(order!=null) {
    		return new OrderResponse(order.getId(),order.getCustomerName(),order.getProduct(),order.getPrice(),order.getStatus());
    	}
    	return null;
    }
    
    public void deleteOrder(Long id) {
    	
    	orders.remove(id);
    	
    }
}
