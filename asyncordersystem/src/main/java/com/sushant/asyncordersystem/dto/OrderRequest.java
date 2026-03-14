package com.sushant.asyncordersystem.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record OrderRequest(
		@NotBlank(message = "Customer name cannot be empty")
		String customerName,
		@NotBlank(message = "Product cannot be empty")
        String product,
        @Min(value = 1, message = "Price must be greater than zero")
        double price
) {}
