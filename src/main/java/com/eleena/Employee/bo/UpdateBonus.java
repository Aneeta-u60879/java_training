package com.eleena.Employee.bo;

public class UpdateBonus {
	Double bonuOfEmployee;
	private String id;
	public Double getBonuOfEmployee() {
		return bonuOfEmployee;
	}
	public UpdateBonus() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UpdateBonus(Double bonuOfEmployee, String id) {
		super();
		this.bonuOfEmployee = bonuOfEmployee;
		this.id = id;
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
