package com.example.employeemgmt.model;

import org.springframework.data.annotation.Id;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;

/**
 * Model class for saving employee details.
 * 
 * @author 144900
 *
 */
@Container(containerName = "employeeMgmt")
public class Employee {
	@Id
	private String employeeId;
	private String name;
	@PartitionKey
	private String accountName;
	private Double salary;
	private Double experience;
	private String band;

	public Employee() {
		super();
	}

	public Employee(String employeeId, String name, String accountName,
			Double salary, Double experience, String band) {
		super();

		this.employeeId = employeeId;
		this.name = name;
		this.accountName = accountName;
		this.salary = salary;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the accountName
	 */
	public String getAccountName() {
		return accountName;
	}

	/**
	 * @param accountName
	 *            the accountName to set
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	/**
	 * @return the salary
	 */
	public Double getSalary() {
		return salary;
	}

	/**
	 * @param salary
	 *            the salary to set
	 */
	public void setSalary(Double salary) {
		this.salary = salary;
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
		return "Employee [employeeId=" + employeeId + ", name=" + name
				+ ", joiningDate=" + ", accountName=" + accountName
				+ ", salary=" + salary + ", experience=" + experience
				+ ", band=" + band + "]";
	}

}
