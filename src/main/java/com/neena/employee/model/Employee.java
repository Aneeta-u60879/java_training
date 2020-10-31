package com.neena.employee.model;

import org.springframework.data.annotation.Id;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;


@Container(containerName= "employeemangmnt")
public class Employee {
	
	@Id
	private String id;
	private Integer employeeId;
	private String employeeName;
	private String joiningDate;
	@PartitionKey
	private String accountName;
	private Integer salary;
	private Integer experience;
	private String emplyBand;
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee(String id, Integer employeeId, String employeeName,
			String joiningDate, String accountName, Integer salary,
			Integer experience, String emplyBand) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.joiningDate = joiningDate; 
		this.accountName = accountName;
		this.salary = salary;
		this.experience = experience;
		this.emplyBand = emplyBand;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
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
	public Integer getSalary() {
		return salary;
	}
	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	public Integer getExperience() {
		return experience;
	}
	public void setExperience(Integer experience) {
		this.experience = experience;
	}
	public String getEmplyBand() {
		return emplyBand;
	}
	public void setEmplyBand(String emplyBand) {
		this.emplyBand = emplyBand;
	}
	

}
