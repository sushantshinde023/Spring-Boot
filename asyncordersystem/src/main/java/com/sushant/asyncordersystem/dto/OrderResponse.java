package com.sushant.asyncordersystem.dto;

public record OrderResponse(
	Long id,
	String customerName,
	String productName,
	double price,
	String status
		
) {}
