package com.employee.employeemgmt.handler;

import java.util.List;

import com.employee.employeemgmt.BO.UpadateExperience;
import com.employee.employeemgmt.BO.UpdateBonus;
import com.employee.employeemgmt.DTO.EmployeeMgmtDTO;
import com.employee.employeemgmt.model.ByAccountModel;
import com.employee.employeemgmt.model.EmployModel;

public interface IEmployeeMgmtHandler {

	
	/**
	 * to get all the employ
	 * @return List<EmployeeMgmtDTO>
	 */
	public List<EmployeeMgmtDTO> getEmployeeList(); 
	
	/**
	 * To save employ
	 * @param employ
	 * @return return the employ added
	 */
	public EmployModel saveEmploy(EmployModel employ); 
	
	/**
	 * To delete an employ
	 * @param employeeId
	 * @return deleted employ
	 */
	public EmployModel deleteEmploye(String employeeId);
	
	/**
	 * to get employ of an account
	 * @param accountName
	 * @return employ to a particular account
	 */
	public EmployModel getEmployeeByAccnt(String accountName);
	
	/**
	 * To update experience of an employ
	 * @param employ
	 * @return EmployModel updated
	 */
	public EmployModel updateEmploy(UpadateExperience employ); 
	
	/**
	 * To add freshers
	 * @param employ
	 * @return EmployModel freshers
	 */
	public EmployModel addFreshers(List<EmployModel> employ);
	
	/**
	 * To update bonus of an employ
	 * @param updateBonus
	 * @return
	 */
	public EmployModel updateBonus(UpdateBonus updateBonus); 
	
	/**
	 * Get list of employ of particular employ
	 * @param account 
	 * @return List<ByAccountModel> 
	 */
	public List<ByAccountModel> getByAccnt(String account);
	
}
