package com.employee.employeemgmt.controller.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.employee.employeemgmt.exception.EmployeeMgmtException;


/**
 * Global exception handler
 * @author 144895
 *
 */
@RestControllerAdvice

public class GlobalExceptionHandler {
	
private static final Logger Log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
		
	
	@ExceptionHandler(EmployeeMgmtException.class)
	public ResponseEntity<Object> handleException(EmployeeMgmtException exception) {
		Log.error("EmployeeMgmtException occured: "  + exception);
		 return  ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
	}

}
