package com.training.ust.hospitalmng.model;

import org.springframework.data.annotation.Id;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;

import lombok.Data;




@Container(containerName = "doctor")
@Data
public class Doctor {
	@Id
	private String doctorId;
	protected String name;
	
	private String specialisation;
	
	
	
    private double personalDetails;
	
	private String address;
	
	private String age;
	
	private String phone;
	
	private String consultingStartTime;
	
	private String consultingEndTime;
	
	private String patients;
	
	@PartitionKey
	private double dept;

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecialisation() {
		return specialisation;
	}

	public void setSpecialisation(String specialisation) {
		this.specialisation = specialisation;
	}

	public double getDept() {
		return dept;
	}

	public void setDept(double dept) {
		this.dept = dept;
	}

	public double getPersonalDetails() {
		return personalDetails;
	}

	public void setPersonalDetails(double personalDetails) {
		this.personalDetails = personalDetails;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getConsultingStartTime() {
		return consultingStartTime;
	}

	public void setConsultingStartTime(String consultingStartTime) {
		this.consultingStartTime = consultingStartTime;
	}

	public String getConsultingEndTime() {
		return consultingEndTime;
	}

	public void setConsultingEndTime(String consultingEndTime) {
		this.consultingEndTime = consultingEndTime;
	}

	public String getPatients() {
		return patients;
	}

	public void setPatients(String patients) {
		this.patients = patients;
	}

	

}
