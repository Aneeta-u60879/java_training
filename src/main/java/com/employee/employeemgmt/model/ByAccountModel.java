package com.employee.employeemgmt.model;

/**
 * @author 144895
 *
 */
public class ByAccountModel {
	
	private String employeeId;
	private String name;
	public String getEmployeeId() {
		return employeeId;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

}
