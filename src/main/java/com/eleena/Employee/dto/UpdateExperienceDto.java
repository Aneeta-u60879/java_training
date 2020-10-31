package com.eleena.Employee.dto;

public class UpdateExperienceDto {
	private String id;
	private Float experience;
	private String band;
	public UpdateExperienceDto(String id, Float experience, String band) {
		super();
		this.id = id;
		this.experience = experience;
		this.band = band;
	}
	public UpdateExperienceDto() {
		super();
		// TODO Auto-generated constructor stub
	}
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
	
}
