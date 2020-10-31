package com.springBoot.employManagement.dto;

public class UpdateBonusDto {
	private String id;
	private Double bonus;

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
		return "UpdateBonusDto [id=" + id + ", bonus=" + bonus + "]";
	}

}
