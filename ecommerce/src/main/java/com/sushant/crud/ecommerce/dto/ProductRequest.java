package com.sushant.crud.ecommerce.dto;

import java.math.BigDecimal;

public record ProductRequest(
		 String name,
		 String description,
		 BigDecimal price,
		 Integer quantity
		
) {}
