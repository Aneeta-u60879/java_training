package com.eleena.Employee.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.eleena.Employee.bo.EmployeeMngmntBo;
import com.eleena.Employee.bo.UpdateBonus;
import com.eleena.Employee.bo.UpdateExperience;
import com.eleena.Employee.dto.EmployeeMngmntDto;
import com.eleena.Employee.dto.UpdateBonusDto;
import com.eleena.Employee.dto.UpdateExperienceDto;
import com.eleena.Employee.handler.impl.EmployeeMgmntHandlerImpl;
import com.eleena.Employee.model.EmployeeMngmnt;
import com.eleena.Employee.model.EmployeeQueryModel;



@RestController
public class EmployeeMngmntController {
	@Autowired
	EmployeeMgmntHandlerImpl handler;

	@GetMapping(value = "/id/{id}")
	@SwaggerToken
	@ApiOperation(value = "Fetch by Id", notes = "Returns 200/204 Response", httpMethod = javax.ws.rs.HttpMethod.GET)
	public Mono<EmployeeMngmnt> getById(@ApiParam @PathVariable("id") String id) {
		Mono<EmployeeMngmnt> empList = handler.findById(id);
		return empList;

	}

	@PostMapping(value = "/save")
	public EmployeeMngmnt saveEmployee(@RequestBody EmployeeMngmnt employee) {
		EmployeeMngmnt newEmployee = handler.saveEmploy(employee);
		return newEmployee;
	}

	@GetMapping(value = "/ByAccount/{accountName}")
	public Flux<EmployeeMngmnt> dltById(
			@PathVariable("accountName") String accountName) {
		Flux<EmployeeMngmnt> empList = handler.findByAccount(accountName);
		return empList;

	}

	@GetMapping(value = "/all")
//	@SwaggerToken
//	@ApiOperation(value = "Fetch all employees", notes = "Returns 200/204 Response", httpMethod = javax.ws.rs.HttpMethod.GET)
	public Flux<EmployeeMngmnt> getEmployeeList() {
		Flux<EmployeeMngmnt> empList = handler.getEmployeeList();
		return empList;
	}

	@PostMapping(value = "/addemployee")
	public ResponseEntity<?> addEmployee(
			@RequestBody EmployeeMngmntDto newEmployeeDto) {
		ResponseEntity<?> response = null;
		EmployeeMngmntBo employeeBO = new EmployeeMngmntBo();
		BeanUtils.copyProperties(newEmployeeDto, employeeBO);
		EmployeeMngmntBo responseBO = handler.addEmployee(employeeBO);
		EmployeeMngmntDto responseDTO = new EmployeeMngmntDto();
		BeanUtils.copyProperties(responseBO, responseDTO);
		response = new ResponseEntity<EmployeeMngmntDto>(responseDTO,
				HttpStatus.OK);
		return response;
	}

	@DeleteMapping(value = "/delete/{id}")
	public Mono<EmployeeMngmnt> deleteEmployee(@PathVariable("id") String id) {
		Mono<EmployeeMngmnt> newEmployeeList = handler.dltEmploy(id);
		return newEmployeeList;
	}

	@PutMapping(value = "/update")
	public ResponseEntity<?> updateExperience(
			@RequestBody UpdateExperienceDto updateExperienceDto) {

		ResponseEntity<?> response = null;
		UpdateExperience updateExperienceBo = new UpdateExperience();
		BeanUtils.copyProperties(updateExperienceDto, updateExperienceBo);

		EmployeeMngmntBo responseBO = handler
				.updateExperience(updateExperienceBo);
		EmployeeMngmntDto responseDTO = new EmployeeMngmntDto();
		BeanUtils.copyProperties(responseBO, responseDTO);
		response = new ResponseEntity<EmployeeMngmntDto>(responseDTO,
				HttpStatus.OK);
		return response;

	}

	@PatchMapping(value = "/updatebonus")
	public ResponseEntity<?> updateBonus(
			@RequestBody UpdateBonusDto updateBonusDto) {
		ResponseEntity<?> response = null;
		UpdateBonus updatBonusBo = new UpdateBonus();
		BeanUtils.copyProperties(updateBonusDto, updatBonusBo);
		EmployeeMngmntBo responseBO = handler.updateBonus(updatBonusBo);
		EmployeeMngmntDto responseDTO = new EmployeeMngmntDto();
		BeanUtils.copyProperties(responseBO, responseDTO);
		response = new ResponseEntity<EmployeeMngmntDto>(responseDTO,
				HttpStatus.OK);
		return response;

	}
//	 @PutMapping(value = "/allowanceEmply")
//		 public ResponseEntity<?> emplyAllowance(
//		 @RequestParam(value = "emplySalary") int bonus) {
//		 ResponseEntity<?> response = null;
//		 List<EmployeeMngmntBo> responseBO = handler.emplyAllowance(bonus);
//		 EmployeeMngmntDto responseDTO = new EmployeeMngmntDto();
//			BeanUtils.copyProperties(responseBO, responseDTO);
//			response = new ResponseEntity<EmployeeMngmntDto>(responseDTO,
//					HttpStatus.OK);
//			return response;
//		
//		 }
	 @PutMapping(value = "/allowanceEmply/{bonus}")
	 public ResponseEntity<?> emplyAllowance(
			 @PathVariable("bonus") int bonus) {
	 ResponseEntity<?> response = null;
	 List<EmployeeMngmntBo> responseBO = handler.emplyAllowance(bonus);
	 EmployeeMngmntDto responseDTO = new EmployeeMngmntDto();
		BeanUtils.copyProperties(responseBO, responseDTO);
		response = new ResponseEntity<EmployeeMngmntDto>(responseDTO,
				HttpStatus.OK);
		return response;
	
	 }
	 @GetMapping(value = "/custom/{name}/{offset}/{limit}")
		public List<EmployeeMngmnt> getEmployeeByCustomName(
				@PathVariable("name") String name,
				@PathVariable("offset") Integer offset,
				@PathVariable("limit") Integer limit) {
			List<EmployeeMngmnt> employee = handler
					.getEmpByNameQuery(name, offset, limit);
			return employee;
		}
	 @GetMapping(value = "/custom/{account}")
		public List<EmployeeQueryModel> getEmployeeName(
				@PathVariable("account") String account) {
			List<EmployeeQueryModel> employee = handler
					.getEmpByNameQury(account);
			return employee;
		}

	// we need to pas dto Object to the user
	// @GetMapping(value = "/getbyBand")
	// public ResponseEntity<List<EmployeeMngmntDto>> fetchByBand(
	// @RequestParam(value = "emplyBand") String emplyBnd) {
	// ResponseEntity<List<EmployeeMngmntDto>> response = null;
	// List<EmployeeMngmntBo> emplyListBoList = handler
	// .fetchEmplyByBand(emplyBnd);
	//
	// if (!CollectionUtils.isEmpty(emplyListBoList)) {
	// List<EmployeeMngmntDto> emplyListDtoList = new
	// ArrayList<EmployeeMngmntDto>();
	//
	// emplyListBoList.forEach(eachEmpBo -> {
	// EmployeeMngmntDto eachEmpDto = new EmployeeMngmntDto();
	// BeanUtils.copyProperties(eachEmpBo, eachEmpDto);
	// emplyListDtoList.add(eachEmpDto);
	//
	// });
	// response = ResponseEntity.ok(emplyListDtoList);
	// } else {
	// // response = ResponseEntity.noContent().build();
	// throw new EmployeeMgmntException("No such employee");
	// }
	// return response;
	// }
	//
	// @GetMapping(value = "/removeById")
	// public ResponseEntity<List<EmployeeMngmntDto>> removeByname(
	// @RequestParam(value = "employeeId") Integer emplyId) {
	// ResponseEntity<List<EmployeeMngmntDto>> response = null;
	// List<EmployeeMngmntBo> removedList = handler.removeEmployee(emplyId);
	// if (!CollectionUtils.isEmpty(removedList)) {
	//
	// List<EmployeeMngmntDto> emplyListDtoList = new
	// ArrayList<EmployeeMngmntDto>();
	//
	// removedList.forEach(eachEmpBo -> {
	// EmployeeMngmntDto eachEmpDto = new EmployeeMngmntDto();
	// BeanUtils.copyProperties(eachEmpBo, eachEmpDto);
	// emplyListDtoList.add(eachEmpDto);
	// });
	// response = ResponseEntity.ok(emplyListDtoList);
	// } else {
	// response = ResponseEntity.status(409).build();
	// }
	// return response;
	// }
	//
	// @PostMapping(value = "/addEmploy")
	// public ResponseEntity<List<EmployeeMngmntBo>> addEmployee(
	// @RequestBody EmployeeMngmntDto employeeMngmntDto) {
	// ResponseEntity<List<EmployeeMngmntBo>> response = null;
	// // get user response through dto and filter to the needed and move to bo
	//
	// EmployeeMngmntBo employeemngmntBo = new EmployeeMngmntBo();
	// BeanUtils.copyProperties(employeeMngmntDto, employeemngmntBo);
	//
	// List<EmployeeMngmntBo> emplyList;
	// // try {
	// // emplyList = handler.addEmployee(employeemngmntBo);
	// // List<EmployeeMngmntDto> emplyListDtoList = new
	// ArrayList<EmployeeMngmntDto>();
	// //
	// // emplyList.forEach(eachEmpBo -> {
	// // EmployeeMngmntDto eachEmpDto = new EmployeeMngmntDto();
	// // BeanUtils.copyProperties(eachEmpBo, eachEmpDto);
	// // emplyListDtoList.add(eachEmpDto);
	// // });
	// // response = ResponseEntity.ok(emplyListDtoList);
	// // } catch (EmployeeMgmntException e) {
	// // // TODO Auto-generated catch block
	// // response = ResponseEntity.status(409).build();
	// // e.printStackTrace();
	// // }
	// emplyList = handler.addEmployee(employeemngmntBo);
	// List<EmployeeMngmntDto> emplyListDtoList = new
	// ArrayList<EmployeeMngmntDto>();
	// if(!CollectionUtils.isEmpty(emplyList)){
	// response = ResponseEntity.ok(emplyList);
	// }
	// else{
	// response = ResponseEntity.status(409).build();
	// }
	// return response;
	//
	// }
	//
	// @PostMapping(value = "/addFreshers")
	// public ResponseEntity<List<EmployeeMngmntDto>> addFreshers(
	// @RequestBody List<EmployeeMngmntDto> employeeMngmntDto) {
	// ResponseEntity<List<EmployeeMngmntDto>> response = null;
	// List<EmployeeMngmntBo> employeeMngmntListBo = new
	// ArrayList<EmployeeMngmntBo>();
	// BeanUtils.copyProperties(employeeMngmntDto, employeeMngmntListBo);
	// List<EmployeeMngmntBo> fresherList = handler
	// .addFreshers(employeeMngmntListBo);
	// if (!CollectionUtils.isEmpty(fresherList)) {
	// List<EmployeeMngmntDto> emplyListDtoList = new
	// ArrayList<EmployeeMngmntDto>();
	//
	// fresherList.forEach(eachEmpBo -> {
	// EmployeeMngmntDto eachEmpDto = new EmployeeMngmntDto();
	// BeanUtils.copyProperties(eachEmpBo, eachEmpDto);
	// emplyListDtoList.add(eachEmpDto);
	// });
	// response = ResponseEntity.ok(emplyListDtoList);
	// } else {
	// response = ResponseEntity.status(409).build();
	// }
	// return response;
	//
	// }
	//
	// @PatchMapping(value = "/dltEmply")
	// public ResponseEntity<List<EmployeeMngmntBo>> deltEmply(
	// @RequestBody DltByAccntDto dltModelDto) {
	// ResponseEntity<List<EmployeeMngmntBo>> response = null;
	// DltByAccnt dltModelBo = new DltByAccnt();
	// BeanUtils.copyProperties(dltModelDto, dltModelBo);
	// List<EmployeeMngmntBo> dltdEmplyList;
	// // try {
	// // dltdEmplyList = handler.deltAllEmply(dltModelBo);
	// // List<EmployeeMngmntDto> emplyListDtoList = new
	// ArrayList<EmployeeMngmntDto>();
	// //
	// // dltdEmplyList.forEach(eachEmpBo -> {
	// // EmployeeMngmntDto eachEmpDto = new EmployeeMngmntDto();
	// // BeanUtils.copyProperties(eachEmpBo, eachEmpDto);
	// // emplyListDtoList.add(eachEmpDto);
	// // });
	// //
	// // response = ResponseEntity.ok(emplyListDtoList);
	// // } catch (EmployeeMgmntException e) {
	// // // TODO Auto-generated catch block
	// // response = ResponseEntity.status(409).build();
	// // e.printStackTrace();
	// // }
	// dltdEmplyList = handler.deltAllEmply(dltModelBo);
	// List<EmployeeMngmntDto> emplyListDtoList = new
	// ArrayList<EmployeeMngmntDto>();
	// if(!CollectionUtils.isEmpty(dltdEmplyList)){
	// response = ResponseEntity.ok(dltdEmplyList);
	// }
	// else{
	// response = ResponseEntity.status(409).build();
	// }
	// return response;
	//
	// }
	//
	// // patch and put are almost same they are used for updationg the current
	// // list .patch is used especially for updating specifically)
	// @PutMapping(value = "/updateEmply")
	// public ResponseEntity<List<EmployeeMngmntDto>> updateEmply(
	// @RequestBody UpdateExperienceDto updateExpDto) {
	// ResponseEntity<List<EmployeeMngmntDto>> response = null;
	// UpdateExperience updateExpBo = new UpdateExperience();
	// BeanUtils.copyProperties(updateExpDto, updateExpBo);
	// List<EmployeeMngmntBo> updatedEmplList = handler
	// .updateExperience(updateExpBo);
	// if (!CollectionUtils.isEmpty(updatedEmplList)) {
	// List<EmployeeMngmntDto> emplyListDtoList = new
	// ArrayList<EmployeeMngmntDto>();
	//
	// updatedEmplList.forEach(eachEmpBo -> {
	// EmployeeMngmntDto eachEmpDto = new EmployeeMngmntDto();
	// BeanUtils.copyProperties(eachEmpBo, eachEmpDto);
	// emplyListDtoList.add(eachEmpDto);
	// });
	// response = ResponseEntity.ok(emplyListDtoList);
	// } else {
	// response = ResponseEntity.status(409).build();
	// }
	// return response;
	//
	// }
	//
	// @PutMapping(value = "/allowanceEmply")
	// public ResponseEntity<List<EmployeeMngmntDto>> emplyAllowance(
	// @RequestParam(value = "emplySalary") int bonus) {
	// ResponseEntity<List<EmployeeMngmntDto>> response = null;
	// List<EmployeeMngmntBo> emplyAllowanceList = handler
	// .emplyAllowance(bonus);
	// if (!CollectionUtils.isEmpty(emplyAllowanceList)) {
	// List<EmployeeMngmntDto> emplyListDtoList = new
	// ArrayList<EmployeeMngmntDto>();
	//
	// emplyAllowanceList.forEach(eachEmpBo -> {
	// EmployeeMngmntDto eachEmpDto = new EmployeeMngmntDto();
	// BeanUtils.copyProperties(eachEmpBo, eachEmpDto);
	// emplyListDtoList.add(eachEmpDto);
	// });
	// response = ResponseEntity.ok(emplyListDtoList);
	// } else {
	// response = ResponseEntity.status(409).build();
	// }
	// return response;
	//
	// }
	//
	// @PatchMapping(value = "/updatebonus")
	// public ResponseEntity<List<EmployeeMngmntDto>> updateBonus(@RequestBody
	// UpdateBonusDto updateBonusDto) {
	// ResponseEntity<List<EmployeeMngmntDto>> response = null;
	// UpdateBonus updatBonusBo = new UpdateBonus();
	// BeanUtils.copyProperties(updateBonusDto, updatBonusBo);
	// List<EmployeeMngmntBo> newList = handler.updateBonus(updatBonusBo);
	// if (!CollectionUtils.isEmpty(newList)) {
	// // if(!newList.isEmpty()){
	// List<EmployeeMngmntDto> emplyListDtoList = new
	// ArrayList<EmployeeMngmntDto>();
	//
	// newList.forEach(eachEmpBo -> {
	// EmployeeMngmntDto eachEmpDto = new EmployeeMngmntDto();
	// BeanUtils.copyProperties(eachEmpBo, eachEmpDto);
	// emplyListDtoList.add(eachEmpDto);
	// });
	// response = ResponseEntity.ok(emplyListDtoList);
	// } else {
	// response = ResponseEntity.noContent().build();
	// }
	//
	// return response;
	// }
	//
	// @GetMapping(value = "/getemployes")
	// public List<EmployeeMngmntDto> getdata() {
	// List<EmployeeMngmntBo> emplyBoList= new ArrayList<EmployeeMngmntBo>();
	// emplyBoList= handler.getAllEmployee();
	// List<EmployeeMngmntDto> emplyListDtoList = new
	// ArrayList<EmployeeMngmntDto>();
	// emplyBoList.forEach(eachEmpBo -> {
	// EmployeeMngmntDto eachEmpDto = new EmployeeMngmntDto();
	// BeanUtils.copyProperties(eachEmpBo, eachEmpDto);
	// emplyListDtoList.add(eachEmpDto);
	// });
	//
	// return emplyListDtoList;
	// }
	// @GetMapping(value = "/getById/{employeeId}")
	// public ResponseEntity<EmployeeMngmntDto> fetchById(
	// @PathVariable(value = "employeeId") Integer emplyId) {
	// ResponseEntity<EmployeeMngmntDto> response = null;
	// EmployeeMngmntBo eachEmpBo = handler.fetchEmplyById(emplyId);
	// if (eachEmpBo != null) {
	// EmployeeMngmntDto eachEmpDto = new EmployeeMngmntDto();
	// BeanUtils.copyProperties(eachEmpBo, eachEmpDto);
	// response = ResponseEntity.ok(eachEmpDto);
	// } else {
	// response = ResponseEntity.noContent().build();
	// throw new EmployeeMgmntException("No content");
	// }
	// return response;
	// }

}
