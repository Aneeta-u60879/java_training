package com.example.employeemanagement.handler;

import java.util.List;

import com.example.employeemanagement.bo.EmployeeBO;
import com.example.employeemanagement.bo.UpdateEmployeeBO;
import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.model.EmployeeNames;

/**
 * EmployeeHandler Interface for EmployeeManagementClass
 * 
 * @author 144892
 *
 */
public interface IEmployeeManagementHandler {
	
	/*
	 * Method to fetch all employees from the list
	 * 
	 * @return List<EmployeeBO>
	 */
	public List<EmployeeBO> getEmployeeList();

	/*
	 * Method to save a new employee into the list
	 * 
	 * @body EmployeeBO employeeBO
	 * 
	 * @return EmployeeBO
	 */

	public EmployeeBO saveEmployee(EmployeeBO employeeBO);

	/*
	 * Method to delete an employee using specific employee id from the list
	 * 
	 * @body EmployeeBO employeeBO
	 * 
	 * @return EmployeeBO
	 */
	
	public EmployeeBO deleteEmployee(String empId);
	
	/*
	 * Method to update an employee using specific employee id in the list
	 * 
	 * @body EmployeeBO employeeBO
	 * 
	 * @return EmployeeBO
	 */

	public EmployeeBO updateEmployee(EmployeeBO employeeBO);

	/*
	 * Method to fetch all employees under a specific band from the list
	 * 
	 * @param String band
	 * 
	 * @return List<EmployeeBO>
	 */

	public List<EmployeeBO> getEmployeesFromBand(String band);

	/*
	 * Method to fetch all employees under a specific account from the list
	 * 
	 * @param String accName
	 * 
	 * @return List<EmployeeBO>
	 */

	public List<EmployeeBO> getByAcc(String accName);

	/*
	 * Method to delete employees under a specific band under a specific account
	 * from the list
	 * 
	 * @param String band
	 * 
	 * @return List<EmployeeBO>
	 */

	public List<EmployeeBO> deleteByAcc(String accName, String band);

	/*
	 * Method to add bonus to a specific employee in the list
	 * 
	 * @body UpdateEmployeeBO employeeBO
	 * 
	 * @return EmployeeBO
	 */
	
	public EmployeeBO addBonus(UpdateEmployeeBO employeeBO);

	/*
	 * Method to add a specific % of allowance to all the employee's salary in
	 * the list
	 * 
	 * @param Double allowance
	 * 
	 * @return List<EmployeeBO>
	 */
	
	public List<EmployeeBO> addAllowance(Double allowance);

	/*
	 * Method to add list of freshers to the list
	 * 
	 * @body List<EmployeeBO> newEmployeeListBO
	 * 
	 * @return List<EmployeeBO>
	 */

	public List<EmployeeBO> addNewEmployees(List<EmployeeBO> newEmployeeListBO);

	/*
	 * Custom Query Method to fetch employees from an account according to given
	 * range from the list
	 * 
	 * @param String account
	 * 
	 * @param Integer offset
	 * 
	 * @param Integer limit
	 * 
	 * @return List<Employee>
	 */
	public List<Employee> getEmpByNameQuery(String name, Integer offset,
			Integer limit);

	/*
	 * Custom Query Method to fetch employee id, name and joiningDate from a
	 * specific band from the list
	 * 
	 * @param String band
	 * 
	 * @return List<EmployeeNames>
	 */
	public List<EmployeeNames> getEmpByNameByBand(String band);
	
}
