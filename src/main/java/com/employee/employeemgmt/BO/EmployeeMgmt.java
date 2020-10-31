package com.employee.employeemgmt.BO;

import java.time.LocalDate;

import lombok.Data;

/**
 * @author 144895
 * Main model class 
 */
@Data
public class EmployeeMgmt {

	private String employeeId;
	private String name;
	private LocalDate joiningDate;
	private String accountName;
	private Double salary;
	private Float experience;
	private String band;
	
	/**
	 * Default constructor
	 */
	public EmployeeMgmt() {
		
	}


	/**
	 * Parameterized constructor
	 * @param employeeId
	 * @param name
	 * @param joiningDate
	 * @param accountName
	 * @param salary
	 * @param experience
	 * @param band
	 */
	public EmployeeMgmt(String employeeId, String name, LocalDate joiningDate,
			String accountName, Double salary, Float experience, String band) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.joiningDate = joiningDate;
		this.accountName = accountName;
		this.salary = salary;
		this.experience = experience;
		this.band = band;
	}


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
	 * @return name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return joiningDate
	 */
	public LocalDate getJoiningDate() {
		return joiningDate;
	}


	/**
	 * @param joiningDate
	 */
	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}


	/**
	 * @return accountName
	 */
	public String getAccountName() {
		return accountName;
	}


	/**
	 * @param accountName
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}


	/**
	 * @return salary
	 */
	public Double getSalary() {
		return salary;
	}


	/**
	 * @param salary
	 */
	public void setSalary(Double salary) {
		this.salary = salary;
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


	@Override
	public String toString() {
		return "EmployeeMgmt [employeeId=" + employeeId + ", name=" + name
				+ ", joiningDate=" + joiningDate + ", accountName="
				+ accountName + ", salary=" + salary + ", experience="
				+ experience + ", band=" + band + "]";
	}

}
