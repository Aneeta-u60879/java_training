package com.neena.employee.dto;


public class EmployeeManagementDto {
	private String id;
	private Integer employeeId;
	private String employeeName;
	private String joiningDate;
	private String accountName;
	private Integer salary;
	private Integer experience;
	private String emplyBand;
	public EmployeeManagementDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmployeeManagementDto(String id, Integer employeeId,
			String employeeName, String joiningDate, String accountName,
			Integer salary, Integer experience, String emplyBand) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.joiningDate = joiningDate;
		this.accountName = accountName;
		this.salary = salary;
		this.experience = experience;
		this.emplyBand = emplyBand;
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the employeeId
	 */
	public Integer getEmployeeId() {
		return employeeId;
	}
	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	/**
	 * @return the employeeName
	 */
	public String getEmployeeName() {
		return employeeName;
	}
	/**
	 * @param employeeName the employeeName to set
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	/**
	 * @return the joiningDate
	 */
	public String getJoiningDate() {
		return joiningDate;
	}
	/**
	 * @param joiningDate the joiningDate to set
	 */
	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}
	/**
	 * @return the accountName
	 */
	public String getAccountName() {
		return accountName;
	}
	/**
	 * @param accountName the accountName to set
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	/**
	 * @return the salary
	 */
	public Integer getSalary() {
		return salary;
	}
	/**
	 * @param salary the salary to set
	 */
	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	/**
	 * @return the experience
	 */
	public Integer getExperience() {
		return experience;
	}
	/**
	 * @param experience the experience to set
	 */
	public void setExperience(Integer experience) {
		this.experience = experience;
	}
	/**
	 * @return the emplyBand
	 */
	public String getEmplyBand() {
		return emplyBand;
	}
	/**
	 * @param emplyBand the emplyBand to set
	 */
	public void setEmplyBand(String emplyBand) {
		this.emplyBand = emplyBand;
	}
	@Override
	public String toString() {
		return "EmployeeManagementDto [id=" + id + ", employeeId=" + employeeId
				+ ", employeeName=" + employeeName + ", joiningDate="
				+ joiningDate + ", accountName=" + accountName + ", salary="
				+ salary + ", experience=" + experience + ", emplyBand="
				+ emplyBand + "]";
	}
 
}
