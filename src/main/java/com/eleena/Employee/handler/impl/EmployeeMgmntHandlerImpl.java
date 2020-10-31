package com.eleena.Employee.handler.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.eleena.Employee.bo.EmployeeMngmntBo;
import com.eleena.Employee.bo.UpdateBonus;
import com.eleena.Employee.bo.UpdateExperience;
import com.eleena.Employee.dao.EmployeeDAO;
import com.eleena.Employee.exception.EmployeeMgmntException;
import com.eleena.Employee.handler.IEmployeeMgmntHandler;
import com.eleena.Employee.model.EmployeeMngmnt;
import com.eleena.Employee.model.EmployeeQueryModel;
import com.eleena.Employee.repo.IEmployeeMgmntRepo;

@Service
public class EmployeeMgmntHandlerImpl implements IEmployeeMgmntHandler {
	@Autowired
	IEmployeeMgmntRepo repo;
	
	@Autowired
	private EmployeeDAO dao;

	@Override
	public Flux<EmployeeMngmnt> getEmployeeList() {
		Flux<EmployeeMngmnt> employeeList = repo.findAll();
		return employeeList;
	}

	@Override
	public EmployeeMngmnt saveEmploy(EmployeeMngmnt employ) {
		Mono<EmployeeMngmnt> employ1 = repo.save(employ);
		return employ1.block();
	}

	@Override
	public Mono<EmployeeMngmnt> findById(String id) {
		Mono<EmployeeMngmnt> employee = repo.findById(id);
		return employee;
	}

	@Override
	public Flux<EmployeeMngmnt> findByAccount(String accountName) {
		Flux<EmployeeMngmnt> employee = repo.findByAccountName(accountName);
		return employee;
	}

	/* (non-Javadoc)
	 * @see com.eleena.Employee.handler.IEmployeeMgmntHandler#addEmployee(com.eleena.Employee.bo.EmployeeMngmntBo)
	 */
	@Override
	public EmployeeMngmntBo addEmployee(EmployeeMngmntBo newEmployee) {
		EmployeeMngmnt employeeModel = new EmployeeMngmnt();
		BeanUtils.copyProperties(newEmployee, employeeModel);
		Mono<EmployeeMngmnt> saveEmp = repo.save(employeeModel);
		EmployeeMngmntBo empBO = new EmployeeMngmntBo();
		BeanUtils.copyProperties(saveEmp.block(), empBO);
		return empBO;
	}

	@Override
	public Mono<EmployeeMngmnt> dltEmploy(String id) {
		Mono<EmployeeMngmnt> emp = repo.findById(id);
		Mono<EmployeeMngmnt> del = emp.flatMap(employ -> repo.delete(employ)
				.then(Mono.just(employ)));
		return del;
	}

	@Override
	public EmployeeMngmntBo updateExperience(UpdateExperience updateExperienceBo)
			throws EmployeeMgmntException {
		Mono<EmployeeMngmnt> emp = repo.findById(updateExperienceBo.getId());
		if (emp.block() == null) {
			throw new EmployeeMgmntException("No such Employee Found");

		} else {

			EmployeeMngmnt employee = emp.block();
			employee.setExperience(updateExperienceBo.getExperience());
			employee.setBand(updateExperienceBo.getBand());

			Mono<EmployeeMngmnt> saveEmp = repo.save(employee);
			EmployeeMngmntBo empBO = new EmployeeMngmntBo();
			BeanUtils.copyProperties(saveEmp.block(), empBO);
			return empBO;
		}
	}

	@Override
	public EmployeeMngmntBo updateBonus(UpdateBonus updateBonusBo) {
		Mono<EmployeeMngmnt> emp = repo.findById(updateBonusBo.getId());
		if (emp.block() == null) {
			throw new EmployeeMgmntException("No such Employee Found");

		} else {
			EmployeeMngmnt employee = emp.block();
			employee.setSalary(employee.getSalary()
					+ updateBonusBo.getBonuOfEmployee());
			Mono<EmployeeMngmnt> saveEmp = repo.save(employee);
			EmployeeMngmntBo empBO = new EmployeeMngmntBo();
			BeanUtils.copyProperties(saveEmp.block(), empBO);
			return empBO;
		}
	}

	@Override
	public List<EmployeeMngmntBo> emplyAllowance(int bonus)
	{
	Flux<EmployeeMngmnt> emp = repo.findAll();
		List<EmployeeMngmnt> emplyList = emp.collectList().block();
		emplyList.forEach(employ -> {
			if(employ.getSalary()!=null){
			employ.setSalary(employ.getSalary()
		
				+ (employ.getSalary() * bonus) / 100);
			}
			});
		
		Flux<EmployeeMngmnt> saveEmp = repo.saveAll(emplyList);
		
		List<EmployeeMngmnt> empList= saveEmp.collectList().block();
		List<EmployeeMngmntBo> empBo = new ArrayList<EmployeeMngmntBo>();
		empList.forEach(employ->{
			EmployeeMngmntBo employBo= new EmployeeMngmntBo();
			BeanUtils.copyProperties(employ, employBo);
			empBo.add(employBo);
		});
		return empBo;
		
		}
	@Override
	public List<EmployeeMngmnt> getEmpByNameQuery(String name, Integer offset,
			Integer limit) {
		List<EmployeeMngmnt> empList = dao.getEmployeesWithLimit(name, offset, limit);
		return empList;
	}
	@Override
	public List<EmployeeQueryModel> getEmpByNameQury(String account) {
		List<EmployeeQueryModel> empList = dao.getEmployeesWithoutLimit(account);
		return empList;
	}


	// @Override
	// public EmployeeMngmntBo fetchEmplyById(Integer employeeId) {
	// EmployeeMngmntBo employ = repo.fetchEmployeeList().stream()
	// .filter(emply -> emply.getEmployeeId().equals(employeeId))
	// .findAny().orElse(null);
	// if(employ!=null){
	// return employ;
	// }
	// else {
	// return null;
	// }
	// }
	//
	// @Override
	// public List<EmployeeMngmntBo> fetchEmplyByBand(String emplyBand) {
	// List<EmployeeMngmntBo> employList = repo.fetchEmployeeList();
	// List<EmployeeMngmntBo> accountWiseList = null;
	// if (!CollectionUtils.isEmpty(employList)) {
	// accountWiseList = employList
	// .stream()
	// .filter(eachemply -> eachemply.getEmplyBand() != null ? eachemply
	// .getEmplyBand().equalsIgnoreCase(emplyBand) : null)
	// .collect(Collectors.toList());
	// } else {
	// return null;
	// }
	// return accountWiseList;
	// }
	//
	// @Override
	// public List<EmployeeMngmntBo> removeEmployee(Integer employeeId) {
	// List<EmployeeMngmntBo> getListNew = repo.fetchEmployeeList();
	// List<EmployeeMngmntBo> removedEmployee = getListNew
	// .stream()
	// .filter(emply -> emply.getEmployeeId().intValue() == employeeId
	// .intValue()).collect(Collectors.toList());
	// if (!removedEmployee.isEmpty()) {
	// getListNew.removeAll(removedEmployee);
	// } else {
	// getListNew = null;
	// }
	// return getListNew;
	// }
	//
	// @Override
	// public List<EmployeeMngmntBo> addEmployee(EmployeeMngmntBo emply) throws
	// EmployeeMgmntException {
	// List<EmployeeMngmntBo> employList = repo.fetchEmployeeList();
	// EmployeeMngmntBo employ = employList
	// .stream()
	// .filter(empl -> empl.getEmployeeId().intValue() == emply
	// .getEmployeeId().intValue()).findAny().orElse(null);
	//
	// if (employ == null) {
	// employList.add(emply);
	// } else {
	// employList=null;
	// }
	// return employList;
	//
	// }
	//
	// @Override
	// public List<EmployeeMngmntBo> addFreshers(List<EmployeeMngmntBo> emply) {
	// List<EmployeeMngmntBo> employList = repo.fetchEmployeeList();
	// employList.addAll(emply);
	// return employList;
	// }

	// particularly changing or updating we needto create a class and pass that
	// as the attribute)
	// @Override
	// public List<EmployeeMngmntBo> deltAllEmply(DltByAccnt dltModel) throws
	// EmployeeMgmntException {
	// List<EmployeeMngmntBo> updatedeEmployList = repo.fetchEmployeeList();
	// will give not conditionn to filter
	// List<EmployeeMngmntBo> dltdList = updatedeEmployList
	// .stream()
	// .filter(eachEmply -> !(eachEmply.getEmplyAccountName()
	// .equalsIgnoreCase(dltModel.getEmplyAccountName())
	// && eachEmply.getEmplyBand().equalsIgnoreCase(
	// dltModel.getEmplyBand())))
	// .collect(Collectors.toList());
	// List<EmployeeMngmntBo> dltdList = updatedeEmployList
	// .stream()
	// .filter(eachEmply -> (eachEmply.getEmplyAccountName()
	// .equalsIgnoreCase(dltModel.getEmplyAccountName())
	// && eachEmply.getEmplyBand().equalsIgnoreCase(
	// dltModel.getEmplyBand())))
	// .collect(Collectors.toList());
	// if(!dltdList.isEmpty()){
	// updatedeEmployList.remove(dltdList);
	// }else{
	// updatedeEmployListnull;
	// }
	// return dltdList;
	// }
	//
	// @Override
	// public List<EmployeeMngmntBo> updateExperience(UpdateExperience update) {
	// List<EmployeeMngmntBo> updatedeEmployList = repo.fetchEmployeeList();
	// updatedeEmployList.stream().forEach(
	// eachEmployee -> {
	// if (eachEmployee.getEmployeeId().intValue() == update
	// .getEmployeeId().intValue()) {
	// eachEmployee.setEmplyExperience(update
	// .getEmplyExperience());
	// eachEmployee.setEmplyBand(update.getEmplyBand());
	// }
	// });
	// return updatedeEmployList;
	// }
	//
	// @Override
	// public List<EmployeeMngmntBo> emplyAllowance(int bonus) {
	// List<EmployeeMngmntBo> updatedeEmployList = repo.fetchEmployeeList();
	// updatedeEmployList.forEach(eachEmployee -> {
	// eachEmployee.setEmplySalary(eachEmployee.getEmplySalary()
	// + (eachEmployee.getEmplySalary()*bonus)/100);
	//
	// });
	// // List<EmployeeMngmntBo> allowanceList = updatedeEmployList
	// // .stream()
	// // .map(employ -> {
	// // employ.setEmplySalary(employ.getEmplySalary()
	// // + (eachEmployee.getEmplySalary()*bonus)/100)));
	// // return employ;
	// // }).collect(Collectors.toList());
	// // for(EmployeeMngmntBo eachEmployee:updatedeEmployList){
	// // eachEmployee.setEmplySalary(eachEmployee.getEmplySalary()
	// // + (eachEmployee.getEmplySalary()*bonus)/100));
	// // }
	// return updatedeEmployList;
	// }
	//
	// @Override
	// public List<EmployeeMngmntBo> updateBonus(UpdateBonus update) {
	// List<EmployeeMngmntBo> newEmployList = repo.fetchEmployeeList();
	// List<EmployeeMngmntBo> updateBonusEmployeeList = new
	// ArrayList<EmployeeMngmntBo>();
	// // long count = newEmployList.stream()
	// // .filter(empl -> empl.getEmployeeId()==update.getEmployeeId()).count();
	// // if(count>0){
	// updateBonusEmployeeList= (List<EmployeeMngmntBo>) newEmployList.stream()
	// .map(eachEmpl -> {
	// if(eachEmpl.getEmployeeId().intValue()==update.getEmployeeId().intValue());
	// eachEmpl.setEmplySalary(eachEmpl.getEmplySalary()+update.getBonuOfEmployee());
	// return eachEmpl;
	// }).collect(Collectors.toList());
	//
	// return updateBonusEmployeeList;
	// }
	//
	// @Override
	// public List<EmployeeMngmntBo> getAllEmployee() {
	// return repo.fetchEmployeeList();
	// }

}
