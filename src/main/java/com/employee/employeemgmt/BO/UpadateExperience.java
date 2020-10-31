package com.employee.employeemgmt.BO;


/**
 * Class for update experience of a particular employ
 * @author 144895
 *
 */
public class UpadateExperience {
	private String employeeId;
	private Float experience;
	private String band;
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
	 * @return experience
	 */
	public Float getExperience() {
		return experience;
	}
	/**
	 * @param experience
	 */
	public void setExperience(Float experience) {
		this.experience = experience;
	}
	/**
	 * @return band
	 */
	public String getBand() {
		return band;
	}
	/**
	 * @param band
	 */
	public void setBand(String band) {
		this.band = band;
	}
}
