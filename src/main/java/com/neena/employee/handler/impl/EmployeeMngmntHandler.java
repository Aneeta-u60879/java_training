package com.neena.employee.handler.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.neena.employee.bo.EmployeeMangmntBO;
import com.neena.employee.bo.UpdateBonusBo;
import com.neena.employee.bo.UpdateExperience;
import com.neena.employee.dao.EmployeeDao;
import com.neena.employee.exception.EmployeeManagementException;
import com.neena.employee.handler.IEmployeeMngmntHandler;
import com.neena.employee.model.Employee;
import com.neena.employee.model.Model;
import com.neena.employee.repo.IEmployeeMangmntRepo;

@Service
public class EmployeeMngmntHandler implements IEmployeeMngmntHandler {

	@Autowired
	IEmployeeMangmntRepo repo;

	@Autowired
	EmployeeDao dao;

	/* (non-Javadoc)
	 * @see com.neena.employee.handler.IEmployeeMngmntHandler#getEmployeeList()
	 * To get the list of employees
	 */
	@Override
	public Flux<Employee> getEmployeeList() {
		Flux<Employee> employeeList = repo.findAll();
		return employeeList;

	}

	/* (non-Javadoc)
	 * @see com.neena.employee.handler.IEmployeeMngmntHandler#saveEmploy(com.neena.employee.model.Employee)
	 * To save an employee
	 */
	@Override
	public Employee saveEmploy(Employee employ) {
		Mono<Employee> employ1 = repo.save(employ);
		return employ1.block();

	}

	/* (non-Javadoc)
	 * @see com.neena.employee.handler.IEmployeeMngmntHandler#findByAccountName(java.lang.String)
	 * To get a list of employees of a given account
	 */
	@Override
	public Flux<Employee> findByAccountName(String accountName) {
		Flux<Employee> employee = repo.findByAccountName(accountName);
		return employee;

	}

	/* (non-Javadoc)
	 * @see com.neena.employee.handler.IEmployeeMngmntHandler#findById(java.lang.String)
	 * To find an employee by ID
	 */
	@Override
	public Mono<Employee> findById(String id) {
		Mono<Employee> employById = repo.findById(id);
		return employById;
	}

	/* (non-Javadoc)
	 * @see com.neena.employee.handler.IEmployeeMngmntHandler#searchById(java.lang.String)
	 * To get an employee byits Id
	 */
	@Override
	public EmployeeMangmntBO searchById(String id) {
		Mono<Employee> emp = repo.findById(id);

		if (emp.block() == null) {
			throw new EmployeeManagementException("No such Employee Found");

		} else {
			EmployeeMangmntBO employeeBO = new EmployeeMangmntBO();
			BeanUtils.copyProperties(emp.block(), employeeBO);
			return employeeBO;
		}
	}

	/* (non-Javadoc)
	 * @see com.neena.employee.handler.IEmployeeMngmntHandler#deleteEmploy(java.lang.String)
	 * To delete an employee from table by a given ID
	 */
	@Override
	public Mono<Employee> deleteEmploy(String id) {
		Mono<Employee> emp = repo.findById(id);
		Mono<Employee> deltdEmployee = emp.flatMap(employ -> repo
				.delete(employ).then(Mono.just(employ)));
		return deltdEmployee;
	}
 
	/* (non-Javadoc)
	 *@see com.neena.employee.handler.IEmployeeMngmntHandler#updateEmployee(com.neena.employee.bo.UpdateExperience)
	 *To update experience and employee band of an employee
	 */
	@Override
	public EmployeeMangmntBO updateEmployee(UpdateExperience updateEmp)
			throws EmployeeManagementException {
		Mono<Employee> emp = repo.findById(updateEmp.getId());
		if (emp.block() == null) {
			throw new EmployeeManagementException("No Such employee");
		} else {
			Employee employee = emp.block();
			employee.setExperience(updateEmp.getExperience());
			employee.setEmplyBand(updateEmp.getBand());
			Mono<Employee> saveEmpl = repo.save(employee);
			EmployeeMangmntBO empBo = new EmployeeMangmntBO();
			BeanUtils.copyProperties(saveEmpl.block(), empBo);
			return empBo;
		}
	}
	/* (non-Javadoc)
	 * @see com.neena.employee.handler.IEmployeeMngmntHandler#updateBonus(com.neena.employee.bo.UpdateBonusBo)
	 * To update the salary of a given employee by adding bonus.
	 */
	@Override
	public EmployeeMangmntBO updateBonus(UpdateBonusBo updateBonus)
			throws EmployeeManagementException {
		Mono<Employee> empbonus = repo.findById(updateBonus.getId());
		if (empbonus.block() == null) {
			throw new EmployeeManagementException("No Such employee");
		} else {
			Employee employee = empbonus.block();
			employee.setSalary(employee.getSalary() + updateBonus.getBonus());
			Mono<Employee> saveEmplBonus = repo.save(employee);
			EmployeeMangmntBO empBo = new EmployeeMangmntBO();
			BeanUtils.copyProperties(saveEmplBonus.block(), empBo);
			return empBo;
		}
	}
	/* (non-Javadoc)
	 * @see com.neena.employee.handler.IEmployeeMngmntHandler#getEmpByNameQuery(java.lang.String, java.lang.Integer, java.lang.Integer)
	 * To get a list of employees with custom query
	 */
	@Override
	public List<Employee> getEmpByNameQuery(String name, Integer offset,
			Integer limit) {
		List<Employee> empList = dao.getEmployeesWithLimit(name, offset, limit);
		return empList;
	}

	/* (non-Javadoc)
	 * @see com.neena.employee.handler.IEmployeeMngmntHandler#getEmpIdByNameQuery(java.lang.String, java.lang.Integer, java.lang.Integer)
	 * To get specific details of a list of employees with custom query
	 */
	@Override
	public List<Model> getEmpIdByNameQuery(String name, Integer offset,
			Integer limit) {
		List<Model> empList = dao.getEmployeesWithID(name, offset, limit);
		return empList;
	}  
	/* (non-Javadoc)
	 * @see com.neena.employee.handler.IEmployeeMngmntHandler#addFreshers(java.util.List)
	 * To add a group of freshers to the table of employee
	 */
	@Override
	public List<EmployeeMangmntBO> addFreshers(
			List<EmployeeMangmntBO> newEmpList) { 
		List<Employee> emp = new ArrayList<Employee>();
		newEmpList.forEach(eachEmp -> {
			Employee employee = new Employee();
			BeanUtils.copyProperties(eachEmp, employee);
			emp.add(employee);
		});
		Flux<Employee> saveEmp = repo.saveAll(emp);
		List<Employee> employeeList = saveEmp.collectList().block();
		List<EmployeeMangmntBO> employeeBOList = new ArrayList<EmployeeMangmntBO>();
		employeeList.forEach(eachEmp -> {
			EmployeeMangmntBO employeeBO = new EmployeeMangmntBO();
			BeanUtils.copyProperties(eachEmp, employeeBO);
			employeeBOList.add(employeeBO);

		});
		return employeeBOList;
	}

	/* (non-Javadoc)
	 * @see com.neena.employee.handler.IEmployeeMngmntHandler#addEmployee(com.neena.employee.bo.EmployeeMangmntBO)
	 * To add an employee to the table of Employee
	 */
	@Override
	public EmployeeMangmntBO addEmployee(EmployeeMangmntBO newEmp)
			throws EmployeeManagementException {
		Mono<Employee> emp = repo.findById(newEmp.getId());
		if (emp.block() == null) {
			Employee employee = new Employee();
			BeanUtils.copyProperties(newEmp, employee);
			Mono<Employee> saveEmp = repo.save(employee);
			EmployeeMangmntBO empBO = new EmployeeMangmntBO();
			BeanUtils.copyProperties(saveEmp.block(), empBO);
			return empBO;
		} else {
			throw new EmployeeManagementException("Employee Already Exist");

		}
	}

}

