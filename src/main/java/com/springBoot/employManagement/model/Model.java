package com.springBoot.employManagement.model;

public class Model {

	private String id;
	private Integer employId;

//	public Model(String id, Integer employId) {
//		super();
//		this.id = id;
//		this.employId = employId;
//	}

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

	@Override
	public String toString() {
		return "Model [id=" + id + ", employId=" + employId + "]";
	}

}
