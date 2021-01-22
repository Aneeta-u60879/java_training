/*Class Name  : Doctor
 *Description : Model class  of Doctor 
 *Date of Creation: 8/01/2021
 */
package com.training.ust.hospitalmng.model;

import org.springframework.data.annotation.Id;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;

import lombok.Data;

/**
 * Class for BookingDetails operations
 * 
 * @author 87094
 */
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

	/*
	 * Default constructor of Doctor
	 */
	public Doctor() {
		super();
	}

	/*
	 * Parameterized constructor of Doctor
	 */
	public Doctor(String doctorId, String name, double age, String phone, String consultingStartTime,
			String consultingEndTime, String patients, String dept) {
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
	/*
	 * Method for toString
	 */

	@Override
	public String toString() {
		return "Doctor [doctorId=" + doctorId + ", name=" + name + ", specialisation=" + specialisation + ", address="
				+ address + ", age=" + age + ", phone=" + phone + ", consultingStartTime=" + consultingStartTime
				+ ", consultingEndTime=" + consultingEndTime + ", patients=" + patients + ", dept=" + dept + "]";
	}

	/*
	 * Assessor method for doctorId
	 */
	public String getDoctorId() {
		return doctorId;
	}

	/*
	 * Assessor method for doctorId
	 */
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	/*
	 * Assessor method for name
	 */
	public String getName() {
		return name;
	}

	/*
	 * Assessor method for name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * Assessor method for specialisation
	 */
	public String getSpecialisation() {
		return specialisation;
	}

	/*
	 * Assessor method for specialisation
	 */
	public void setSpecialisation(String specialisation) {
		this.specialisation = specialisation;
	}

	/*
	 * Assessor method for address
	 */

	public String getAddress() {
		return address;
	}

	/*
	 * Assessor method for address
	 */

	public void setAddress(String address) {
		this.address = address;
	}
	/*
	 * Assessor method for age
	 */

	public double getAge() {
		return age;
	}

	/*
	 * Assessor method for age
	 */

	public void setAge(double age) {
		this.age = age;
	}

	/*
	 * Assessor method for dept
	 */

	public String getDept() {
		return dept;
	}

	/*
	 * Assessor method for dept
	 */
	public void setDept(String dept) {
		this.dept = dept;
	}

	/*
	 * Assessor method for phone
	 */
	public String getPhone() {
		return phone;
	}

	/*
	 * Assessor method for phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/*
	 * Assessor method for consultingStartTime
	 */
	public String getConsultingStartTime() {
		return consultingStartTime;
	}

	/*
	 * Assessor method for consultingStartTime
	 */
	public void setConsultingStartTime(String consultingStartTime) {
		this.consultingStartTime = consultingStartTime;
	}

	/*
	 * Assessor method for consultingEndTime
	 */
	public String getConsultingEndTime() {
		return consultingEndTime;
	}

	/*
	 * Assessor method for consultingEndTime
	 */
	public void setConsultingEndTime(String consultingEndTime) {
		this.consultingEndTime = consultingEndTime;
	}

	/*
	 * Assessor method for patients
	 */
	public String getPatients() {
		return patients;
	}

	/*
	 * Assessor method for patients
	 */
	public void setPatients(String patients) {
		this.patients = patients;
	}

}
