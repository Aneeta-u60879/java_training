package com.eleena.Employee.controller.advice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory; 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.eleena.Employee.exception.EmployeeMgmntException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	private static final Logger Log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
		
	
	@ExceptionHandler(EmployeeMgmntException.class)
	public ResponseEntity<Object> handleException(EmployeeMgmntException exception) {
		Log.error("EmployeeMgmtException occured: "  + exception);
		 return  ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
	}
//	@ExceptionHandler(EmployeeMgmntException.class)
//	public ResponseEntity<Object> handleEmployException(EmployeeMgmntException exception) {
//		Log.error("EmployeeMgmtException occured: "  + exception);
//		 return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
//	}
}
