/*Class Name  : Employee
 *Description : Model class  of employee 
 *Date of Creation: 28/11/2020
 */
package com.training.ust.employee.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;

import lombok.Data;
/**
 * Class for employee operations
 * @author 87094
 *
 */
@Container(containerName = "employee")
@Data
public class Employee {

	/*
	 * Method for toString
	 */
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", empName=" + empName + ", joiningDate=" + joiningDate
				+ ", salary=" + salary + ", experince=" + experince + ", band=" + band + ", bonus=" + bonus
				+ ", accountName=" + accountName + "]";
	}

	@Id
	private String employeeId;
	protected String empName;
	
	private String joiningDate;
	
	private double salary;
	
    private double experince;
	
	private String band;
	
	private int bonus;
	

	@PartitionKey
	private String accountName;

	/*
	 * Default constructor of employee
	 */
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * Parameterized  constructor of employee
	 */
	public Employee( String employeeId, String empName,String accountName, String joiningDate,double salary,double experince,String band,int bonus) {
		super();
		this.employeeId = employeeId;
		this.empName = empName;
	    this.accountName=accountName;
		this.joiningDate = joiningDate;
		this.salary = salary;
		this.experince=experince;
		this.band=band;
		this.bonus=bonus;
	}

	/*
	 * Assessor method for employeeId
	 */
	public String getEmployeeId() {
		return employeeId;
	}

	/*
	 * Assessor method for employeeId
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	/*
	 * Assessor method for empName
	 */
	public String getEmpName() {
		return empName;
	}

	/*
	 * Assessor method for empName
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	
	/*
	 * Assessor method for salary
	 */	

	public double getSalary() {
		return salary;
	}

	/*
	 * Assessor method for salary
	 */
	public void setSalary(double salary) {
		this.salary = salary;
	}


	/*
	 * Assessor method for experince
	 */
	public double getExperince() {
		return experince;
	}


	/*
	 * Assessor method for experince
	 */
	public void setExperince(double experince) {
		this.experince = experince;
	}

	/*
	 * Assessor method for band
	 */
	public String getBand() {
		return band;
	}

	/*
	 * Assessor method for band
	 */
	public void setBand(String band) {
		this.band = band;
	}


	/*
	 * Assessor method for bonus
	 */
	public int getBonus() {
		return bonus;
	}


	/*
	 * Assessor method for bonus
	 */
	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	/*
	 * Assessor method for accountName
	 */
	public String getAccountName() {
		return accountName;
	}

	/*
	 * Assessor method for accountName
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	
}

