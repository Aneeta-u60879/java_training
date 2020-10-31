package com.neena.employee.controller;

import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.neena.employee.bo.EmployeeMangmntBO;
import com.neena.employee.bo.UpdateBonusBo;
import com.neena.employee.bo.UpdateExperience;
import com.neena.employee.dto.EmployeeManagementDto;
import com.neena.employee.dto.UpdateBonusDto;
import com.neena.employee.dto.UpdateExperienceDto;
import com.neena.employee.exception.EmployeeManagementException;
import com.neena.employee.handler.impl.EmployeeMngmntHandler;
import com.neena.employee.model.Employee;
import com.neena.employee.model.Model;

/**
 * Controller for the CRUD operations
 * @author 144787
 *
 */
@RestController
public class EmployeeMangmntController {

	@Autowired
	EmployeeMngmntHandler handler;

	/**
	 * Get the list of all employees from the table
	 * @return List of employees
	 */
	@GetMapping(value = "/all")
	@SwaggerToken
	@ApiOperation(value = "Fetch all employees", notes = "Returns 200/204 Response", httpMethod = javax.ws.rs.HttpMethod.GET)
	public Flux<Employee> getEmployeeList() {
		Flux<Employee> empList = handler.getEmployeeList();
		return empList;

	}  

	/**
	 * To Save an employee
	 * @param employee
	 * @return the added employee or satus code 409
	 */
	@PostMapping(value = "/save") 
	@SwaggerToken
	@ApiOperation(value = "Save employees", notes = "Returns 200/409 Response", httpMethod = javax.ws.rs.HttpMethod.POST)
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
		ResponseEntity<Employee> response = null;
		Employee newEmployee = handler.saveEmploy(employee);
		if (newEmployee != null) {
			response = ResponseEntity.ok(newEmployee);
		} else {
			response = ResponseEntity.status(409).build();
		}

		return response;
	}

	/**
	 * To get all the employees of a given account
	 * @param accountName
	 * @return the list of employee of a given account or status code 409
	 */
	@GetMapping(value = "/ByAccount/{accountName}")
	@SwaggerToken
	@ApiOperation(value = "Fetch all employees by account", notes = "Returns 200/409 Response", httpMethod = javax.ws.rs.HttpMethod.GET)
	public ResponseEntity<Flux<Employee>> findByAccountName(
			@PathVariable("accountName") String accountName) {
		ResponseEntity<Flux<Employee>> response = null;
		Flux<Employee> empList = handler.findByAccountName(accountName);
		if (empList != null) {
			response = ResponseEntity.ok(empList);
		} else {
			response = ResponseEntity.status(409).build();
		}

		return response;
	}

	/**
	 * Fetch an employee by Id
	 * @param id
	 * @return the employee or 400 status code
	 */
	@GetMapping(value = "/id/{id}")
	@SwaggerToken
	@ApiOperation(value = "Fetch all employees", notes = "Returns 200/400 Response", httpMethod = javax.ws.rs.HttpMethod.GET)
	public ResponseEntity<?> getById(@PathVariable("id") String id) {
		ResponseEntity<?> response = null;
		Mono<Employee> empList = handler.findById(id);
		if (empList != null) {
			response = ResponseEntity.ok(empList);
		} else {
			response = ResponseEntity.status(400)
					.body("No such employee found");
		}

		return response;
	}

	/**
	 * Fetch an employee by Id
	 * @param employeeId
	 * @return the employee 
	 * @throws EmployeeManagementException
	 */
	@GetMapping(value = "/searchbyid/{id}")
	@SwaggerToken
	@ApiOperation(value = "Fetch all employees", notes = "Returns 200/204 Response", httpMethod = javax.ws.rs.HttpMethod.GET)
	public ResponseEntity<?> searchById(
			@PathVariable(value = "id") String employeeId)
			throws EmployeeManagementException {
		ResponseEntity<EmployeeManagementDto> response = null;
		EmployeeMangmntBO employee = handler.searchById(employeeId);
		if (employee != null) {
			EmployeeManagementDto empDTO = new EmployeeManagementDto();
			BeanUtils.copyProperties(employee, empDTO);

			response = ResponseEntity.ok(empDTO);

		}
		return response;

	}

	/**
	 * Delete an employee by its Id
	 * @param id
	 * @return deleted employee or 400 status code
	 */
	@DeleteMapping(value = "/delete/{id}")
	@SwaggerToken
	@ApiOperation(value = "Delete employee", notes = "Returns 200/400 Response", httpMethod = javax.ws.rs.HttpMethod.DELETE)
	public ResponseEntity<?> deleteEmployee(@PathVariable("id") String id) {
		ResponseEntity<?> response = null;
		Mono<Employee> newEmployeeList = handler.deleteEmploy(id);
		if (newEmployeeList != null) {
			response = ResponseEntity.ok(newEmployeeList);
		} else {
			response = ResponseEntity.status(400)
					.body("No such employee found");
		}

		return response;
	}

	/**
	 * Add a list of freshers
	 * @param employ
	 * @return List of freshers or status code 409
	 */
	@PostMapping(value = "/addFreshers")
	@SwaggerToken
	@ApiOperation(value = "Add freshers", notes = "Returns 200/409 Response", httpMethod = javax.ws.rs.HttpMethod.POST)
	public ResponseEntity<?> addFreshers(
			@RequestBody List<EmployeeMangmntBO> employ) {
		ResponseEntity<?> response = null;
		List<EmployeeMangmntBO> empList = handler.addFreshers(employ);
		if (empList != null) {
			response = ResponseEntity.ok(empList);
		} else {
			response = ResponseEntity.status(409).build();
		}

		return response;
	}

	/**
	 * To update the experience and band of an employee 
	 * @param updateEmp
	 * @return updated employee
	 */
	@PostMapping(value = "/updateexp")
	@SwaggerToken
	@ApiOperation(value = "Update Employee", notes = "Returns 200 Response", httpMethod = javax.ws.rs.HttpMethod.POST)
	public ResponseEntity<?> updateEmployee(
			@RequestBody UpdateExperienceDto updateEmp) {
		ResponseEntity<?> response = null;
		UpdateExperience updateExperienceBo = new UpdateExperience();
		BeanUtils.copyProperties(updateEmp, updateExperienceBo);
		EmployeeMangmntBO empList = handler.updateEmployee(updateExperienceBo);
		response = new ResponseEntity<EmployeeMangmntBO>(empList, HttpStatus.OK);
		return response;
	}

	/**
	 * To update the salary of an employee by adding bonus
	 * @param updateBonusDto
	 * @return the employee with updated salary
	 */
	@PostMapping(value = "/updatebonus")
	@SwaggerToken
	@ApiOperation(value = "Update Bonus", notes = "Returns 200  Response", httpMethod = javax.ws.rs.HttpMethod.POST)
	public ResponseEntity<?> updateBonus(
			@RequestBody UpdateBonusDto updateBonusDto) {
		ResponseEntity<?> response = null;
		UpdateBonusBo updateBonusBo = new UpdateBonusBo();
		BeanUtils.copyProperties(updateBonusDto, updateBonusBo);
		EmployeeMangmntBO empBonusList = handler.updateBonus(updateBonusBo);
		response = new ResponseEntity<EmployeeMangmntBO>(empBonusList,
				HttpStatus.OK);
		return response;
	}

	/**
	 * To get list of employ using custom query 
	 * @param name
	 * @param offset
	 * @param limit
	 * @return List of employees
	 */
	@GetMapping(value = "/custom/{name}/{offset}/{limit}")
	@SwaggerToken
	@ApiOperation(value = "Get Employee By CustomName", notes = "Returns 200 Response", httpMethod = javax.ws.rs.HttpMethod.GET)
	public List<Employee> getEmployeeByCustomName(
			@PathVariable("name") String name,
			@PathVariable("offset") Integer offset,
			@PathVariable("limit") Integer limit) {
		List<Employee> employee = handler
				.getEmpByNameQuery(name, offset, limit);
		
		return employee;
	}

	/**
	 * To get specific details of a list of employee using custom query
	 * @param name
	 * @param offset
	 * @param limit
	 * @return A list of employee with specific details
	 */
	@GetMapping(value = "/id/{name}/{offset}/{limit}")
	@SwaggerToken
	@ApiOperation(value = "Get Employee ByID", notes = "Returns 200 Response", httpMethod = javax.ws.rs.HttpMethod.GET)
	public List<Model> getEmployeeByID(@PathVariable("name") String name,
			@PathVariable("offset") Integer offset,
			@PathVariable("limit") Integer limit) {
		List<Model> employee = handler.getEmpIdByNameQuery(name, offset, limit);
		return employee;
	}

	/**
	 * To add a list of employees
	 * @param newEmp
	 * @return Added List of employees
	 */
	@PostMapping(value = "/addemployee")
	@SwaggerToken
	@ApiOperation(value = "Add employees", notes = "Returns 200 Response", httpMethod = javax.ws.rs.HttpMethod.POST)
	public ResponseEntity<?> addEmployee(
			@RequestBody EmployeeManagementDto newEmp) {
		ResponseEntity<?> response = null;
		EmployeeMangmntBO employeeBO = new EmployeeMangmntBO();
		BeanUtils.copyProperties(newEmp, employeeBO);
		System.out.println(employeeBO);
		EmployeeMangmntBO empresponseBO = handler.addEmployee(employeeBO);
		EmployeeManagementDto empresponseDTO = new EmployeeManagementDto();
		BeanUtils.copyProperties(empresponseBO, empresponseDTO);

		response = new ResponseEntity<EmployeeManagementDto>(empresponseDTO,
				HttpStatus.OK);
		return response;
	}

}
