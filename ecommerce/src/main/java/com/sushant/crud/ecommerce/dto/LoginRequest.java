package com.sushant.crud.ecommerce.dto;
public record LoginRequest(
        String email,
        String password
) {}