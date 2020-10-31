package com.eleena.Employee.mapper;

import org.springframework.stereotype.Component;

import com.eleena.Employee.model.EmployeeMngmnt;

@Component
public class EmployeeMapper {

	public EmployeeMngmnt mapEmployeeDetail(EmployeeMngmnt employee) {
		EmployeeMngmnt emp = employee;
		return emp;
	}

}
