/*Class Name  : EmployeeMapper
 *Description : EmployeeMapper class  of employee 
 *Date of Creation: 28/11/2020
 */
package com.training.ust.employee.mapper;

import org.springframework.stereotype.Component;

import com.training.ust.employee.model.Employee;

/**
 * class for EmployeeMapper operations
 * @author 87094
 *
 */
@Component
public class EmployeeMapper {
	
	/*
	 * Method for map EmployeeDetail
	 */

	public Employee mapEmployeeDetail(Employee employee) {
		Employee emp = employee;
		return emp;
	}

}
