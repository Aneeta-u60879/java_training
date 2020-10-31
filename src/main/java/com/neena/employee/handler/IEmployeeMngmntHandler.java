package com.neena.employee.handler;

import java.util.List;
import java.util.Map;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.neena.employee.bo.DeleteByAccount;
import com.neena.employee.bo.EmployeeMangmntBO;
import com.neena.employee.bo.UpdateBonusBo;
import com.neena.employee.bo.UpdateExperience;
import com.neena.employee.exception.EmployeeManagementException;
import com.neena.employee.model.Employee;
import com.neena.employee.model.Model;

public interface IEmployeeMngmntHandler {

	
	/**
	 * To add employee to the table of employee
	 * @param employ
	 * @return Added employee
	 */
	public Employee saveEmploy(Employee employ);

	/**
	 * To get all employees from the table
	 * @return List of employee
	 */
	public Flux<Employee> getEmployeeList();

	/**
	 * To find an employee by it Id
	 * @param id
	 * @return The employee of the given Id
	 */
	public Mono<Employee> findById(String id);

	/**
	 * To get list of employees with a given account 
	 * @param accountName
	 * @return The list of employees under a given account
	 */
	public Flux<Employee> findByAccountName(String accountName);

	/**
	 * Update the experience and Employeeband of apa
	 * @param updateEmp
	 * @return
	 * @throws EmployeeManagementException
	 */
	public EmployeeMangmntBO updateEmployee(UpdateExperience updateEmp)throws EmployeeManagementException ;
	 
	/**
	 * To update the salary of the given employee by addinf bonus
	 * @param updateBonus
	 * @return The employee with updated salary
	 */
	public EmployeeMangmntBO updateBonus(UpdateBonusBo updateBonus) ;

	/**
	 * To get a list of employees with cutom query
	 * @param name
	 * @param offset
	 * @param limit
	 * @return List of employees
	 */
	
	public List<Employee> getEmpByNameQuery(String name, Integer offset, Integer limit);

	/**
	 * To get the specific detials of list of employees
	 * @param name
	 * @param offset
	 * @param limit
	 * @return List of employee with specific details
	 */
	public List<Model> getEmpIdByNameQuery(String name, Integer offset,
			Integer limit);

	/**
	 * To add a list of freshers to the table of employee
	 * @param newEmpList
	 * @return the list of freshers added
	 */
	public List<EmployeeMangmntBO> addFreshers(List<EmployeeMangmntBO> newEmpList);

	/**
	 * Delete the employee of a given id
	 * @param id
	 * @return The deleted employee
	 */
	public Mono<Employee> deleteEmploy(String id);
    
	/**
	 * To find an employee by it Id
	 * @param id
	 * @return The employee of the given Id
	 */
    public EmployeeMangmntBO searchById(String id);
    
    /**
	 * To add a list of employees
	 * @param newEmp
	 * @return Added List of employees
	 */
    public EmployeeMangmntBO addEmployee(EmployeeMangmntBO newEmp)throws EmployeeManagementException ;

	
	 
	 
	 
	 
}
