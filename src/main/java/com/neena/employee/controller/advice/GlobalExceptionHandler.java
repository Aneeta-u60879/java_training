package com.neena.employee.controller.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.neena.employee.exception.EmployeeManagementException;
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
    private static final Logger Log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
    
//    @ExceptionHandler(EmployeeManagementException.class)
//    public ResponseEntity<Object> handleException(EmployeeManagementException exception) {
//          Log.error("EmployeeMgmtException occured: "  + exception);
//          return  ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
//    }

    @ExceptionHandler(EmployeeManagementException.class)
	public ResponseEntity<?> handleException(EmployeeManagementException exception) {
		ResponseEntity<?> response = null;
		if(exception.getMessage().equals("Employee Already Exist")|| 
				exception.getMessage().equals("No such Employee Found")){
		Log.error("EmployeeMgmtException occured: "  + exception);
		response = ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
		}
		else {
			Log.error("EmployeeMgmtException occured: "  + exception);
			response =  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return response;
	}
}
