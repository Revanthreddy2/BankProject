package com.common.exception;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	 @ExceptionHandler(Exception.class)
	    public ResponseEntity<?> handle(Exception ex) {
	        return ResponseEntity.badRequest().body(
	                Map.of(
	                        "timestamp", LocalDateTime.now(),
	                        "error", ex.getMessage()
	                )
	        );
	    }

}
