package com.example.employeemanagement.model;

import org.springframework.data.annotation.Id;

import lombok.Data;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;

/**
 * Model Class for Employee
 * 
 * @author 144892
 *
 */
@Container(containerName = "details_employee")
@Data
public class Employee {

	/*
	 * primary key empId
	 */
	@Id
	private Integer empId;
	private String empName;
	private String joiningDate;
	/*
	 * partition key accName
	 */
	@PartitionKey
	private String accName;
	private Double salary;
	private Double experience;
	private String band;
	
	/**
	 *Constructor from Superclass
	 */
	public Employee() {
		super();
	}
	
	/**
	 *Constructor with fields 
	 */
	public Employee(Integer empId, String empName, String joiningDate,
			String accName, Double salary, Double experience, String band) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.joiningDate = joiningDate;
		this.accName = accName;
		this.salary = salary;
		this.experience = experience;
		this.band = band;
	}
	
	/**
	 *Getters and Setters
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
	public String getAccName() {
		return accName;
	}
	public void setAccName(String accName) {
		this.accName = accName;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public Double getExperience() {
		return experience;
	}
	public void setExperience(Double experience) {
		this.experience = experience;
	}
	public String getBand() {
		return band;
	}
	public void setBand(String band) {
		this.band = band;
	}
	
	/**
	 *toString Method
	 */
	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName
				+ ", joiningDate=" + joiningDate + ", accName=" + accName
				+ ", salary=" + salary + ", experience=" + experience
				+ ", band=" + band + "]";
	}
}
