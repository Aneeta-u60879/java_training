/*Class Name  : EmployeeController
 *Description : Controller class  of employee 
 *Date of Creation: 28/11/2020
 */
package com.training.ust.employee.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.training.ust.employee.contoller.annotation.SwaggerToken;
import com.training.ust.employee.exception.EmployeeMgmtException;
import com.training.ust.employee.model.Employee;
import com.training.ust.employee.service.EmployeeService;

import io.swagger.annotations.ApiOperation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Controller for the CRED operations
 * @author 87094
 *
 */

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	/**
	 * Method for get all employees from list
	 * @return List<Employee>
	 */
	@GetMapping(value = "/employees")
	@SwaggerToken
	@ApiOperation(value = "Fetch all employees", notes = "Returns 200/204 Response", httpMethod = javax.ws.rs.HttpMethod.GET)
	public ResponseEntity<List<Employee>> getEmployeeList() {
		List<Employee> empList = service.getEmployeeList();

		if (empList.size() > 0) {
			return new ResponseEntity<List<Employee>>(empList, HttpStatus.OK);
		} else
			return new ResponseEntity<List<Employee>>(empList, HttpStatus.NO_CONTENT);
	}

	/**
	 * Method for deleting  employee by band & account
	 * @param accountName
	 * @param band
	 * @return the employ deleted or bad request
	 */
	@SwaggerToken
	@ApiOperation(value = "Delete an employe by band & account ", notes = "Returns 200/400 Response", httpMethod = javax.ws.rs.HttpMethod.DELETE)
	@DeleteMapping(value = "del/{accountName}/{band}")
	
	public ResponseEntity<String> deleteAccount(@PathVariable String accountName, @PathVariable String band) {
		int size = service.getEmployeeList().size();
		Flux<Employee> resp = service.deleteByAccountAndBand(accountName, band);

		if (service.getEmployeeList().size() < size) {
			return new ResponseEntity<String>("Deleted", HttpStatus.OK);
		}

		return new ResponseEntity<String>("Not Deleted", HttpStatus.NOT_FOUND);

	}

	/**
	 * Method for deleting employee by id
	 * @param id
	 * @return the employ deleted or bad request
	 */
	@SwaggerToken
	@ApiOperation(value = "Delete an employe", notes = "Returns 200/400 Response", httpMethod = javax.ws.rs.HttpMethod.DELETE)
	@DeleteMapping(value = "/delete/{id}")
	
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") String id) {
		Mono<Employee> resp = service.deleteEmployee(id);
		System.out.println("block" + resp.block());
		if (resp.block() != null) {
			return new ResponseEntity<String>("DEleted", HttpStatus.OK);
		} else

			return new ResponseEntity<String>("not deleted", HttpStatus.NOT_FOUND);

	}

	/**
	 * Method for adding batch of freshers
	 * @param employeeList
	 * @return The employ list added or status code 409
	 */
	@SwaggerToken
	@ApiOperation(value = "Add freshers", notes = "Returns 200/409 Response", httpMethod = javax.ws.rs.HttpMethod.POST)
	@PostMapping(value = "/saveAll")
	
	public ResponseEntity<List<Employee>> saveEmployeeByBatch(@RequestBody List<Employee> employeeList) {

		int size = service.getEmployeeList().size();

		List<Employee> newEmployee = service.saveBatchEmployee(employeeList);

		if (employeeList.isEmpty()) {
			return new ResponseEntity<List<Employee>>(newEmployee, HttpStatus.NO_CONTENT);
		} else
			return new ResponseEntity<List<Employee>>(newEmployee, HttpStatus.CREATED);

	}

	/**
	 * Method for get employees using account name
	 * @param accountName
	 * @return The list of employ 
	 */
	@SwaggerToken
	@ApiOperation(value = "Fetch employees by account", notes = "Returns 200/204 Response", httpMethod = javax.ws.rs.HttpMethod.GET)
	@GetMapping(value = "/getEmp/{accountName}")
	public ResponseEntity<List<Employee>> getEmpByAccount(@PathVariable((String) "accountName") String accountName) {
		List<Employee> employee = service.getEmpByAccount(accountName);

		if (employee.size() > 0) {
			return new ResponseEntity<List<Employee>>(employee, HttpStatus.OK);
		} else
			return new ResponseEntity<List<Employee>>(employee, HttpStatus.NO_CONTENT);

	}

	/**
	 * Method for add employee
	 * @param employee
	 * @return the employ added
	 */
	@SwaggerToken
	@ApiOperation(value = "Add employ", notes = "Returns 200/204 Response", httpMethod = javax.ws.rs.HttpMethod.POST)
	@PostMapping(value = "/addEmployee")
	public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {

	
		service.saveEmployee(employee);
		
		if (employee.getAccountName() != null) {
			return new ResponseEntity<String>("Added", HttpStatus.CREATED);
		}

		return new ResponseEntity<String>("not Added", HttpStatus.NOT_FOUND);
	}

	/**
	 * Method to update the experience and band of a particular employ
	 * @param employee
	 * @return  updated object of employ or bad request
	 */
	@SwaggerToken
	@ApiOperation(value = "Update experiencs", notes = "Returns 200/400 Response", httpMethod = javax.ws.rs.HttpMethod.PUT)
    @PutMapping(value = "/update")
	public ResponseEntity<String> updateEmployee(@RequestBody Employee employee) {

		try {
			if (service.updateEmployee(employee) != null) {
				return new ResponseEntity<String>("updated", HttpStatus.OK);
			}
		} catch (EmployeeMgmtException e) {

			return new ResponseEntity<String>(" Not updated", HttpStatus.NOT_FOUND);

		}
		return null;
	}
        
	/**
	 * Method for get employee by  name, offset & limit
	 * @param name
	 * @param offset
	 * @param limit
	 * @return list of employees
	 */
	@SwaggerToken
	@ApiOperation(value = "get Employee By CustomName", notes = "Returns 200/204 Response", httpMethod = javax.ws.rs.HttpMethod.GET)
	@GetMapping(value = "/custom/{name}/{offset}/{limit}")
	
	public List<Employee> getEmployeeByCustomName(@PathVariable String name, @PathVariable Integer offset,
			@PathVariable Integer limit) {
		List<Employee> employee = service.getEmpByNameQuery(name, offset, limit);
		return employee;
	}

	/**
	 * Method for update bonus
	 * @param employee
	 * @return updated employ or bad request
	 */
	@SwaggerToken
	@ApiOperation(value = "Update bonus", notes = "Returns 200/400 Response", httpMethod = javax.ws.rs.HttpMethod.PUT)
	@PutMapping(value = "/addBonus")
	
	public ResponseEntity<String> addBonus(@RequestBody Employee employee) {
		try {
			if (service.addBonus(employee) != null) {

				return new ResponseEntity<String>("Bonus added", HttpStatus.OK);
			}
		} catch (EmployeeMgmtException e) {

			return new ResponseEntity<String>("Bonus not added", HttpStatus.NOT_FOUND);

		}
		return null;

	}

}
