package com.sushant.asyncordersystem.exception;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<?> handleOrderNotFoundException(OrderNotFoundException ex){
		
		Map<String,Object> error=Map.of(
				"status", false,
                "message", ex.getMessage(),
                "errorCode", "ORDER_NOT_FOUND");
		return ResponseEntity.status(404).body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex){

	    String errorMessage = ex.getBindingResult()
	            .getFieldErrors()
	            .get(0)
	            .getDefaultMessage();

	    Map<String,Object> error = Map.of(
	            "status", false,
	            "message", errorMessage,
	            "errorCode", "VALIDATION_ERROR"
	    );

	    return ResponseEntity.badRequest().body(error);
	}
}
