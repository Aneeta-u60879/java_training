package com.example.employeemgmt.controller.advice;

import static com.example.employeemgmt.common.EmployeeMgmtConstants.CONFLICT_EMPLOYEE_EXIST;
import static com.example.employeemgmt.common.EmployeeMgmtConstants.CONFLICT_EMPLOYEE_NOT_FOUND;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.employeemgmt.exception.EmployeeMgmtException;

/**
 * Global Exception Handler for EmployeeMgmt Controller.
 * 
 * @author 144900
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger Log = LoggerFactory
			.getLogger(GlobalExceptionHandler.class);

	/**
	 * Method for handling exception and return response as conflict and no 
	 * content.
	 * 
	 * @param exception
	 * @return ResponseEntity 
	 */
	@ExceptionHandler(EmployeeMgmtException.class)
	public ResponseEntity<?> handleException(EmployeeMgmtException exception) {
		ResponseEntity<?> response = null;
		if (exception.getMessage().equals(CONFLICT_EMPLOYEE_EXIST)
				|| exception.getMessage().equals(CONFLICT_EMPLOYEE_NOT_FOUND)) {
			Log.error("EmployeeMgmtException occured: " + exception);
			response = ResponseEntity.status(HttpStatus.CONFLICT).body(
					exception.getMessage());
		} else {
			Log.error("EmployeeMgmtException occured: " + exception);
			response = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return response;
	}

}
