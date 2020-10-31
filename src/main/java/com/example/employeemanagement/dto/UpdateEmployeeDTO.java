package com.example.employeemanagement.dto;

/**
 * DTO Class for UpdateEmployee
 * 
 * @author 144892
 *
 */
public class UpdateEmployeeDTO {

	private Integer empId;
	private Double bonus;

	/**
	 * Constructor from Superclass
	 */
	public UpdateEmployeeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor with fields
	 */
	public UpdateEmployeeDTO(Integer empId, Double bonus) {
		super();
		this.empId = empId;
		this.bonus = bonus;
	}

	/**
	 * Getters and Setters
	 */
	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public Double getBonus() {
		return bonus;
	}

	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}

}
