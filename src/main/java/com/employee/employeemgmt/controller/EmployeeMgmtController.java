package com.employee.employeemgmt.controller;

import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

import com.employee.employeemgmt.BO.UpadateExperience;
import com.employee.employeemgmt.BO.UpdateBonus;
import com.employee.employeemgmt.DTO.EmployeeMgmtDTO;
import com.employee.employeemgmt.DTO.UpdateBonusDTO;
import com.employee.employeemgmt.DTO.UpdateExperienceDTO;
import com.employee.employeemgmt.controller.annotation.SwaggerToken;
import com.employee.employeemgmt.exception.EmployeeMgmtException;
import com.employee.employeemgmt.handler.impl.EmployeeMgmtHandlerImpl;
import com.employee.employeemgmt.model.ByAccountModel;
import com.employee.employeemgmt.model.EmployModel;

/**
 * Controller for the CRED operations
 * @author 144895
 *
 */
@RestController
public class EmployeeMgmtController {
	
	@Autowired
	EmployeeMgmtHandlerImpl emplHandler;
	
	
	
	/**
	 * Get the list of all employ in the table
	 * @return List<EmployeeMgmtDTO>
	 */
	@GetMapping(value = "/getemployes")
	@SwaggerToken
	@ApiOperation(value = "Fetch all employees", notes = "Returns 200/204 Response", httpMethod = javax.ws.rs.HttpMethod.GET)
	public List<EmployeeMgmtDTO> getdata(){		
		List<EmployeeMgmtDTO> finalEmployList = emplHandler.getEmployeeList();
		return finalEmployList;
	}
	
	/**
	 * Get the list of employees of a particular account
	 * @param accountName
	 * @return The list of employ or throw exception 
	 * @throws EmployeeMgmtException
	 */
	@SwaggerToken
	@ApiOperation(value = "Fetch employees by account", notes = "Returns 200/204 Response", httpMethod = javax.ws.rs.HttpMethod.GET)
	@GetMapping(value = "/byaccount/{accountName}")	
	public ResponseEntity<?> getByAccount
			(@PathVariable("accountName") String accountName) throws EmployeeMgmtException {
		EmployModel byAccount = emplHandler.getEmployeeByAccnt(accountName);
		
		if(byAccount != null){		
			EmployeeMgmtDTO empDtoByAcc = new EmployeeMgmtDTO();
			BeanUtils.copyProperties(byAccount, empDtoByAcc);
			return ResponseEntity.ok(empDtoByAcc);
		} else{
			// TODO Auto-generated catch block
			throw new EmployeeMgmtException("No such account");
		}
		
	}
	
	/**
	 * To add an employ
	 * @param employee
	 * @return the employ added 
	 * @throws EmployeeMgmtException
	 */
	@SwaggerToken
	@ApiOperation(value = "Add employ", notes = "Returns 200/204 Response", httpMethod = javax.ws.rs.HttpMethod.POST)
	@PostMapping(value = "/addEmploy")
	public ResponseEntity<?> addEmployee
	(@RequestBody EmployeeMgmtDTO employee) throws EmployeeMgmtException{		
		EmployModel employ = new EmployModel();
		BeanUtils.copyProperties(employee, employ);
		ResponseEntity<?> response = null;
		EmployModel newEmploy = emplHandler.saveEmploy(employ);		
		if(newEmploy!=null){
			EmployeeMgmtDTO addedEmploy = new EmployeeMgmtDTO();
			BeanUtils.copyProperties(newEmploy, addedEmploy);
			response = ResponseEntity.ok(addedEmploy);
		}
		return response;
		
	}
	
	/**
	 * To add freshers
	 * @param employ
	 * @return The employ list added or status code 409
	 */
	@SwaggerToken
	@ApiOperation(value = "Add freshers", notes = "Returns 200/409 Response", httpMethod = javax.ws.rs.HttpMethod.POST)
	@PostMapping(value = "/addFreshers")
	public ResponseEntity<?> addFreshers
	(@RequestBody List<EmployModel> employ){
		ResponseEntity<?> response = null;
		EmployModel employList = emplHandler.addFreshers(employ);
		if(employList != null){
			response = ResponseEntity.ok(employList);
		}
		else{
			response = ResponseEntity.status(409).build();
		}
		return response;
		
	}
	
	/**
	 * To delete a particular employee
	 * @param employeeId
	 * @return the employ deleted or bad request
	 */
	@SwaggerToken
	@ApiOperation(value = "Delete an employe", notes = "Returns 200/400 Response", httpMethod = javax.ws.rs.HttpMethod.DELETE)
	@DeleteMapping(value ="/delete/{employeeId}")
	public ResponseEntity<?> deleteEmploy(
			@PathVariable("employeeId") String employeeId){
		ResponseEntity<?> response = null;
		EmployModel deletedEmploy = emplHandler.deleteEmploye(employeeId);
		if(deletedEmploy != null){
			response = ResponseEntity.ok(deletedEmploy);
		}
		else{
			response = ResponseEntity.status(400).body("There is no such user");
		}
		
		return response;
		
	}

	/**
	 * To update the experience and band of a particular employ
	 * @param updateExp
	 * @return updated object of employ or bad request
	 */
	@SwaggerToken
	@ApiOperation(value = "Update experiiencs", notes = "Returns 200/400 Response", httpMethod = javax.ws.rs.HttpMethod.PUT)
	@PutMapping(value = "/updateexpr")
	public ResponseEntity<?> updateExperience(
			@RequestBody UpdateExperienceDTO updateExp ){
		ResponseEntity<?> response = null;
		UpadateExperience update = new UpadateExperience();
		BeanUtils.copyProperties(updateExp, update);
		EmployModel updatedEmploy = emplHandler.updateEmploy(update);
		if(updatedEmploy != null){
			response = ResponseEntity.ok(updatedEmploy);
		}
		else{
			response = ResponseEntity.status(400).body("There is no such user");
		}
		
		return response;
		
	} 
	
	/**
	 * To update the bonus of particular country
	 * @param updateBonus
	 * @return updated employ or bad request
	 */
	@SwaggerToken
	@ApiOperation(value = "Update bonus", notes = "Returns 200/400 Response", httpMethod = javax.ws.rs.HttpMethod.PUT)
	@PutMapping(value = "/updatebonus")
	public ResponseEntity<?> updateBonus(
			@RequestBody UpdateBonusDTO updateBonus ){
		ResponseEntity<?> response = null;
		UpdateBonus update = new UpdateBonus();
		BeanUtils.copyProperties(updateBonus, update);
		EmployModel updateList = emplHandler.updateBonus(update);
		if(updateList != null){
			response = ResponseEntity.ok(updateList);
		}
		else{
			response = ResponseEntity.badRequest().body("There is no such user");;
		}
		
		return response;
		
	} 
	
	/**
	 * To get list of employ using custom query or bad request
	 * @param name
	 * @return list of employe
	 */
	@SwaggerToken
	@ApiOperation(value = "Fetch all employees by limit ", notes = "Returns 200/400 Response", httpMethod = javax.ws.rs.HttpMethod.GET)
	@GetMapping(value = "/accntByLimit/{name}")
	public ResponseEntity<?> getByAccntLimt(@PathVariable("name") String name){
		ResponseEntity<?> response = null;
		List<ByAccountModel> employee = emplHandler
				.getByAccnt(name);
		if(!CollectionUtils.isEmpty(employee)){
			response = ResponseEntity.ok(employee);
		}
		else{
			response = ResponseEntity.badRequest().body("There is no such account");;
		}
		return response;
		
	}
	
}
