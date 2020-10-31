package com.eleena.Employee.dto;

public class UpdateBonusDto {
	Double bonuOfEmployee;
	private String id;
	public UpdateBonusDto(Double bonuOfEmployee, String id) {
		super();
		this.bonuOfEmployee = bonuOfEmployee;
		this.id = id;
	}
	public UpdateBonusDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Double getBonuOfEmployee() {
		return bonuOfEmployee;
	}
	public void setBonuOfEmployee(Double bonuOfEmployee) {
		this.bonuOfEmployee = bonuOfEmployee;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	

	
}
