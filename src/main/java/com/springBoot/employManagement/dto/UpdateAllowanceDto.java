package com.springBoot.employManagement.dto;

public class UpdateAllowanceDto {
	private String id;
	private Integer allowance;

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
	 * @return the allowance
	 */
	public Integer getAllowance() {
		return allowance;
	}

	/**
	 * @param allowance
	 *            the allowance to set
	 */
	public void setAllowance(Integer allowance) {
		this.allowance = allowance;
	}

	@Override
	public String toString() {
		return "UpdateAllowanceDto [id=" + id + ", allowance=" + allowance
				+ "]";
	}

}
