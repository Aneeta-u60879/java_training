package com.training.ust.employee.exception;

/**
 * Custom Exception Class for EmployeeMgmt .
 * 
 * @author 87094
 *
 */
public class EmployeeMgmtException extends RuntimeException {
	
	/*
	 * Parametarized constructor for  EmployeeMgmtException
	 */
	public EmployeeMgmtException(String message) {
		super(message);
	}

	/*
	 * Default constructor for  EmployeeMgmtException
	 */
	public EmployeeMgmtException() {
		super();
	}

	
	private static final long serialVersionUID = -3221190255798269840L;

}
