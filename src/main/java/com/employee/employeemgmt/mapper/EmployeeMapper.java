package com.employee.employeemgmt.mapper;

import org.springframework.stereotype.Component;

import com.employee.employeemgmt.model.EmployModel;


/**
 * @author 144895
 *
 */
@Component
public class EmployeeMapper {
	
	public EmployModel mapEmployeeDetail(EmployModel employee) {
		EmployModel emp = employee;
		return emp;
	}

}
