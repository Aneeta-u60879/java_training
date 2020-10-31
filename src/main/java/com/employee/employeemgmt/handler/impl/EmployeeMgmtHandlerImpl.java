package com.employee.employeemgmt.handler.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.employee.employeemgmt.BO.UpadateExperience;
import com.employee.employeemgmt.BO.UpdateBonus;
import com.employee.employeemgmt.DTO.EmployeeMgmtDTO;
import com.employee.employeemgmt.dao.EmployeeDao;
import com.employee.employeemgmt.exception.EmployeeMgmtException;
import com.employee.employeemgmt.handler.IEmployeeMgmtHandler;
import com.employee.employeemgmt.model.ByAccountModel;
import com.employee.employeemgmt.model.EmployModel;
import com.employee.employeemgmt.repo.EmployeeMgmtRepo;

@Service
public class EmployeeMgmtHandlerImpl implements IEmployeeMgmtHandler {

	@Autowired
	EmployeeMgmtRepo  employRepo;
	@Autowired
	EmployeeDao  employDao;

	/* (non-Javadoc)
	 * To get list of Employ
	 * @return List<EmployeeMgmtDTO>
	 * @see com.employee.employeemgmt.handler.IEmployeeMgmtHandler#getEmployeeList()
	 */
	@Override
	public List<EmployeeMgmtDTO> getEmployeeList() {
		// TODO Auto-generated method stub
		Flux<EmployModel> employList = employRepo.findAll();
		List<EmployModel> empList = employList.collect(Collectors.toList()).block();
		List<EmployeeMgmtDTO> finalEmployList = new ArrayList<>();
		empList.forEach(eachEmpl -> {
			EmployeeMgmtDTO empDto = new EmployeeMgmtDTO();
			BeanUtils.copyProperties(eachEmpl, empDto);
			finalEmployList.add(empDto);
		});
		
		return finalEmployList;
	}

	/* (non-Javadoc)
	 * To save employ
	 * @param employ
	 * @return return the employ added
	 * @see com.employee.employeemgmt.handler.IEmployeeMgmtHandler#saveEmploy(com.employee.employeemgmt.model.EmployModel)
	 */
	@Override
	public EmployModel saveEmploy(EmployModel employ) {
		// TODO Auto-generated method stub
		Mono<EmployModel>   emp = employRepo.findById(employ.getEmployeeId());
		if(emp==null){
		Mono<EmployModel> employ1 = employRepo.save(employ);
		return employ1.block();
		}
		else{
			throw new EmployeeMgmtException("Employ already exist");
		}
		
	}
	
	/* (non-Javadoc)
	 * To delete an employ
	 * @param employeeId
	 * @return deleted employ
	 * @see com.employee.employeemgmt.handler.IEmployeeMgmtHandler#deleteEmploye(java.lang.String)
	 */
	@Override
	public EmployModel deleteEmploye(String employeeId){
		Mono<EmployModel>   emp = employRepo.findById(employeeId);
		Mono<EmployModel>  del = emp.flatMap(each->employRepo.delete(each).then(Mono.just(each)));
		return del.block();
	}

	/* (non-Javadoc)
	 * to get employ of an account
	 * @param accountName
	 * @return employ to a particular account
	 * @see com.employee.employeemgmt.handler.IEmployeeMgmtHandler#getEmployeeByAccnt(java.lang.String)
	 */
	@Override
	public EmployModel getEmployeeByAccnt(String accountName) {
		
		// TODO Auto-generated method stub
		Flux<EmployModel> employList = employRepo.findByAccountName(accountName);
		return employList.blockFirst();
	}

	/* (non-Javadoc)
	 * To update experience of an employ
	 * @param employ
	 * @return EmployModel updated
	 * @see com.employee.employeemgmt.handler.IEmployeeMgmtHandler#updateEmploy(com.employee.employeemgmt.BO.UpadateExperience)
	 */
	@Override
	public EmployModel updateEmploy(UpadateExperience employ) {
		// TODO Auto-generated method stub
		Mono<EmployModel>   emp = employRepo.findById(employ.getEmployeeId());
		EmployModel newEmploy = emp.block();
		newEmploy.setExperience(employ.getExperience());
		newEmploy.setBand(employ.getBand());
		Mono<EmployModel> employ1 = employRepo.save(newEmploy);
		return employ1.block();
	}

	/* (non-Javadoc)
	 * To add freshers
	 * @param employ
	 * @return EmployModel freshers
	 * @see com.employee.employeemgmt.handler.IEmployeeMgmtHandler#addFreshers(java.util.List)
	 */
	@Override
	public EmployModel addFreshers(List<EmployModel> employ) {
		// TODO Auto-generated method stub
		Flux<EmployModel> employList = employRepo.saveAll(employ);
		return employList.blockFirst();
	}

	/* (non-Javadoc)
	 * To update bonus of an employ
	 * @param updateBonus
	 * @return
	 * @see com.employee.employeemgmt.handler.IEmployeeMgmtHandler#updateBonus(com.employee.employeemgmt.BO.UpdateBonus)
	 */
	@Override
	public EmployModel updateBonus(UpdateBonus update) {
		// TODO Auto-generated method stub
		Mono<EmployModel>   emp = employRepo.findById(update.getEmployeeId());
		EmployModel newEmploy = emp.block();
		newEmploy.setSalary(newEmploy.getSalary()+update.getBonus());
		Mono<EmployModel> employ = employRepo.save(newEmploy);
		return employ.block();
	}

	/* (non-Javadoc)
	 * Get list of employ of particular employ
	 * @param account 
	 * @return List<ByAccountModel> 
	 * @see com.employee.employeemgmt.handler.IEmployeeMgmtHandler#getByAccnt(java.lang.String)
	 */
	@Override
	public List<ByAccountModel> getByAccnt(String account) {
		// TODO Auto-generated method stub
		return employDao.getEmloyList(account);
	}


	
	


}
