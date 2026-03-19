package com.sushant.crud.ecommerce.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductRequest(
		 @NotBlank(message = "Name is required")
		 String name,
		 @NotBlank(message = "Description is required")
		 String description,
		 @NotNull(message = "Price is required")
		 @Positive(message = "Price must be greater than 0")
		 BigDecimal price,
		 @NotNull(message = "Quantity is required")
		 @Min(value = 0, message = "Quantity cannot be negative")
		 Integer quantity
		
) {}
