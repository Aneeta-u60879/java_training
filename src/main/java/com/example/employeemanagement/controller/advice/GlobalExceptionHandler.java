package com.example.employeemanagement.controller.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.employeemanagement.exception.EmployeeManagementException;

/**
 * GlobalExceptionHandler for EmployeeManagementApplication 
 * 
 * @author 144892
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	private static final Logger Log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	/*
	 * Exception Handling for custom exception class EmployeeManagementException
	 */
	@ExceptionHandler(EmployeeManagementException.class)
	public ResponseEntity<Object> handleExcpetion(EmployeeManagementException exception) {
		Log.error("" + exception);
		return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
	}
}
