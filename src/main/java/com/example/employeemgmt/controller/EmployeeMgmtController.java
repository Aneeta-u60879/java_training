package com.example.employeemgmt.controller;

import static com.example.employeemgmt.common.EmployeeMgmtConstants.*;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeemgmt.bo.DeleteEmployeeBO;
import com.example.employeemgmt.bo.EmployeeBO;
import com.example.employeemgmt.bo.EmployeeBonusBO;
import com.example.employeemgmt.bo.EmployeeUpdateBO;
import com.example.employeemgmt.common.EmployeeMgmtConstants;
import com.example.employeemgmt.controller.annotation.SwaggerToken;
import com.example.employeemgmt.dto.DeleteEmployeeDTO;
import com.example.employeemgmt.dto.EmployeeBonusDTO;
import com.example.employeemgmt.dto.EmployeeDTO;
import com.example.employeemgmt.dto.EmployeeUpdateDTO;
import com.example.employeemgmt.exception.EmployeeMgmtException;
import com.example.employeemgmt.handler.EmployeeMgmtHandlerImpl;

/**
 * Controller class for EmployeeMgmt which have all end-points.
 * 
 * @author 144900
 *
 */
@RestController
public class EmployeeMgmtController {

	@Autowired
	EmployeeMgmtHandlerImpl handler;

	/**
	 * End-point for fetching all employees. 
	 * 
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/getlist", method = RequestMethod.GET)
	@SwaggerToken
	@ApiOperation(value = GET_EMPLOYEE_LIST_VALUE, notes = EmployeeMgmtConstants.NOTES_FOR_SWAGGER, httpMethod = javax.ws.rs.HttpMethod.GET)
	public ResponseEntity<?> getEmployeeList() {
		ResponseEntity<?> response = null;
		List<EmployeeBO> employeeListBO = handler.getEmpList();
		if (!CollectionUtils.isEmpty(employeeListBO)) {
			List<EmployeeDTO> employeeListDTO = new ArrayList<EmployeeDTO>();
			employeeListBO.forEach(eachEmp -> {
				EmployeeDTO empDTO = new EmployeeDTO();
				BeanUtils.copyProperties(eachEmp, empDTO);
				employeeListDTO.add(empDTO);
			});
			response = ResponseEntity.ok(employeeListDTO);
		}
		return response;
	}

	/**
	 * End-point for searching an employee using id.
	 * 
	 * @return ResponseEntity
	 */
	@GetMapping(value = "/searchbyid/{employeeId}")
	@SwaggerToken
	@ApiOperation(value = GET_EMPLOYEE_BY_ID, notes = EmployeeMgmtConstants.NOTES_FOR_SWAGGER, httpMethod = javax.ws.rs.HttpMethod.GET)
	public ResponseEntity<?> searchById(
			@PathVariable(value = EMPLOYEE_ID) String employeeId)
			throws EmployeeMgmtException {
		ResponseEntity<EmployeeDTO> response = null;
		EmployeeBO employee = handler.searchById(employeeId);
		if (employee != null) {
			EmployeeDTO empDTO = new EmployeeDTO();
			BeanUtils.copyProperties(employee, empDTO);
			response = ResponseEntity.ok(empDTO);
		}
		return response;
	}

	/**
	 * End-point for searching employees by band.
	 * 
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/searchbyband", method = RequestMethod.GET)
	@SwaggerToken
	@ApiOperation(value = GET_EMPLOYEE_BY_ID, notes = EmployeeMgmtConstants.NOTES_FOR_SWAGGER, httpMethod = javax.ws.rs.HttpMethod.GET)
	public ResponseEntity<?> searchByBand(
			@RequestParam(value = EMPLOYEE_BAND) String band) {
		ResponseEntity<?> response = null;
		List<EmployeeBO> empBandWiseListBO = handler.searchByBand(band);
		if (!empBandWiseListBO.isEmpty()) {
			List<EmployeeDTO> empBandWiseListDTO = new ArrayList<EmployeeDTO>();
			empBandWiseListBO.forEach(eachEmp -> {
				EmployeeDTO empDTO = new EmployeeDTO();
				BeanUtils.copyProperties(eachEmp, empDTO);
				empBandWiseListDTO.add(empDTO);
			});
			response = ResponseEntity.ok(empBandWiseListDTO);
		}
		return response;
	}

	/**
	 * End-point for adding an employee.
	 * 
	 * @return ResponseEntity
	 */
	@PostMapping(value = "/addemployee")
	@SwaggerToken
	@ApiOperation(value = ADD_AN_EMPLOYEE, notes = EmployeeMgmtConstants.SWAGGER_NOTES, httpMethod = javax.ws.rs.HttpMethod.POST)
	public ResponseEntity<?> addEmployee(@RequestBody EmployeeDTO newEmployee) {
		ResponseEntity<?> response = null;
		EmployeeBO employeeBO = new EmployeeBO();
		BeanUtils.copyProperties(newEmployee, employeeBO);
		EmployeeBO responseBO = handler.addEmployee(employeeBO);
		if (responseBO != null) {
			EmployeeDTO responseDTO = new EmployeeDTO();
			BeanUtils.copyProperties(responseBO, responseDTO);
			response = new ResponseEntity<EmployeeDTO>(responseDTO,
					HttpStatus.CREATED);
		}
		return response;
	}

	/**
	 * End-point for adding employees.
	 * 
	 * @return ResponseEntity
	 */
	@PostMapping(value = "/addfreshers")
	@SwaggerToken
	@ApiOperation(value = ADD_EMPLOYEES, notes = EmployeeMgmtConstants.SWAGGER_NOTES, httpMethod = javax.ws.rs.HttpMethod.POST)
	public ResponseEntity<?> addEmployees(
			@RequestBody List<EmployeeDTO> newFreshersList) {
		ResponseEntity<?> response = null;
		List<EmployeeBO> employeeBOList = new ArrayList<EmployeeBO>();
		newFreshersList.forEach(eachEmp -> {
			EmployeeBO employeeBO = new EmployeeBO();
			BeanUtils.copyProperties(eachEmp, employeeBO);
			employeeBOList.add(employeeBO);
		});
		List<EmployeeBO> newFreshersEmpList = handler
				.addFreshers(employeeBOList);
		if (!CollectionUtils.isEmpty(newFreshersEmpList)) {
			List<EmployeeDTO> employeeDTOList = new ArrayList<EmployeeDTO>();
			newFreshersEmpList.forEach(eachEmp -> {
				EmployeeDTO employeeDTO = new EmployeeDTO();
				BeanUtils.copyProperties(eachEmp, employeeDTO);
				employeeDTOList.add(employeeDTO);
			});
			response = new ResponseEntity<List<EmployeeDTO>>(employeeDTOList,
					HttpStatus.CREATED);
		}
		return response;
	}

	/**
	 * End-point for deleting an employee.
	 * 
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@SwaggerToken
	@ApiOperation(value = DELETE_AN_EMPLOYEE_BY_ID, notes = EmployeeMgmtConstants.SWAGGER_NOTES, httpMethod = javax.ws.rs.HttpMethod.DELETE)
	public ResponseEntity<?> deleteEmployee(
			@RequestParam(value = EMPLOYEE_ID) String employeeId) {
		ResponseEntity<?> response = null;
		EmployeeBO responseBO = handler.deleteEmployee(employeeId);
		if (responseBO != null) {
			EmployeeDTO responseDTO = new EmployeeDTO();
			BeanUtils.copyProperties(responseBO, responseDTO);
			response = new ResponseEntity<EmployeeDTO>(responseDTO,
					HttpStatus.OK);
		}
		return response;
	}

	/**
	 * End-point for deleting employees using band.
	 * 
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/deleteByBand", method = RequestMethod.DELETE)
	@SwaggerToken
	@ApiOperation(value = DELETE_EMPLOYEES_BY_BAND, notes = EmployeeMgmtConstants.SWAGGER_NOTES, httpMethod = javax.ws.rs.HttpMethod.DELETE)
	public ResponseEntity<?> deleteEmployeeByBand(
			@RequestBody DeleteEmployeeDTO deleteEmployeeByBand) {
		ResponseEntity<?> response = null;
		DeleteEmployeeBO deleteEmployeeBO = new DeleteEmployeeBO();
		BeanUtils.copyProperties(deleteEmployeeByBand, deleteEmployeeBO);
		List<EmployeeBO> empListBO;
		empListBO = handler.deleteEmployeeByBand(deleteEmployeeBO);
		if (!CollectionUtils.isEmpty(empListBO)) {
			List<EmployeeDTO> empDTOList = new ArrayList<EmployeeDTO>();
			empListBO.forEach(eachemp -> {
				EmployeeDTO employeeDTO = new EmployeeDTO();
				BeanUtils.copyProperties(eachemp, employeeDTO);
				empDTOList.add(employeeDTO);
			});
			response = ResponseEntity.ok(empDTOList);
		}
		return response;
	}

	/**
	 * End-point for updating an employee.
	 * 
	 * @return ResponseEntity
	 */
	@PostMapping(value = "/updateemp")
	@SwaggerToken
	@ApiOperation(value = UPDATE_AN_EMPLOYEE, notes = EmployeeMgmtConstants.SWAGGER_NOTES, httpMethod = javax.ws.rs.HttpMethod.POST)
	public ResponseEntity<?> updateEmployee(
			@RequestBody EmployeeUpdateDTO updateEmp) {
		ResponseEntity<?> response = null;
		EmployeeUpdateBO employeeUpdateBO = new EmployeeUpdateBO();
		BeanUtils.copyProperties(updateEmp, employeeUpdateBO);
		EmployeeBO employeeBO = handler.updateEmployee(employeeUpdateBO);
		if (employeeBO != null) {
			EmployeeDTO employeeDTO = new EmployeeDTO();
			BeanUtils.copyProperties(employeeBO, employeeDTO);
			response = new ResponseEntity<EmployeeDTO>(employeeDTO,
					HttpStatus.OK);
		}
		return response;
	}

	/**
	 * End-point for updating all employees allowance.
	 * 
	 * @return ResponseEntity
	 */
	@PostMapping(value = "/updateAllowance")
	@SwaggerToken
	@ApiOperation(value = ADD_ALLOWANCE_EMPLOYEES, notes = EmployeeMgmtConstants.NOTES_FOR_SWAGGER, httpMethod = javax.ws.rs.HttpMethod.POST)
	public ResponseEntity<?> addAllowance(
			@RequestParam(value = EMPLOYEE_ALLOWANCE) Double allowance) {
		ResponseEntity<?> response = null;
		List<EmployeeBO> employeeList = handler.addAllowance(allowance);
		if (!CollectionUtils.isEmpty(employeeList)) {
			List<EmployeeDTO> empDTOList = new ArrayList<EmployeeDTO>();
			employeeList.forEach(eachemp -> {
				EmployeeDTO employeeDTO = new EmployeeDTO();
				BeanUtils.copyProperties(eachemp, employeeDTO);
				empDTOList.add(employeeDTO);
			});
			response = new ResponseEntity<List<EmployeeDTO>>(empDTOList,
					HttpStatus.OK);
		}
		return response;
	}

	/**
	 * End-point for searching employees using account name.
	 * 
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/searchbyacc", method = RequestMethod.GET)
	@SwaggerToken
	@ApiOperation(value = GET_EMPLOYEES_BY_ACCOUNTNAME, notes = EmployeeMgmtConstants.NOTES_FOR_SWAGGER, httpMethod = javax.ws.rs.HttpMethod.GET)
	public ResponseEntity<?> searchByAcc(
			@RequestParam(value = EMPLOYEE_ACCOUNT_NAME) String accountName) {
		ResponseEntity<?> response = null;
		List<EmployeeBO> empAccWiseList = handler.searchByAcc(accountName);
		if (!empAccWiseList.isEmpty()) {
			List<EmployeeDTO> employeeDTOList = new ArrayList<EmployeeDTO>();
			empAccWiseList.forEach(eachEmp -> {
				EmployeeDTO employeeDTO = new EmployeeDTO();
				BeanUtils.copyProperties(eachEmp, employeeDTO);
				employeeDTOList.add(employeeDTO);
			});
			response = ResponseEntity.ok(employeeDTOList);
		}
		return response;
	}

	/**
	 * End-point for updating bonus of an employee.
	 * 
	 * @return ResponseEntity
	 */
	@PostMapping(value = "/updatebonus")
	@SwaggerToken
	@ApiOperation(value = UPDATE_BONUS_EMPLOYEE, notes = EmployeeMgmtConstants.NOTES_FOR_SWAGGER, httpMethod = javax.ws.rs.HttpMethod.POST)
	public ResponseEntity<?> addBonus(
			@RequestBody EmployeeBonusDTO employeeBonusDTO) {
		ResponseEntity<?> response = null;
		EmployeeBonusBO employeeBonusBO = new EmployeeBonusBO();
		BeanUtils.copyProperties(employeeBonusDTO, employeeBonusBO);
		System.out.println(employeeBonusBO);
		EmployeeBO employeeBO = handler.addBonus(employeeBonusBO);
		if (employeeBO != null) {
			EmployeeDTO employeeDTO = new EmployeeDTO();
			BeanUtils.copyProperties(employeeBO, employeeDTO);
			response = new ResponseEntity<EmployeeDTO>(employeeDTO,
					HttpStatus.OK);
		}
		return response;
	}

	/**
	 * End-point for fetching employees using account name.
	 * 
	 * @return ResponseEntity
	 */
	@GetMapping(value = "/getempbyacc/{name}/{offset}/{limit}")
	@SwaggerToken
	@ApiOperation(value = GET_EMPLOYEES_BY_ACCOUNTNAME, notes = EmployeeMgmtConstants.NOTES_FOR_SWAGGER, httpMethod = javax.ws.rs.HttpMethod.GET)
	public ResponseEntity<?> getEmployeeByCustomName(
			@PathVariable(ACCOUNT_NAME) String name,
			@PathVariable(OFFSET_LIMIT) Integer offset,
			@PathVariable(LIMIT) Integer limit) {
		ResponseEntity<?> response = null;
		List<EmployeeBO> employeeList = handler.getEmpByNameQuery(name, offset,
				limit);
		if (!CollectionUtils.isEmpty(employeeList)) {
			List<EmployeeDTO> employeeDTOList = new ArrayList<EmployeeDTO>();
			employeeList.forEach(eachEmp -> {
				EmployeeDTO employeeDTO = new EmployeeDTO();
				BeanUtils.copyProperties(eachEmp, employeeDTO);
				employeeDTOList.add(employeeDTO);
			});
			response = ResponseEntity.ok(employeeDTOList);
		}
		return response;
	}

	/**
	 * End-point for fetching an employee using employee Id.
	 * 
	 * @return ResponseEntity
	 */
	@GetMapping(value = "/getempbyid/{employeeId}")
	@SwaggerToken
	@ApiOperation(value = GET_EMPLOYEE_BY_ID, notes = EmployeeMgmtConstants.NOTES_FOR_SWAGGER, httpMethod = javax.ws.rs.HttpMethod.GET)
	public ResponseEntity<?> getEmployeeByCusId(
			@PathVariable(EMPLOYEE_ID) String employeeId) {
		ResponseEntity<?> response = null;
		EmployeeBO employeeBO = handler.getEmployeesWithId(employeeId);
		if (employeeBO != null) {
			EmployeeDTO employeeDTO = new EmployeeDTO();
			BeanUtils.copyProperties(employeeBO, employeeDTO);
			response = new ResponseEntity<EmployeeDTO>(employeeDTO,
					HttpStatus.OK);
		}
		return response;
	}

}
