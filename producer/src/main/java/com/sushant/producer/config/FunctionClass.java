package com.sushant.producer.config;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FunctionClass {
	@Bean
	public Function<String,String> uppercase(){
		return value->value.toUpperCase();
	}
}
