package com.neena.employee.mapper;

import org.springframework.stereotype.Component;

import com.neena.employee.model.Employee;


@Component
public class EmployeeMapper {
	
	public Employee mapEmployeeDetail(Employee employee) {
		Employee emp = employee;
		return emp;
	}
	
}