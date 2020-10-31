package com.example.employeemanagement.controller;

import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeemanagement.bo.EmployeeBO;
import com.example.employeemanagement.bo.UpdateEmployeeBO;
import com.example.employeemanagement.dto.EmployeeDTO;
import com.example.employeemanagement.dto.UpdateEmployeeDTO;
import com.example.employeemanagement.handler.impl.EmployeeManagementHandlerImpl;
import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.model.EmployeeNames;

/**
 * Controller for EmployeeManagementClass
 * 
 * @author 144892
 *
 */
@RestController
public class EmployeeManagementController {

	/*
	 * Handler Dependency Injected using @Autowired
	 */
	@Autowired
	EmployeeManagementHandlerImpl empHandler;

	/*
	 * Method to fetch all employees from the list
	 * 
	 * @return List<EmployeeDTO>
	 * 
	 * Swagger Token added
	 */
	@GetMapping(value = "/all")
	@SwaggerToken
	@ApiOperation(value = "Fetch all employees", notes = "Returns 200/204 Response", httpMethod = javax.ws.rs.HttpMethod.GET)
	public List<EmployeeDTO> getEmployeeList() {
		List<EmployeeBO> empList = empHandler.getEmployeeList();
		List<EmployeeDTO> employeeListDTO = new ArrayList<EmployeeDTO>();
		empList.forEach(employee -> {
			EmployeeDTO employeeDTO = new EmployeeDTO();
			BeanUtils.copyProperties(employee, employeeDTO);
			employeeListDTO.add(employeeDTO);
		});

		return employeeListDTO;
	}

	/*
	 * Method to save a new employee into the list
	 * 
	 * @body EmployeeDTO employeeDTO
	 * 
	 * @return EmployeeDTO
	 */
	@PostMapping(value = "/save")
	public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
		EmployeeBO employeeBO = new EmployeeBO();
		BeanUtils.copyProperties(employeeDTO, employeeBO);
		EmployeeBO newEmployee = empHandler.saveEmployee(employeeBO);
		EmployeeDTO employeeDTO1 = new EmployeeDTO();
		BeanUtils.copyProperties(newEmployee, employeeDTO1);
		return employeeDTO1;
	}

	/*
	 * Method to delete an employee using specific employee id from the list
	 * 
	 * @body EmployeeDTO employeeDTO
	 * 
	 * @return EmployeeDTO
	 */
	@DeleteMapping(value = "/deleteemp/{empId}")
	public EmployeeDTO deleteEmployee(
			@PathVariable(value = "empId") String empId) {
		EmployeeBO deletedEmployee = empHandler.deleteEmployee(empId);
		EmployeeDTO employeeDTO1 = new EmployeeDTO();
		BeanUtils.copyProperties(deletedEmployee, employeeDTO1);
		return employeeDTO1;
	}

	/*
	 * Method to update an employee using specific employee id in the list
	 * 
	 * @body EmployeeDTO employeeDTO
	 * 
	 * @return EmployeeDTO
	 */
	@PutMapping(value = "/update")
	public EmployeeDTO updatedEmployee(@RequestBody EmployeeDTO employeeDTO) {
		EmployeeBO employee = new EmployeeBO();
		BeanUtils.copyProperties(employeeDTO, employee);
		EmployeeBO updatedEmployee = empHandler.updateEmployee(employee);
		EmployeeDTO employeeDTO1 = new EmployeeDTO();
		BeanUtils.copyProperties(updatedEmployee, employeeDTO1);
		return employeeDTO1;
	}

	/*
	 * Method to fetch all employees under a specific band from the list
	 * 
	 * @param String band
	 * 
	 * @return List<EmployeeDTO>
	 */
	@GetMapping(value = "/getbandemp")
	public List<EmployeeDTO> getBandEmployeeList(
			@RequestParam(value = "band") String band) {
		List<EmployeeBO> empList = empHandler.getEmployeesFromBand(band);
		List<EmployeeDTO> employeeListDTO = new ArrayList<EmployeeDTO>();
		empList.forEach(employee -> {
			EmployeeDTO employeeDTO = new EmployeeDTO();
			BeanUtils.copyProperties(employee, employeeDTO);
			employeeListDTO.add(employeeDTO);
		});
		return employeeListDTO;
	}

	/*
	 * Method to fetch all employees under a specific account from the list
	 * 
	 * @param String accName
	 * 
	 * @return List<EmployeeDTO>
	 */
	@GetMapping(value = "/getaccemp")
	public List<EmployeeDTO> getAccEmployeeList(
			@RequestParam(value = "accName") String accName) {
		List<EmployeeBO> empList = empHandler.getByAcc(accName);
		List<EmployeeDTO> employeeListDTO = new ArrayList<EmployeeDTO>();
		empList.forEach(employee -> {
			EmployeeDTO employeeDTO = new EmployeeDTO();
			BeanUtils.copyProperties(employee, employeeDTO);
			employeeListDTO.add(employeeDTO);
		});
		return employeeListDTO;
	}

	/*
	 * Method to delete employees under a specific band under a specific account
	 * from the list
	 * 
	 * @param String band
	 * 
	 * @return List<EmployeeDTO>
	 */
	@DeleteMapping(value = "/deleteaccemp/{accName}/{band}")
	public List<EmployeeDTO> deleteByAcc(
			@PathVariable(value = "accName") String accName,
			@PathVariable(value = "band") String band) {
		List<EmployeeBO> deletedEmployeeList = empHandler.deleteByAcc(accName,
				band);
		List<EmployeeDTO> employeeListDTO = new ArrayList<EmployeeDTO>();
		deletedEmployeeList.forEach(employee -> {
			EmployeeDTO employeeDTO = new EmployeeDTO();
			BeanUtils.copyProperties(employee, employeeDTO);
			employeeListDTO.add(employeeDTO);
		});
		return employeeListDTO;
	}

	/*
	 * Method to add bonus to a specific employee in the list
	 * 
	 * @body UpdateEmployeeDTO employeeDTO
	 * 
	 * @return EmployeeDTO
	 */
	@PutMapping(value = "/addbonus")
	public EmployeeDTO addBonus(@RequestBody UpdateEmployeeDTO employeeDTO) {
		UpdateEmployeeBO employee = new UpdateEmployeeBO();
		BeanUtils.copyProperties(employeeDTO, employee);
		EmployeeBO updatedEmployee = empHandler.addBonus(employee);
		EmployeeDTO employeeDTO1 = new EmployeeDTO();
		BeanUtils.copyProperties(updatedEmployee, employeeDTO1);
		return employeeDTO1;
	}

	/*
	 * Method to add a specific % of allowance to all the employee's salary in
	 * the list
	 * 
	 * @param Double allowance
	 * 
	 * @return List<EmployeeDTO>
	 */
	@PutMapping(value = "/addallowance/{allowance}")
	public List<EmployeeDTO> addAllowance(
			@PathVariable(value = "allowance") Double allowance) {
		List<EmployeeBO> addedAllowanceList = empHandler
				.addAllowance(allowance);
		List<EmployeeDTO> addedAllowanceListDTO = new ArrayList<EmployeeDTO>();
		addedAllowanceList.forEach(employee -> {
			EmployeeDTO employeeDTO = new EmployeeDTO();
			BeanUtils.copyProperties(employee, employeeDTO);
			addedAllowanceListDTO.add(employeeDTO);
		});
		return addedAllowanceListDTO;
	}

	/*
	 * Method to add list of freshers to the list
	 * 
	 * @body List<EmployeeDTO> newEmployeeListDTO
	 * 
	 * @return List<EmployeeDTO>
	 */
	@PostMapping(value = "/addemployeelist")
	public List<EmployeeDTO> addEmployeeList(
			@RequestBody List<EmployeeDTO> newEmployeeListDTO) {
		List<EmployeeBO> newEmployeeListBO = new ArrayList<EmployeeBO>();
		newEmployeeListDTO.forEach(employee -> {
			EmployeeBO employeeBO = new EmployeeBO();
			BeanUtils.copyProperties(employee, employeeBO);
			newEmployeeListBO.add(employeeBO);
		});
		List<EmployeeBO> employeAddedList = empHandler
				.addNewEmployees(newEmployeeListBO);
		List<EmployeeDTO> employeAddedListDTO = new ArrayList<EmployeeDTO>();
		employeAddedList.forEach(employee -> {
			EmployeeDTO employeeDTO = new EmployeeDTO();
			BeanUtils.copyProperties(employee, employeeDTO);
			employeAddedListDTO.add(employeeDTO);
		});
		return employeAddedListDTO;
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
	@GetMapping(value = "/customacc/{account}/{offset}/{limit}")
	public List<Employee> getEmployeeByAccName(
			@PathVariable("account") String account,
			@PathVariable("offset") Integer offset,
			@PathVariable("limit") Integer limit) {
		List<Employee> employee = empHandler.getEmpByNameQuery(account, offset,
				limit);
		return employee;
	}

	/*
	 * Custom Query Method to fetch employee id, name and joiningDate from a
	 * specific band from the list
	 * 
	 * @param String band
	 * 
	 * @return List<EmployeeNames>
	 */
	@GetMapping(value = "/customband/{band}")
	public List<EmployeeNames> getEmployeeByBand(
			@PathVariable("band") String band) {
		List<EmployeeNames> employee = empHandler.getEmpByNameByBand(band);
		return employee;
	}
}
