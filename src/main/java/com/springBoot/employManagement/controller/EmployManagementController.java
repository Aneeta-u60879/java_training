package com.springBoot.employManagement.controller;

import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.springBoot.employManagement.bo.EmployBO;
import com.springBoot.employManagement.bo.UpdateAllowance;
import com.springBoot.employManagement.bo.UpdateBonusBO;
import com.springBoot.employManagement.bo.UpdateExperienceBO;
import com.springBoot.employManagement.dto.EmployDto;
import com.springBoot.employManagement.dto.UpdateAllowanceDto;
import com.springBoot.employManagement.dto.UpdateBonusDto;
import com.springBoot.employManagement.dto.UpdateExperienceDto;
import com.springBoot.employManagement.handler.impl.EmployManagementHandlerImpl;
import com.springBoot.employManagement.model.Employee;
import com.springBoot.employManagement.model.Model;

/**
 * Controller for CRUD operation
 * 
 * @author 144785
 *
 */
@RestController
public class EmployManagementController {

	@Autowired
	EmployManagementHandlerImpl handler;

	/**
	 * Fetch all employees
	 * 
	 * @return Flux<Employee>
	 */
	@GetMapping(value = "/all")
	@SwaggerToken
	@ApiOperation(value = "Fetch all employees", notes = "Returns 200/204 Response", httpMethod = javax.ws.rs.HttpMethod.GET)
	public Flux<Employee> getEmployeeList() {
		Flux<Employee> employeeListBO = handler.getEmployeeList();
		return employeeListBO;
	}

	/**
	 * Save employees
	 * 
	 * @param employDto
	 * @return EmployBO
	 */
	@PostMapping(value = "/save")
	@SwaggerToken
	@ApiOperation(value = "Save employees", notes = "Returns 200/204 Response", httpMethod = javax.ws.rs.HttpMethod.POST)
	public ResponseEntity<?> saveEmployee(@RequestBody EmployDto employDto) {
		EmployBO employBO = new EmployBO();
		BeanUtils.copyProperties(employDto, employBO);
		ResponseEntity<?> response = null;
		EmployBO newList = handler.saveEmployee(employBO);
		response = ResponseEntity.ok(newList);
		return response;
	}

	/**
	 * Fetch employees by account
	 * 
	 * @param accountName
	 * @return Flux<Employee>
	 */
	@GetMapping(value = "/account/{accountName}")
	@SwaggerToken
	@ApiOperation(value = "Fetch employees by account", notes = "Returns 200/204 Response", httpMethod = javax.ws.rs.HttpMethod.GET)
	public ResponseEntity<Flux<Employee>> getEmployeeByAccont(
			@PathVariable("accountName") String accountName) {
		ResponseEntity<Flux<Employee>> response = null;
		Flux<Employee> empList = handler.getEmployeeByAccountName(accountName);
		if (empList != null) {
			response = ResponseEntity.ok(empList);
		} else {
			response = ResponseEntity.status(409).build();
		}
		return response;
	}

	/**
	 * Fetch employees by band
	 * 
	 * @param band
	 * @return Flux<Employee>
	 */
	@GetMapping(value = "/band/{band}")
	@SwaggerToken
	@ApiOperation(value = "Fetch employees by band", notes = "Returns 200/204 Response", httpMethod = javax.ws.rs.HttpMethod.GET)
	public ResponseEntity<Flux<Employee>> getEmployeeByBand(
			@PathVariable("band") String band) {
		ResponseEntity<Flux<Employee>> response = null;
		Flux<Employee> empList = handler.getEmployeeByBand(band);
		if (empList != null) {
			response = ResponseEntity.ok(empList);
		} else {
			response = ResponseEntity.status(409).build();
		}
		return response;
	}

	/**
	 * Fetch employees by id
	 * 
	 * @param employId
	 * @return Flux<Employee>
	 */
	@GetMapping(value = "/employId/{employId}")
	@SwaggerToken
	@ApiOperation(value = "Fetch employees by id", notes = "Returns 200/204 Response", httpMethod = javax.ws.rs.HttpMethod.GET)
	public ResponseEntity<Flux<Employee>> getEmployeeByEmpId(
			@PathVariable("employId") Integer employId) {
		ResponseEntity<Flux<Employee>> response = null;
		Flux<Employee> empList = handler.getEmployeeByEmployId(employId);
		if (empList != null) {
			response = ResponseEntity.ok(empList);
		} else {
			response = ResponseEntity.status(409).build();
		}
		return response;
	}

	/**
	 * Delete employee
	 * 
	 * @param id
	 * @return Mono<Employee>
	 */
	@DeleteMapping(value = "/delete/{id}")
	@SwaggerToken
	@ApiOperation(value = "Delete employee", notes = "Returns 200/204 Response", httpMethod = javax.ws.rs.HttpMethod.DELETE)
	public Mono<Employee> deleteEmployee(@PathVariable("id") String id) {
		Mono<Employee> newEmployeeList = handler.deleteEmployee(id);
		return newEmployeeList;
	}

	/**
	 * Update experience of an employee
	 * 
	 * @param updateExperienceDto
	 * @return EmployBO
	 */
	@PostMapping(value = "/updateexpr")
	@SwaggerToken
	@ApiOperation(value = "Update experience of an employee", notes = "Returns 200/204 Response", httpMethod = javax.ws.rs.HttpMethod.POST)
	public ResponseEntity<?> updateExperience(
			@RequestBody UpdateExperienceDto updateExperienceDto) {
		ResponseEntity<?> response = null;
		UpdateExperienceBO updateExperienceBo = new UpdateExperienceBO();
		BeanUtils.copyProperties(updateExperienceDto, updateExperienceBo);
		EmployBO newList = handler.updateExperience(updateExperienceBo);
		response = new ResponseEntity<EmployBO>(newList, HttpStatus.OK);
		return response;

	}
 
	/**
	 * Update bonus of an employee
	 * 
	 * @param updateBonusDto
	 * @return EmployBO
	 */
	@PostMapping(value = "/updatebonus")
	@SwaggerToken
	@ApiOperation(value = "Update bonus of an employee", notes = "Returns 200/204 Response", httpMethod = javax.ws.rs.HttpMethod.POST)
	public ResponseEntity<?> updateBonus(
			@RequestBody UpdateBonusDto updateBonusDto) {
		ResponseEntity<?> response = null;
		UpdateBonusBO updateBonusBO = new UpdateBonusBO();
		BeanUtils.copyProperties(updateBonusDto, updateBonusBO);
		EmployBO newList = handler.updateBonus(updateBonusBO);
		response = new ResponseEntity<EmployBO>(newList, HttpStatus.OK);
		return response;
	}

	/**
	 * Update allowance for an employee
	 * 
	 * @param updateAllowanceDto
	 * @return EmployBO
	 */
	@PostMapping(value = "/updateallowance")
	@SwaggerToken
	@ApiOperation(value = "Update allowance for an employee", notes = "Returns 200/204 Response", httpMethod = javax.ws.rs.HttpMethod.POST)
	public ResponseEntity<?> updateAllowance(
			@RequestBody UpdateAllowanceDto updateAllowanceDto) {
		ResponseEntity<?> response = null;
		UpdateAllowance updateAllowance = new UpdateAllowance();
		BeanUtils.copyProperties(updateAllowanceDto, updateAllowance);
		EmployBO newList = handler.updateAllowance(updateAllowance);
		response = new ResponseEntity<EmployBO>(newList, HttpStatus.OK);
		return response;
	}

	/**
	 * Add list of freshers
	 * 
	 * @param employee
	 * @return List<EmployBO>
	 */
	@PostMapping(value = "/addFreshers")
	@SwaggerToken
	@ApiOperation(value = "Add list of freshers", notes = "Returns 200/409 Response", httpMethod = javax.ws.rs.HttpMethod.POST)
	public ResponseEntity<List<EmployBO>> addFreshers(
			@RequestBody List<EmployBO> employee) {
		ResponseEntity<List<EmployBO>> response = null;
		List<EmployBO> newList = handler.addFreshers(employee);
		if (!CollectionUtils.isEmpty(newList)) {
			response = ResponseEntity.ok(newList);
		} else {
			response = ResponseEntity.status(409).build();
		}
		return response;
	}

	/**
	 * Fetch employees by accountName based on querry
	 * 
	 * @param accountName
	 * @param offset
	 * @param limit
	 * @return List<Employee>
	 */
	@GetMapping(value = "/custom/{name}/{offset}/{limit}")
	@SwaggerToken
	@ApiOperation(value = "Fetch employees by accountName based on querry", notes = "Returns 200/204 Response", httpMethod = javax.ws.rs.HttpMethod.GET)
	public List<Employee> getEmployeeByCustomName(
			@PathVariable("name") String accountName,
			@PathVariable("offset") Integer offset,
			@PathVariable("limit") Integer limit) {
		List<Employee> employee = handler.getEmpByNameQuery(accountName,
				offset, limit);
		return employee;
	}

	/**
	 * Fetch id and employId based on querry
	 * 
	 * @param accountName
	 * @param offset
	 * @param limit
	 * @return List<Model>
	 */
	@GetMapping(value = "/id/{name}/{offset}/{limit}")
	@SwaggerToken
	@ApiOperation(value = "Fetch id and employId based on querry", notes = "Returns 200/204 Response", httpMethod = javax.ws.rs.HttpMethod.GET)
	public List<Model> getEmployeeById(
			@PathVariable("name") String accountName,
			@PathVariable("offset") Integer offset,
			@PathVariable("limit") Integer limit) {
		List<Model> employee = handler.getEmpByIdQuery(accountName, offset,
				limit);
		return employee;
	}
}
