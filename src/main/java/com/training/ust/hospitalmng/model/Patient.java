package com.training.ust.hospitalmng.model;

import org.springframework.data.annotation.Id;

import com.azure.spring.data.cosmos.core.mapping.Container;

import lombok.Data;

@Container(containerName = "patient")
@Data
public class Patient {
	
	
	@Id
	private String patientId;
	protected String name;
	
	private String personalDetails;
	
	private double address;
	
    private double age;
	
	private String phone;
	
	private String consultingDoctorId;
	
	private String lastConsultedDateTime;

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPersonalDetails() {
		return personalDetails;
	}

	public void setPersonalDetails(String personalDetails) {
		this.personalDetails = personalDetails;
	}

	public double getAddress() {
		return address;
	}

	public void setAddress(double address) {
		this.address = address;
	}

	public double getAge() {
		return age;
	}

	public void setAge(double age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getConsultingDoctorId() {
		return consultingDoctorId;
	}

	public void setConsultingDoctorId(String consultingDoctorId) {
		this.consultingDoctorId = consultingDoctorId;
	}

	public String getLastConsultedDateTime() {
		return lastConsultedDateTime;
	}

	public void setLastConsultedDateTime(String lastConsultedDateTime) {
		this.lastConsultedDateTime = lastConsultedDateTime;
	}
	

}
