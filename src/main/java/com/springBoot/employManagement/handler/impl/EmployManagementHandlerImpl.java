package com.springBoot.employManagement.handler.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.springBoot.employManagement.bo.EmployBO;
import com.springBoot.employManagement.bo.UpdateAllowance;
import com.springBoot.employManagement.bo.UpdateBonusBO;
import com.springBoot.employManagement.bo.UpdateExperienceBO;
import com.springBoot.employManagement.dao.EmployManagementDAO;
import com.springBoot.employManagement.exception.EmployManagementException;
import com.springBoot.employManagement.handler.IEmployManagementHandler;
import com.springBoot.employManagement.model.Employee;
import com.springBoot.employManagement.model.Model;
import com.springBoot.employManagement.repo.IEmployManagementRepo;

/**
 * Handler for CRUD operation
 * 
 * @author 144785
 *
 */
@Service
public class EmployManagementHandlerImpl implements IEmployManagementHandler {

	@Autowired
	IEmployManagementRepo repo;

	@Autowired
	private EmployManagementDAO dao;

	/**
	 * fetch employee list
	 * 
	 * @return Flux<Employee>
	 */
	@Override
	public Flux<Employee> getEmployeeList() {
		Flux<Employee> employeeLis = repo.findAll();
		return employeeLis;
	}
 
	/**
	 * save employee list
	 * 
	 * @param employeeBO
	 * @return EmployBO
	 */
	@Override
	public EmployBO saveEmployee(EmployBO employeeBO)
			throws EmployManagementException {
		Employee employee = new Employee();
		BeanUtils.copyProperties(employeeBO, employee);
		Mono<Employee> savedEmployee = repo.save(employee);
		EmployBO empBO = new EmployBO();
		BeanUtils.copyProperties(savedEmployee.block(), empBO);
		return empBO;
	}

	/**
	 * fetch employee by account name
	 * 
	 * @param accountName
	 * @return Flux<Employee>
	 */
	@Override
	public Flux<Employee> getEmployeeByAccountName(String accountName) {
		Flux<Employee> employeeList = repo.getEmpByAccountName(accountName);
		return employeeList;
	}

	/**
	 * fetch employee by band
	 * 
	 * @param band
	 * @return Flux<Employee>
	 */
	@Override
	public Flux<Employee> getEmployeeByBand(String band) {
		Flux<Employee> employeeList = repo.getEmpByBand(band);
		return employeeList;
	}

	/**
	 * fetch employee by employId
	 * 
	 * @param employId
	 * @return Flux<Employee>
	 */
	@Override
	public Flux<Employee> getEmployeeByEmployId(Integer employId) {
		Flux<Employee> employeeList = repo.getEmpByEmployId(employId);
		return employeeList;
	}

	/**
	 * Delete employee
	 * 
	 * @param id
	 * @return Mono<Employee>
	 */
	@Override
	public Mono<Employee> deleteEmployee(String id) {
		Mono<Employee> emp = repo.findById(id);
		Mono<Employee> del = emp.flatMap(each -> repo.delete(each).then(
				Mono.just(each)));
		return del;
	}

	/**
	 * update experience of an employee
	 * 
	 * @param updateExperience
	 * @return EmployBO
	 * @throws EmployManagementException
	 */
	@Override
	public EmployBO updateExperience(UpdateExperienceBO updateExperience)
			throws EmployManagementException {
		Mono<Employee> getById = repo.findById(updateExperience.getId());
		if (getById == null) {
			throw new EmployManagementException("No such Employee Found");

		} else {

			Employee employee = getById.block();
			employee.setExperience(updateExperience.getExperience());
			employee.setBand(updateExperience.getBand());

			Mono<Employee> saveEmp = repo.save(employee);
			EmployBO empBO = new EmployBO();
			BeanUtils.copyProperties(saveEmp.block(), empBO);
			return empBO;
		}
	}

	/**
	 * update bonus f an employee
	 * 
	 * @param update
	 * @return
	 * @throws EmployManagementException
	 */
	@Override
	public EmployBO updateBonus(UpdateBonusBO update)
			throws EmployManagementException {
		Mono<Employee> getById = repo.findById(update.getId());
		if (getById == null) {
			throw new EmployManagementException("No such Employee Found");

		} else {
			Employee employee = getById.block();
			employee.setSalary(employee.getSalary() + update.getBonus());

			Mono<Employee> savedEmployee = repo.save(employee);
			EmployBO empBO = new EmployBO();
			BeanUtils.copyProperties(savedEmployee.block(), empBO);
			return empBO;
		}
	}

	/**
	 * update allowance of an employee
	 * 
	 * @param allowance
	 * @return EmployBO
	 * @throws EmployManagementException
	 */
	@Override
	public EmployBO updateAllowance(UpdateAllowance allowance)
			throws EmployManagementException {
		Mono<Employee> getById = repo.findById(allowance.getId());
		if (getById == null) {
			throw new EmployManagementException("No such Employee Found");

		} else {
			Employee employee = getById.block();
			employee.setSalary(employee.getSalary()
					+ (employee.getSalary() * allowance.getAllowance() / 100));

			Mono<Employee> savedEmployee = repo.save(employee);
			EmployBO empBO = new EmployBO();
			BeanUtils.copyProperties(savedEmployee.block(), empBO);
			return empBO;
		}
	}

	/**
	 * add list of freshers
	 * 
	 * @param newEmpList
	 * @return List<EmployBO>
	 */
	@Override
	public List<EmployBO> addFreshers(List<EmployBO> newEmpList) {
		List<Employee> emp = new ArrayList<Employee>();
		newEmpList.forEach(eachEmp -> {
			Employee employee = new Employee();
			BeanUtils.copyProperties(eachEmp, employee);
			emp.add(employee);
		});
		Flux<Employee> saveEmp = repo.saveAll(emp);
		List<Employee> employeeList = saveEmp.collectList().block();
		List<EmployBO> employeeBOList = new ArrayList<EmployBO>();
		employeeList.forEach(eachEmp -> {
			EmployBO employeeBO = new EmployBO();
			BeanUtils.copyProperties(eachEmp, employeeBO);
			employeeBOList.add(employeeBO);
		});
		return employeeBOList;
	}

	/**
	 * fetch employList based on querry
	 * 
	 * @param name
	 * @param offset
	 * @param limit
	 * @return List<Employee>
	 */
	@Override
	public List<Employee> getEmpByNameQuery(String accountName, Integer offset,
			Integer limit) {
		List<Employee> empList = dao.getEmployeesWithLimit(accountName, offset,
				limit);
		return empList;
	}

	/**
	 * fetch id and employId based on querry
	 * 
	 * @param accountName
	 * @param offset
	 * @param limit
	 * @return Model
	 */
	@Override
	public List<Model> getEmpByIdQuery(String accountName, Integer offset,
			Integer limit) {
		List<Model> empList = dao
				.getEmployWithLimit(accountName, offset, limit);
		return empList;
	}

}
