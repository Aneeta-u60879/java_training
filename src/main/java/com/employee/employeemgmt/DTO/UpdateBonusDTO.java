package com.employee.employeemgmt.DTO;

public class UpdateBonusDTO {

	
	private String employeeId;
	private Double bonus;
	/**
	 * @return the employeeId
	 */
	public String getEmployeeId() {
		return employeeId;
	}
	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	/**
	 * @return the bonus
	 */
	public Double getBonus() {
		return bonus;
	}
	/**
	 * @param bonus the bonus to set
	 */
	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}
	

}
