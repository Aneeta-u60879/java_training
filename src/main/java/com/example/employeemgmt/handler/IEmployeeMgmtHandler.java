package com.example.employeemgmt.handler;

import java.util.List;

import com.example.employeemgmt.bo.DeleteEmployeeBO;
import com.example.employeemgmt.bo.EmployeeBO;
import com.example.employeemgmt.bo.EmployeeBonusBO;
import com.example.employeemgmt.bo.EmployeeUpdateBO;
import com.example.employeemgmt.exception.EmployeeMgmtException;

/**
 * Interface for EmployeeMgmt handler.
 * 
 * @author 144900
 *
 */
public interface IEmployeeMgmtHandler {
	/**
	 * Function for fetching employees from repo.
	 * 
	 * @return List<EmployeeBO>
	 */
	public List<EmployeeBO> getEmpList();

	/**
	 * Function for Searching an employee from repo.
	 * 
	 * @return EmployeeBO
	 */
	public EmployeeBO searchById(String employeeId);

	/**
	 * Function for searching employees using band from repo.
	 * 
	 * @return List<EmployeeBO>
	 */
	public List<EmployeeBO> searchByBand(String band);

	/**
	 * Function for add an employee.
	 * 
	 * @return EmployeeBO
	 */
	public EmployeeBO addEmployee(EmployeeBO newEmployee)
			throws EmployeeMgmtException;

	/**
	 * Function for adding employees.
	 * 
	 * @return List<EmployeeBO>
	 */
	public List<EmployeeBO> addFreshers(List<EmployeeBO> newEmpList);

	/**
	 * Function for deleting an employee using employee Id.
	 * 
	 * @return EmployeeBO
	 */
	public EmployeeBO deleteEmployee(String employeeId)
			throws EmployeeMgmtException;

	/**
	 * Function for deleting employees using account name and band.
	 * 
	 * @return List<EmployeeBO>
	 */
	public List<EmployeeBO> deleteEmployeeByBand(DeleteEmployeeBO deleteByBand)
			throws EmployeeMgmtException;

	/**
	 * Function for updating an employee.
	 * 
	 * @return EmployeeBO
	 */
	public EmployeeBO updateEmployee(EmployeeUpdateBO updateEmp)
			throws EmployeeMgmtException;

	/**
	 * Function for adding allowance to all employees.
	 * 
	 * @return List<EmployeeBO>
	 */
	public List<EmployeeBO> addAllowance(Double allowance);

	/**
	 * Function for searching employees using account name.
	 * 
	 * @return List<EmployeeBO>
	 */
	public List<EmployeeBO> searchByAcc(String accountName);

	/**
	 * Function for adding bonus to an employee.
	 * 
	 * @return EmployeeBO
	 */

	public EmployeeBO addBonus(EmployeeBonusBO employeeBonusBO)
			throws EmployeeMgmtException;

	/**
	 * Function for fetching employees from repo using custom query.
	 * 
	 * @return List<EmployeeBO>
	 */
	public List<EmployeeBO> getEmpByNameQuery(String name, Integer offset,
			Integer limit);

	/**
	 * Function for fetching an employee from repo using custom query.
	 * 
	 * @return EmployeeBO
	 */
	public EmployeeBO getEmployeesWithId(String employeeId);
}
