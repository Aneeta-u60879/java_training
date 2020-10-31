package com.eleena.Employee.bo;

import java.time.LocalDate;

public class UpdateExperience {
	public UpdateExperience(String id, Float experience, String band) {
		super();
		this.id = id;
		this.experience = experience;
		this.band = band;
	}
	private String id;
	private Float experience;
	private String band;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Float getExperience() {
		return experience;
	}
	public void setExperience(Float experience) {
		this.experience = experience;
	}
	public String getBand() {
		return band;
	}
	public void setBand(String band) {
		this.band = band;
	}
	public UpdateExperience() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
