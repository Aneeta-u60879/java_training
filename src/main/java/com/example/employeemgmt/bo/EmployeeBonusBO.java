package com.example.employeemgmt.bo;

/**
 * This BO holds data for add employee bonus.
 * 
 * @author 144900
 *
 */
public class EmployeeBonusBO {
	private String employeeId;
	private Double bonus;

	public EmployeeBonusBO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeBonusBO(String employeeId, Double bonus) {
		super();
		this.employeeId = employeeId;
		this.bonus = bonus;
	}

	/**
	 * @return the employeeId
	 */
	public String getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId
	 *            the employeeId to set
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the bonus
	 */
	public Double getBonus() {
		return bonus;
	}

	/**
	 * @param bonus
	 *            the bonus to set
	 */
	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}

	@Override
	public String toString() {
		return "EmployeeBonusBO [employeeId=" + employeeId + ", bonus=" + bonus
				+ "]";
	}

}
