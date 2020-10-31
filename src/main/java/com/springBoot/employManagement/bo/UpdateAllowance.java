package com.springBoot.employManagement.bo;

public class UpdateAllowance {
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
		return "UpdateAllowance [id=" + id + ", allowance=" + allowance + "]";
	}
}
