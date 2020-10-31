package com.eleena.Employee.model;

import lombok.Data;

import org.springframework.data.annotation.Id;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;

@Container(containerName = "employeeMgmt")
@Data
public class EmployeeMngmnt {
	@Id
	private String id;
	private String employeeId;
	private String name;
	private String joiningDate;
	@PartitionKey
	private String accountName;
	private Double salary;
	private Float experience;
	private String band;
	public EmployeeMngmnt(String id, String employeeId, String name,
			String joiningDate, String accountName, Double salary,
			Float experience, String band) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.name = name;
		this.joiningDate = joiningDate;
		this.accountName = accountName;
		this.salary = salary;
		this.experience = experience;
		this.band = band;
	}
	public EmployeeMngmnt() {
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
	public Float getExperience() {
		return experience;
	}
	public void setExperience(Float experience) {
		this.experience = experience;
	}
	public String getBand() {
		return band;
	}
	public void setBand(String band) {
		this.band = band;
	}

	
}
