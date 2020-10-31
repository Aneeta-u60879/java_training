package com.example.employeemgmt.handler;

import static com.example.employeemgmt.common.EmployeeMgmtConstants.CONFLICT_EMPLOYEE_EXIST;
import static com.example.employeemgmt.common.EmployeeMgmtConstants.CONFLICT_EMPLOYEE_NOT_FOUND;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.example.employeemgmt.bo.DeleteEmployeeBO;
import com.example.employeemgmt.bo.EmployeeBO;
import com.example.employeemgmt.bo.EmployeeBonusBO;
import com.example.employeemgmt.bo.EmployeeUpdateBO;
import com.example.employeemgmt.dao.EmployeeMgmtDAO;
import com.example.employeemgmt.exception.EmployeeMgmtException;
import com.example.employeemgmt.model.Employee;
import com.example.employeemgmt.repo.IEmployeeMgmtRepo;

/**
 * Handler class for Employee Mgmt.
 * 
 * @author 144900
 *
 */
@Service
public class EmployeeMgmtHandlerImpl implements IEmployeeMgmtHandler {

	@Autowired
	IEmployeeMgmtRepo repo;

	@Autowired
	EmployeeMgmtDAO dao;

	/**
	 * Function for fetching employees from repo.
	 * 
	 * @return List<EmployeeBO>
	 */
	@Override
	public List<EmployeeBO> getEmpList() {
		Flux<Employee> employeeList = repo.findAll();
		List<EmployeeBO> empBOList = new ArrayList<EmployeeBO>();
		List<Employee> empList = employeeList.collectList().block();
		if (!CollectionUtils.isEmpty(empList)) {
			empList.forEach(eachEmp -> {
				EmployeeBO employeeBO = new EmployeeBO();
				BeanUtils.copyProperties(eachEmp, employeeBO);
				empBOList.add(employeeBO);
			});
		}
		return empBOList;
	}

	/**
	 * Function for Searching an employee from repo.
	 * 
	 * @return EmployeeBO
	 */
	@Override
	public EmployeeBO searchById(String employeeId) {
		Mono<Employee> emp = repo.findById(employeeId);
		if (emp.block() == null) {
			throw new EmployeeMgmtException(CONFLICT_EMPLOYEE_NOT_FOUND);
		} else {
			EmployeeBO employeeBO = new EmployeeBO();
			BeanUtils.copyProperties(emp.block(), employeeBO);
			return employeeBO;
		}
	}

	/**
	 * Function for searching employees using band from repo.
	 * 
	 * @return List<EmployeeBO>
	 */
	@Override
	public List<EmployeeBO> searchByBand(String band) {
		Flux<Employee> empBandWiseList = repo.getEmpByBand(band);
		List<Employee> empList = empBandWiseList.collectList().block();
		if (CollectionUtils.isEmpty(empList)) {
			throw new EmployeeMgmtException(CONFLICT_EMPLOYEE_NOT_FOUND);
		} else {
			List<EmployeeBO> employeeBOList = new ArrayList<EmployeeBO>();
			empList.forEach(eachEmp -> {
				EmployeeBO employeeBO = new EmployeeBO();
				BeanUtils.copyProperties(eachEmp, employeeBO);
				employeeBOList.add(employeeBO);
			});
			return employeeBOList;
		}
	}

	/**
	 * Function for add an employee.
	 * 
	 * @return EmployeeBO
	 */
	@Override
	public EmployeeBO addEmployee(EmployeeBO newEmployee)
			throws EmployeeMgmtException {
		Mono<Employee> emp = repo.findById(newEmployee.getEmployeeId());
		if (emp.block() == null) {
			Employee employee = new Employee();
			BeanUtils.copyProperties(newEmployee, employee);
			Mono<Employee> saveEmp = repo.save(employee);
			EmployeeBO empBO = new EmployeeBO();
			BeanUtils.copyProperties(saveEmp.block(), empBO);
			return empBO;
		} else {
			throw new EmployeeMgmtException(CONFLICT_EMPLOYEE_EXIST);
		}
	}

	/**
	 * Function for adding employees.
	 * 
	 * @return List<EmployeeBO>
	 */
	@Override
	public List<EmployeeBO> addFreshers(List<EmployeeBO> newEmpList) {
		List<Employee> emp = new ArrayList<Employee>();
		newEmpList.forEach(eachEmp -> {
			Employee employee = new Employee();
			BeanUtils.copyProperties(eachEmp, employee);
			emp.add(employee);
		});
		Flux<Employee> saveEmp = repo.saveAll(emp);
		List<Employee> employeeList = saveEmp.collectList().block();
		List<EmployeeBO> employeeBOList = new ArrayList<EmployeeBO>();
		employeeList.forEach(eachEmp -> {
			EmployeeBO employeeBO = new EmployeeBO();
			BeanUtils.copyProperties(eachEmp, employeeBO);
			employeeBOList.add(employeeBO);
		});
		return employeeBOList;
	}

	/**
	 * Function for deleting an employee using employee Id.
	 * 
	 * @return EmployeeBO
	 */
	@Override
	public EmployeeBO deleteEmployee(String employeeId)
			throws EmployeeMgmtException {
		Mono<Employee> emp = repo.findById(employeeId);
		if (emp.block() == null) {
			throw new EmployeeMgmtException(CONFLICT_EMPLOYEE_NOT_FOUND);
		} else {
			Mono<Employee> del = emp.flatMap(each -> repo.delete(each).then(
					Mono.just(each)));
			EmployeeBO empBO = new EmployeeBO();
			BeanUtils.copyProperties(del.block(), empBO);
			return empBO;
		}
	}

	/**
	 * Function for deleting employees using account name and band.
	 * 
	 * @return List<EmployeeBO>
	 */
	@Override
	public List<EmployeeBO> deleteEmployeeByBand(DeleteEmployeeBO deleteByBand)
			throws EmployeeMgmtException {
		Flux<Employee> empList = repo.getEmpByAccountNameAndBand(
				deleteByBand.getAccountName(), deleteByBand.getBand());
		List<Employee> empDelList = empList.collectList().block();
		if (CollectionUtils.isEmpty(empDelList)) {
			throw new EmployeeMgmtException(CONFLICT_EMPLOYEE_NOT_FOUND);
		}
		List<EmployeeBO> employeeBOList = new ArrayList<EmployeeBO>();
		empDelList.forEach(eachEmp -> {
			Mono<Employee> del = Mono.just(eachEmp).flatMap(
					each -> repo.delete(each).then(Mono.just(each)));
			EmployeeBO employeeBO = new EmployeeBO();
			BeanUtils.copyProperties(del.block(), employeeBO);
			employeeBOList.add(employeeBO);
		});
		return employeeBOList;
	}

	/**
	 * Function for updating an employee.
	 * 
	 * @return EmployeeBO
	 */
	@Override
	public EmployeeBO updateEmployee(EmployeeUpdateBO updateEmp)
			throws EmployeeMgmtException {
		Mono<Employee> emp = repo.findById(updateEmp.getEmployeeId());
		if (emp.block() == null) {
			throw new EmployeeMgmtException(CONFLICT_EMPLOYEE_NOT_FOUND);
		} else {
			Employee employee = emp.block();
			employee.setExperience(updateEmp.getExperience());
			employee.setBand(updateEmp.getBand());
			Mono<Employee> saveEmp = repo.save(employee);
			EmployeeBO empBO = new EmployeeBO();
			BeanUtils.copyProperties(saveEmp.block(), empBO);
			return empBO;
		}
	}

	/**
	 * Function for adding allowance to all employees.
	 * 
	 * @return List<EmployeeBO>
	 */
	@Override
	public List<EmployeeBO> addAllowance(Double allowance) {
		Flux<Employee> empList = repo.findAll();
		List<Employee> newList = empList.collectList().block();
		List<EmployeeBO> empBOList = new ArrayList<EmployeeBO>();
		if (!CollectionUtils.isEmpty(newList)) {
			newList.forEach(eachEmp -> {
				if (eachEmp.getSalary() != null) {
					eachEmp.setSalary(eachEmp.getSalary()
							+ (eachEmp.getSalary() * allowance) / 100);
				}
			});
			Flux<Employee> saveEmp = repo.saveAll(newList);
			List<Employee> employeeList = saveEmp.collectList().block();
			employeeList.forEach(eachEmp -> {
				EmployeeBO empBO = new EmployeeBO();
				BeanUtils.copyProperties(eachEmp, empBO);
				empBOList.add(empBO);
			});
		}
		return empBOList;

	}

	/**
	 * Function for searching employees using account name.
	 * 
	 * @return List<EmployeeBO>
	 */
	@Override
	public List<EmployeeBO> searchByAcc(String accountName) {
		Flux<Employee> empAccountWiseList = repo
				.getEmpByAccountName(accountName);
		List<Employee> empList = empAccountWiseList.collectList().block();
		if (CollectionUtils.isEmpty(empList)) {
			throw new EmployeeMgmtException(CONFLICT_EMPLOYEE_NOT_FOUND);
		} else {
			List<EmployeeBO> employeeBOList = new ArrayList<EmployeeBO>();
			empList.forEach(eachEmp -> {
				EmployeeBO employeeBO = new EmployeeBO();
				BeanUtils.copyProperties(eachEmp, employeeBO);
				employeeBOList.add(employeeBO);
			});
			return employeeBOList;
		}
	}

	/**
	 * Function for adding bonus to an employee.
	 * 
	 * @return EmployeeBO
	 */
	@Override
	public EmployeeBO addBonus(EmployeeBonusBO employeeBonusBO)
			throws EmployeeMgmtException {
		Mono<Employee> emp = repo.findById(employeeBonusBO.getEmployeeId());
		if (emp.block() != null) {
			Employee employee = emp.block();
			employee.setSalary(employee.getSalary()
					+ (employee.getSalary() * employeeBonusBO.getBonus()) / 100);
			EmployeeBO employeeBO = new EmployeeBO();
			BeanUtils.copyProperties(employee, employeeBO);
			return employeeBO;
		} else {
			throw new EmployeeMgmtException(CONFLICT_EMPLOYEE_NOT_FOUND);
		}
	}

	/**
	 * Function for fetching employees from repo using custom query.
	 * 
	 * @return List<EmployeeBO>
	 */
	@Override
	public List<EmployeeBO> getEmpByNameQuery(String name, Integer offset,
			Integer limit) {
		List<Employee> empList = dao.getEmployeesWithLimit(name, offset, limit);
		if (CollectionUtils.isEmpty(empList)) {
			throw new EmployeeMgmtException(CONFLICT_EMPLOYEE_NOT_FOUND);
		}
		List<EmployeeBO> employeeBOList = new ArrayList<EmployeeBO>();
		empList.forEach(eachEmp -> {
			EmployeeBO employeeBO = new EmployeeBO();
			BeanUtils.copyProperties(eachEmp, employeeBO);
			employeeBOList.add(employeeBO);
		});
		return employeeBOList;
	}

	/**
	 * Function for fetching an employee from repo using custom query.
	 * 
	 * @return EmployeeBO
	 */
	@Override
	public EmployeeBO getEmployeesWithId(String employeeId) {
		Employee employee = dao.getEmployeesWithId(employeeId);
		if (employee == null) {
			throw new EmployeeMgmtException(CONFLICT_EMPLOYEE_NOT_FOUND);
		}
		EmployeeBO employeeBO = new EmployeeBO();
		BeanUtils.copyProperties(employee, employeeBO);
		return employeeBO;

	}
}
