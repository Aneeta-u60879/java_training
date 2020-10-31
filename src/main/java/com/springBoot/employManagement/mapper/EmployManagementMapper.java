package com.springBoot.employManagement.mapper;

import org.springframework.stereotype.Component;

import com.springBoot.employManagement.model.Employee;

@Component
public class EmployManagementMapper {
	public Employee mapEmployeeDetail(Employee employee) {
		Employee emp = employee;
		return emp;
	}

}
