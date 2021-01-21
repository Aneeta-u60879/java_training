package com.training.ust.hospitalmng.exception;
/**
 * Custom Exception Class for HospitalMgmtException .
 * 
 * @author 87094
 *
 */
public class HospitalMgmtException extends RuntimeException {
	
	/*
	 * Parametarized constructor for  HospitalMgmtException
	 */
	public HospitalMgmtException(String message) {
		super(message);
	}

	/*
	 * Default constructor for  HospitalMgmtException
	 */
	public HospitalMgmtException() {
		super();
	}

	
	private static final long serialVersionUID = -3221190255798269840L;

}
