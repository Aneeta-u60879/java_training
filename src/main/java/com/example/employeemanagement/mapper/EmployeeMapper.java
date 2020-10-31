package com.example.employeemanagement.mapper;

import org.springframework.stereotype.Component;

import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.model.EmployeeNames;


/**
 * EmployeeMapper for EmployeeManagementClass
 * 
 * @author 144892
 *
 */
@Component
public class EmployeeMapper {
	/*
	 * Method to map Employee Model
	 */
	public Employee mapEmployeeDetail(Employee employee) {
		Employee emp = employee;
		return emp;
	}
	
	/*
	 * Method to map EmployeeNames Model
	 */
	public EmployeeNames mapEmployeeNames(EmployeeNames employee) {
		EmployeeNames emp = employee;
		return emp;
	}
}
