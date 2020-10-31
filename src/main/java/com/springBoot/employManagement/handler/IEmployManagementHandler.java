package com.springBoot.employManagement.handler;

import java.util.List;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.springBoot.employManagement.bo.EmployBO;
import com.springBoot.employManagement.bo.UpdateAllowance;
import com.springBoot.employManagement.bo.UpdateBonusBO;
import com.springBoot.employManagement.bo.UpdateExperienceBO;
import com.springBoot.employManagement.exception.EmployManagementException;
import com.springBoot.employManagement.model.Employee;

/**
 * Handler for CRUD operation
 * 
 * @author 144785
 *
 */
public interface IEmployManagementHandler {

	/**
	 * save employee list
	 * 
	 * @param employeeBO
	 * @return EmployBO
	 */
	public EmployBO saveEmployee(EmployBO employeeBO);

	/**
	 * fetch employee list
	 * 
	 * @return Flux<Employee>
	 */
	public Flux<Employee> getEmployeeList();

	/**
	 * fetch employee by account name
	 * 
	 * @param accountName
	 * @return Flux<Employee>
	 */
	public Flux<Employee> getEmployeeByAccountName(String accountName);

	/**
	 * fetch employee by band
	 * 
	 * @param band
	 * @return Flux<Employee>
	 */
	public Flux<Employee> getEmployeeByBand(String band);

	/**
	 * fetch employee by employId
	 * 
	 * @param employId
	 * @return Flux<Employee>
	 */
	public Flux<Employee> getEmployeeByEmployId(Integer employId);

	/**
	 * Delete employee
	 * 
	 * @param id
	 * @return Mono<Employee>
	 */
	public Mono<Employee> deleteEmployee(String id);

	/**
	 * update experience of an employee
	 * 
	 * @param updateExperience
	 * @return EmployBO
	 * @throws EmployManagementException
	 */
	public EmployBO updateExperience(UpdateExperienceBO updateExperience)
			throws EmployManagementException;

	/**
	 * update bonus f an employee
	 * 
	 * @param update
	 * @return
	 * @throws EmployManagementException
	 */
	public EmployBO updateBonus(UpdateBonusBO update)
			throws EmployManagementException;

	/**
	 * update allowance of an employee
	 * 
	 * @param allowance
	 * @return EmployBO
	 * @throws EmployManagementException
	 */
	public EmployBO updateAllowance(UpdateAllowance allowance)
			throws EmployManagementException;

	/**
	 * fetch employList based on querry
	 * 
	 * @param name
	 * @param offset
	 * @param limit
	 * @return List<Employee>
	 */
	public List<Employee> getEmpByNameQuery(String name, Integer offset,
			Integer limit);

	/**
	 * fetch id and employId based on querry
	 * 
	 * @param accountName
	 * @param offset
	 * @param limit
	 * @return Model
	 */
	public List<?> getEmpByIdQuery(String accountName, Integer offset,
			Integer limit);

	/**
	 * add list of freshers
	 * 
	 * @param newEmpList
	 * @return List<EmployBO>
	 */
	public List<EmployBO> addFreshers(List<EmployBO> newEmpList);

}
