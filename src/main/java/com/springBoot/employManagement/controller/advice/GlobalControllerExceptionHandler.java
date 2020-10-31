package com.springBoot.employManagement.controller.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.springBoot.employManagement.exception.EmployManagementException;

@RestControllerAdvice
public class GlobalControllerExceptionHandler extends
		ResponseEntityExceptionHandler {
	private static final Logger Log = LoggerFactory
			.getLogger(GlobalControllerExceptionHandler.class);

	@ExceptionHandler(EmployManagementException.class)
	public ResponseEntity<Object> handleException(
			EmployManagementException exception) {
		Log.error("EmployeeMgmtException occured: " + exception);
		return ResponseEntity.status(HttpStatus.CONFLICT).body(
				exception.getMessage());
	}

}
