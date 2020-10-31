package com.example.employeemanagement.handler.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.example.employeemanagement.bo.EmployeeBO;
import com.example.employeemanagement.bo.UpdateEmployeeBO;
import com.example.employeemanagement.dao.EmployeeDAO;
import com.example.employeemanagement.exception.EmployeeManagementException;
import com.example.employeemanagement.handler.IEmployeeManagementHandler;
import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.model.EmployeeNames;
import com.example.employeemanagement.repo.IEmployeeManagementRepo;

/**
 * EmployeeHandler(Service) for EmployeeManagementClass
 * 
 * @author 144892
 *
 */
@Service
public class EmployeeManagementHandlerImpl implements
		IEmployeeManagementHandler {

	/*
	 * EmployeeRepo Dependency Injected using @Autowired
	 */
	@Autowired
	IEmployeeManagementRepo empRepo;

	/*
	 * EmployeeDao Dependency Injected using @Autowired
	 */
	@Autowired
	EmployeeDAO empDao;

	/*
	 * Method to fetch all employees from the list
	 * 
	 * @return List<EmployeeBO>
	 */
	@Override
	public List<EmployeeBO> getEmployeeList()
			throws EmployeeManagementException {
		Flux<Employee> employeeFlux = empRepo.findAll();
		List<Employee> employeeList = employeeFlux.collectList().block();
		if (CollectionUtils.isEmpty(employeeList)) {
			throw new EmployeeManagementException("No Employee List found!!");
		} else {
			List<EmployeeBO> employeeListBO = new ArrayList<EmployeeBO>();
			employeeList.forEach(employee -> {
				EmployeeBO employeeBO = new EmployeeBO();
				BeanUtils.copyProperties(employee, employeeBO);
				employeeListBO.add(employeeBO);
			});
			return employeeListBO;
		}
	}

	/*
	 * Method to save a new employee into the list
	 * 
	 * @body EmployeeBO employeeBO
	 * 
	 * @return EmployeeBO
	 */
	@Override
	public EmployeeBO saveEmployee(EmployeeBO employeeBO) {
		Employee employee = new Employee();
		BeanUtils.copyProperties(employeeBO, employee);
		Mono<Employee> savedEmployee = empRepo.save(employee);
		EmployeeBO savedEmployeeBO = new EmployeeBO();
		BeanUtils.copyProperties(savedEmployee.block(), savedEmployeeBO);
		return savedEmployeeBO;
	}

	/*
	 * Method to delete an employee using specific employee id from the list
	 * 
	 * @body EmployeeBO employeeBO
	 * 
	 * @return EmployeeBO
	 */
	@Override
	public EmployeeBO deleteEmployee(String empId)
			throws EmployeeManagementException {
		Mono<Employee> emp = empRepo.findById(empId);
		if (emp.block() == null) {
			throw new EmployeeManagementException("No such Employee found!");
		} else {
			Mono<Employee> deletedEmployee = emp.flatMap(each -> empRepo
					.delete(each).then(Mono.just(each)));
			EmployeeBO deletedEmployeeBO = new EmployeeBO();
			BeanUtils
					.copyProperties(deletedEmployee.block(), deletedEmployeeBO);
			return deletedEmployeeBO;
		}

	}

	/*
	 * Method to update an employee using specific employee id in the list
	 * 
	 * @body EmployeeBO employeeBO
	 * 
	 * @return EmployeeBO
	 */
	@Override
	public EmployeeBO updateEmployee(EmployeeBO employeeBO)
			throws EmployeeManagementException {
		Mono<Employee> emp = empRepo.findById(employeeBO.getEmpId().toString());
		if (emp.block() == null) {
			throw new EmployeeManagementException("No such Employee found!");
		} else {
			Employee updateEmployee = emp.block();
			Employee employee = new Employee();
			BeanUtils.copyProperties(employeeBO, employee);
			updateEmployee.setSalary(employee.getSalary());
			updateEmployee.setBand(employee.getBand());
			Mono<Employee> updatedEmployee = empRepo.save(updateEmployee);
			EmployeeBO updatedEmployeeBO = new EmployeeBO();
			BeanUtils
					.copyProperties(updatedEmployee.block(), updatedEmployeeBO);
			return updatedEmployeeBO;
		}
	}

	/*
	 * Method to fetch all employees under a specific band from the list
	 * 
	 * @param String band
	 * 
	 * @return List<EmployeeBO>
	 */
	@Override
	public List<EmployeeBO> getEmployeesFromBand(String band)
			throws EmployeeManagementException {
		Flux<Employee> employeeFluxList = empRepo.findAll();
		List<Employee> employeeList = employeeFluxList.collectList().block();
		if (CollectionUtils.isEmpty(employeeList)) {
			throw new EmployeeManagementException("No Employee List found!!");
		} else {
			List<Employee> employeeBandList = new ArrayList<Employee>();
			for (Employee employee : employeeList) {
				if (employee.getBand().equalsIgnoreCase(band)) {
					employeeBandList.add(employee);
				}
			}
			List<EmployeeBO> employeeBandListBO = new ArrayList<EmployeeBO>();
			employeeBandList.forEach(employee -> {
				EmployeeBO employeeBO = new EmployeeBO();
				BeanUtils.copyProperties(employee, employeeBO);
				employeeBandListBO.add(employeeBO);
			});
			return employeeBandListBO;
		}
	}

	/*
	 * Method to fetch all employees under a specific account from the list
	 * 
	 * @param String accName
	 * 
	 * @return List<EmployeeBO>
	 */
	@Override
	public List<EmployeeBO> getByAcc(String accName) {
		Flux<Employee> employeeFluxList = empRepo.findAll();
		List<Employee> employeeList = employeeFluxList.collectList().block();
		if (CollectionUtils.isEmpty(employeeList)) {
			throw new EmployeeManagementException("No Employee List found!!");
		} else {
			List<Employee> employeeBandList = new ArrayList<Employee>();
			for (Employee employee : employeeList) {
				if (employee.getAccName().equalsIgnoreCase(accName)) {
					employeeBandList.add(employee);
				}
			}
			List<EmployeeBO> employeeAccListBO = new ArrayList<EmployeeBO>();
			employeeBandList.forEach(employee -> {
				EmployeeBO employeeBO = new EmployeeBO();
				BeanUtils.copyProperties(employee, employeeBO);
				employeeAccListBO.add(employeeBO);
			});
			return employeeAccListBO;
		}
	}

	/*
	 * Method to delete employees under a specific band under a specific account
	 * from the list
	 * 
	 * @param String band
	 * 
	 * @return List<EmployeeBO>
	 */
	@Override
	public List<EmployeeBO> deleteByAcc(String accName, String band) {
		Flux<Employee> employeeFluxList = empRepo.findAll();
		List<Employee> employeeList = employeeFluxList.collectList().block();
		if (CollectionUtils.isEmpty(employeeList)) {
			throw new EmployeeManagementException("No Employee List found!!");
		} else {
			for (Employee employee : employeeList) {
				if (employee.getAccName().equalsIgnoreCase(accName)) {
					if (employee.getBand().equalsIgnoreCase(band)) {
						empRepo.delete(employee).block();
					}
				}
			}
			List<EmployeeBO> employeeAccListBO = new ArrayList<EmployeeBO>();
			employeeList.forEach(employee -> {
				EmployeeBO employeeBO = new EmployeeBO();
				BeanUtils.copyProperties(employee, employeeBO);
				employeeAccListBO.add(employeeBO);
			});
			return employeeAccListBO;
		}
	}

	/*
	 * Method to add bonus to a specific employee in the list
	 * 
	 * @body UpdateEmployeeBO employeeBO
	 * 
	 * @return EmployeeBO
	 */
	@Override
	public EmployeeBO addBonus(UpdateEmployeeBO employeeBO)
			throws EmployeeManagementException {
		Mono<Employee> emp = empRepo.findById(employeeBO.getEmpId().toString());
		Employee updateEmployee = emp.block();
		if (updateEmployee == null) {
			throw new EmployeeManagementException("No such Employee found!");
		} else {
			updateEmployee.setSalary(updateEmployee.getSalary()
					+ employeeBO.getBonus());
			Mono<Employee> updatedEmployee = empRepo.save(updateEmployee);
			EmployeeBO updatedEmployeeBO = new EmployeeBO();
			BeanUtils
					.copyProperties(updatedEmployee.block(), updatedEmployeeBO);
			return updatedEmployeeBO;
		}
	}

	/*
	 * Method to add a specific % of allowance to all the employee's salary in
	 * the list
	 * 
	 * @param Double allowance
	 * 
	 * @return List<EmployeeBO>
	 */
	@Override
	public List<EmployeeBO> addAllowance(Double allowance) {
		Flux<Employee> employeeFluxList = empRepo.findAll();
		List<Employee> employeeDetailsList = employeeFluxList.collectList()
				.block();
		if (CollectionUtils.isEmpty(employeeDetailsList)) {
			throw new EmployeeManagementException("No Employee List found!!");
		} else {
			employeeDetailsList.forEach(employee -> {
				Double allowances = (employee.getSalary() * allowance) / 100.0;
				employee.setSalary(employee.getSalary() + allowances);
				empRepo.save(employee).block();
			});
			List<EmployeeBO> employeeDetailsListBO = new ArrayList<EmployeeBO>();
			employeeDetailsList.forEach(employee -> {
				EmployeeBO employeeBO = new EmployeeBO();
				BeanUtils.copyProperties(employee, employeeBO);
				employeeDetailsListBO.add(employeeBO);
			});
			return employeeDetailsListBO;
		}
	}

	/*
	 * Method to add list of freshers to the list
	 * 
	 * @body List<EmployeeBO> newEmployeeListBO
	 * 
	 * @return List<EmployeeBO>
	 */
	@Override
	public List<EmployeeBO> addNewEmployees(List<EmployeeBO> newEmployeeListBO) {
		List<Employee> newEmployeeList = new ArrayList<Employee>();
		newEmployeeListBO.forEach(emp -> {
			Employee employee = new Employee();
			BeanUtils.copyProperties(emp, employee);
			newEmployeeList.add(employee);
		});
		Flux<Employee> newEmployeeFlux = empRepo.saveAll(newEmployeeList);
		List<Employee> newEmployeeAddedList = newEmployeeFlux.collectList()
				.block();
		List<EmployeeBO> newEmployeeAddedListBO = new ArrayList<EmployeeBO>();
		newEmployeeAddedList.forEach(employee -> {
			EmployeeBO employeeBO = new EmployeeBO();
			BeanUtils.copyProperties(employee, employeeBO);
			newEmployeeAddedListBO.add(employeeBO);
		});
		return newEmployeeAddedListBO;
	}

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
	@Override
	public List<Employee> getEmpByNameQuery(String name, Integer offset,
			Integer limit) {
		List<Employee> empList = empDao.getEmployeesWithLimit(name, offset,
				limit);
		return empList;
	}

	/*
	 * Custom Query Method to fetch employee id, name and joiningDate from a
	 * specific band from the list
	 * 
	 * @param String band
	 * 
	 * @return List<EmployeeNames>
	 */
	@Override
	public List<EmployeeNames> getEmpByNameByBand(String band) {
		List<EmployeeNames> empList = empDao.getEmployeeByBand(band);
		return empList;
	}
}
