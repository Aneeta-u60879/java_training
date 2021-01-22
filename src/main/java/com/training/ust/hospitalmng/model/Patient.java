/*Class Name  : Patient
 *Description : Model class  of Patient 
 *Date of Creation: 8/01/2021
 */

package com.training.ust.hospitalmng.model;

import org.springframework.data.annotation.Id;

import com.azure.spring.data.cosmos.core.mapping.Container;

import lombok.Data;

/**
 * Class for Patient operations
 * 
 * @author 87094
 */
@Container(containerName = "patient")
@Data
public class Patient {

	@Id
	private String patientId;
	protected String name;

	private String personalDetails;

	private String address;

	private double age;

	private String phone;

	private String consultingDoctorId;

	private String lastConsultedDateTime;

	/*
	 * Default constructor of Patient
	 */
	public Patient() {
		super();

	}

	/*
	 * Parameterized constructor of Patient
	 */
	public Patient(String patientId, String name, String personalDetails, double age, String phone,
			String consultingDoctorId, String lastConsultedDateTime) {
		super();
		this.patientId = patientId;
		this.name = name;
		this.age = age;
		this.phone = phone;
		this.consultingDoctorId = consultingDoctorId;
		this.lastConsultedDateTime = lastConsultedDateTime;
	}

	/*
	 * Assessor method for patientId
	 */
	public String getPatientId() {
		return patientId;
	}
	/*
	 * Method for toString
	 */

	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", name=" + name + ", address=" + address + ", age=" + age
				+ ", phone=" + phone + ", consultingDoctorId=" + consultingDoctorId + ", lastConsultedDateTime="
				+ lastConsultedDateTime + "]";
	}

	/*
	 * Assessor method for patientId
	 */
	public void setPatientId(String patientId) {
		this.patientId = patientId;
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
	 * Assessor method for consultingDoctorId
	 */
	public String getConsultingDoctorId() {
		return consultingDoctorId;
	}

	/*
	 * Assessor method for consultingDoctorId
	 */
	public void setConsultingDoctorId(String consultingDoctorId) {
		this.consultingDoctorId = consultingDoctorId;
	}

	/*
	 * Assessor method for lastConsultedDateTime
	 */
	public String getLastConsultedDateTime() {
		return lastConsultedDateTime;
	}

	/*
	 * Assessor method for lastConsultedDateTime
	 */
	public void setLastConsultedDateTime(String lastConsultedDateTime) {
		this.lastConsultedDateTime = lastConsultedDateTime;
	}

}
