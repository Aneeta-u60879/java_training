/*Class Name  : EmployeeService
 *Description : class for employee service
 *Date of Creation: 28/11/2020
 */
package com.training.ust.employee.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.training.ust.employee.dataacess.EmployeeDAOImpl;
import com.training.ust.employee.exception.EmployeeMgmtException;
import com.training.ust.employee.model.Employee;
import com.training.ust.employee.repo.EmployeeRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *This class for employee service
 */


@Service
public class EmployeeService{

	@Autowired
	private EmployeeRepo repo;

	@Autowired
	private EmployeeDAOImpl dao;
/*
 * Method for save employee
 */
	
	public Employee saveEmployee(Employee employee) throws EmployeeMgmtException {
		Mono<Employee> savedEmployee = null;
		try {

			savedEmployee = repo.save(employee);
		} catch (EmployeeMgmtException e) {
			System.out.println("employee not found");
		}

		return savedEmployee.block();

	}

	/*
	 * Method for get employeeList
	 */
	public List<Employee> getEmployeeList() {

		Flux<Employee> employeeList = repo.findAll();
		if (employeeList == null) {
			throw new EmployeeMgmtException("CONFLICT_EMPLOYEE_NOT_FOUND");
		} else {
			List<Employee> empList = employeeList.collectList().block();
			return empList;
		}
	}

	/*
	 * Method for delete employee
	 */
	public Mono<Employee> deleteEmployee(String employeeId) throws EmployeeMgmtException {
		Mono<Employee> response = repo.findById(employeeId);
		if (response.block() == null) {
			throw new EmployeeMgmtException("employee not found");
		} else {
			Mono<Employee> deleteResponse = response.flatMap(emp -> repo.delete(emp).then(Mono.just(emp)));

			return deleteResponse;
		}
	}

	/*
	 * Method for get employee by account
	 */
	public List<Employee> getEmpByAccount(String accountName) {

		Flux<Employee> employeeList = repo.findByAccountName(accountName);
		if (employeeList == null) {
			throw new EmployeeMgmtException("CONFLICT_EMPLOYEE_NOT_FOUND");
		} else {
			List<Employee> empList = employeeList.collectList().block();

			return empList;
		}
	}

	/*
	 * Method for update employee
	 */
	public Employee updateEmployee(Employee employee) {
		Mono<Employee> response = repo.findById(employee.getEmployeeId());
		if (response.block() == null) {
			throw new EmployeeMgmtException("CONFLICT_EMPLOYEE_NOT_FOUND");
		} else {
			Employee updateEmp = response.block();

			updateEmp.setExperince(employee.getExperince());
			Mono<Employee> savedEmployee = repo.save(updateEmp);
			return savedEmployee.block();

		}
	}

	/*
	 * Method for update bonus
	 */
	public Employee addBonus(Employee employee) {
		Mono<Employee> response = repo.findById(employee.getEmployeeId());

		if (response.block() == null) {
			throw new EmployeeMgmtException("CONFLICT_EMPLOYEE_NOT_FOUND");
		} else {

			Employee updateEmp = response.block();
			updateEmp.setBonus(employee.getBonus());
			Mono<Employee> savedEmployee = repo.save(updateEmp);

			return savedEmployee.block();
		}
	}

	/*
	 * Method for save batch employees
	 */
	public List<Employee> saveBatchEmployee(List<Employee> employeeList) {

		if (employeeList == null) {
			throw new EmployeeMgmtException("CONFLICT_EMPLOYEE_NOT_FOUND");
		} else {
			for (Employee employeeDetails2 : employeeList) {
				String band = bandCalc(employeeDetails2.getExperince());
				employeeDetails2.setBand(band);
				System.out.println(employeeDetails2);
			}
			Flux<Employee> savedEmployee = repo.saveAll(employeeList);
			List<Employee> emp = savedEmployee.collectList().block();
			return emp;
		}

	}

	/*
	 * Method for Band calculation
	 */
	public String bandCalc(double exp) {
		if (exp < 1) {
			return "A";
		} else if (exp >= 1 && exp <= 4) {
			return "B";
		} else
			return "C";
	}

	/*
	 * Method for get Employee By NameQuery
	 */
	public List<Employee> getEmpByNameQuery(String name, Integer offset, Integer limit) {
		List<Employee> empList = null;
		try {

			empList = dao.getEmployeesWithLimit(name, offset, limit);
		} catch (EmployeeMgmtException e) {
			System.out.println("employee not found");
		}
		return empList;
	}

	/*
	 * Method for delete Employee By Account And Band
	 */
	public Flux<Employee> deleteByAccountAndBand(String accountName, String band) {
		Flux<Employee> response = repo.findByAccountName(accountName);
		if (response == null) {
			throw new EmployeeMgmtException("employee not found");
		} else {
			Flux<Employee> ResponseBand = response.flatMap(emp -> repo.findByBand(band).then(Mono.just(emp)));
			Flux<Employee> deleteResponse = ResponseBand.flatMap(emp -> repo.delete(emp).then(Mono.just(emp)));
			return deleteResponse;
		}

	}

}
