package com.example.employeemgmt.dto;

/**
 * DTO class for update an employees' experience and band.
 * 
 * @author 144900
 *
 */
public class EmployeeUpdateDTO {
	private String employeeId;
	private Double experience;
	private String band;

	public EmployeeUpdateDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeUpdateDTO(String employeeId, Double experience, String band) {
		super();
		this.employeeId = employeeId;
		this.experience = experience;
		this.band = band;
	}

	/**
	 * @return the employeeId
	 */
	public String getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId
	 *            the employeeId to set
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the experience
	 */
	public Double getExperience() {
		return experience;
	}

	/**
	 * @param experience
	 *            the experience to set
	 */
	public void setExperience(Double experience) {
		this.experience = experience;
	}

	/**
	 * @return the band
	 */
	public String getBand() {
		return band;
	}

	/**
	 * @param band
	 *            the band to set
	 */
	public void setBand(String band) {
		this.band = band;
	}

	@Override
	public String toString() {
		return "EmployeeUpdateBO [employeeId=" + employeeId + ", experience="
				+ experience + ", band=" + band + "]";
	}

}
