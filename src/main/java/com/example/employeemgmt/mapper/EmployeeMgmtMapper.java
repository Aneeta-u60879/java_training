package com.example.employeemgmt.mapper;

import org.springframework.stereotype.Component;

import com.example.employeemgmt.model.Employee;

/**
 * Mapper class for EmployeeMgmt.
 * 
 * @author 144900
 *
 */
@Component
public class EmployeeMgmtMapper {

	/**
	 * @param employee
	 * @return Employee
	 */
	public Employee mapEmployeeDetail(Employee employee) {
		Employee emp = employee;
		return emp;
	}
}
