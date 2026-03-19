package com.sushant.crud.ecommerce.dto;

public record UserRegisterDto(
        String name,
        String email,
        String password
) {}