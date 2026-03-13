package com.springsecurity.springsecuritydemo.exception;

import java.util.HashMap;
import java.util.Map;

import javax.security.sasl.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> handleAuthException(AuthenticationException ex){

        Map<String,Object> map=new HashMap<>();
        map.put("message","Bad Credentials");
        map.put("status",false);

        return new ResponseEntity<>(map,HttpStatus.UNAUTHORIZED);
    }
}