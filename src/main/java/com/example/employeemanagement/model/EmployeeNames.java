package com.example.employeemanagement.model;

/**
 * Model Class for EmployeeNames
 * 
 * @author 144892
 *
 */
public class EmployeeNames {

	private Integer empId;
	private String empName;
	private String joiningDate;
	
	/*
	 * Constructor from Superclass
	 */
	public EmployeeNames() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * Constructor with fields
	 */
	public EmployeeNames(Integer empId, String empName, String joiningDate) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.joiningDate = joiningDate;
	}

	/*
	 * Getters and Setters
	 */
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}

	/*
	 * toString Method
	 */
	@Override
	public String toString() {
		return "EmployeeNames [empId=" + empId + ", empName=" + empName
				+ ", joiningDate=" + joiningDate + "]";
	}
	
}
