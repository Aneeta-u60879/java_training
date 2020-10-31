package com.employee.employeemgmt.BO;


/**
 * Update bonus of a particular employ
 * @author 144895
 *
 */
public class UpdateBonus {
	
	private String employeeId;
	private Double bonus;
	/**
	 * @return employeeId
	 */
	public String getEmployeeId() {
		return employeeId;
	}
	/**
	 * @param employeeId
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	/**
	 * @return bonus
	 */
	public Double getBonus() {
		return bonus;
	}
	/**
	 * @param bonus
	 */
	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}

}
