package com.eleena.Employee.handler;



import java.util.List;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.eleena.Employee.bo.EmployeeMngmntBo;
import com.eleena.Employee.bo.UpdateBonus;
import com.eleena.Employee.bo.UpdateExperience;
import com.eleena.Employee.exception.EmployeeMgmntException;
import com.eleena.Employee.model.EmployeeMngmnt;





/**
 * @author 144784
 *
 */
public interface IEmployeeMgmntHandler {
//public EmployeeMngmntBo fetchEmplyById(Integer employeeId);
//public List<EmployeeMngmntBo> removeEmployee(Integer employeeId);
//public List<EmployeeMngmntBo> fetchEmplyByBand(String emplyBand);
//public List<EmployeeMngmntBo> addEmployee(EmployeeMngmntBo emply) throws EmployeeMgmntException;
//public List<EmployeeMngmntBo> addFreshers(List<EmployeeMngmntBo> emply);
//public List<EmployeeMngmntBo> deltAllEmply(DltByAccnt dltModel) throws EmployeeMgmntException;
//public List<EmployeeMngmntBo> updateExperience(UpdateExperience update)	;
//public List<EmployeeMngmntBo> emplyAllowance(int emplySalary);
//public List<EmployeeMngmntBo> updateBonus(UpdateBonus update);
//public List<EmployeeMngmntBo> getAllEmployee();
	
/**
 * @param employ
 * @return
 */
public EmployeeMngmnt saveEmploy(EmployeeMngmnt employ);
public Flux<EmployeeMngmnt> getEmployeeList();
public Mono<EmployeeMngmnt> findById(String id);
public Flux<EmployeeMngmnt> findByAccount(String accountName);
public EmployeeMngmntBo addEmployee(EmployeeMngmntBo newEmployee);
public Mono<EmployeeMngmnt> dltEmploy(String id);
public EmployeeMngmntBo updateExperience(UpdateExperience updateExperienceBo)
		throws EmployeeMgmntException;
public EmployeeMngmntBo updateBonus(UpdateBonus updateBonusBo);
public List<EmployeeMngmntBo> emplyAllowance(int bonus);
public List<EmployeeMngmnt> getEmpByNameQuery(String name, Integer offset,
		Integer limit);
public List<?> getEmpByNameQury(String name);
		
			
}


