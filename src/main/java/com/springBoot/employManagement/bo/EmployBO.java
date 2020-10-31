package com.springBoot.employManagement.bo;

import lombok.Data;

@Data
public class EmployBO {
	private String id;
	private Integer employId;
	private String name;
	private String joiningDate;
	private String accountName;
	private Double salary;
	private Integer experience;
	private String band;

	public EmployBO(Integer employId, String name, String joiningDate,
			String accountName, Double salary, Integer experience, String band,
			String id) {
		super();
		this.id = id;
		this.employId = employId;
		this.name = name;
		this.joiningDate = joiningDate;
		this.accountName = accountName;
		this.salary = salary;
		this.experience = experience;
		this.band = band;
	}

	public EmployBO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the employId
	 */
	public Integer getEmployId() {
		return employId;
	}

	/**
	 * @param employId
	 *            the employId to set
	 */
	public void setEmployId(Integer employId) {
		this.employId = employId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the joiningDate
	 */
	public String getJoiningDate() {
		return joiningDate;
	}

	/**
	 * @param joiningDate
	 *            the joiningDate to set
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
	 * @param accountName
	 *            the accountName to set
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	/**
	 * @return the salary
	 */
	public Double getSalary() {
		return salary;
	}

	/**
	 * @param salary
	 *            the salary to set
	 */
	public void setSalary(Double salary) {
		this.salary = salary;
	}

	/**
	 * @return the experience
	 */
	public Integer getExperience() {
		return experience;
	}

	/**
	 * @param experience
	 *            the experience to set
	 */
	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	/**
	 * @return the band
	 */
	public String getBand() {
		return band;
	}

	/**
	 * @param band
	 *            the band to set
	 */
	public void setBand(String band) {
		this.band = band;
	}

	@Override
	public String toString() {
		return "EmployBO [employId=" + employId + ", name=" + name
				+ ", joiningDate=" + joiningDate + ", accountName="
				+ accountName + ", salary=" + salary + ", experience="
				+ experience + ", band=" + band + "]";
	}
}
