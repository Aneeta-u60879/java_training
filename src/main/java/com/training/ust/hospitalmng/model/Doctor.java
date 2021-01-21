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

	private String address;

	private double age;

	private String phone;

	private String consultingStartTime;

	private String consultingEndTime;

	private String patients;

	@PartitionKey
	private String dept;
	
	public Doctor (){
		
	}

	public Doctor(String doctorId, String name, double age, String phone,
			String consultingStartTime, String consultingEndTime, String patients, String dept) {
		super();
		this.doctorId = doctorId;
		this.name = name;
		this.age = age;
		this.phone = phone;
		this.consultingStartTime = consultingStartTime;
		this.consultingEndTime = consultingEndTime;
		this.patients = patients;
		this.dept = dept;
	}

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

	

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	

	public double getAge() {
		return age;
	}

	public void setAge(double age) {
		this.age = age;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
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
