package com.training.ust.hospitalmng.model;

import org.springframework.data.annotation.Id;

import com.azure.spring.data.cosmos.core.mapping.Container;

import lombok.Data;

@Container(containerName = "bookingdetails")
@Data
public class BookingDetails {

	@Id
	private String bookingId;
	
	protected String patientId;
	
	private String doctorId;
	
	private double bookingDateTime;
	
    private double appointmentDateTime;

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public double getBookingDateTime() {
		return bookingDateTime;
	}

	public void setBookingDateTime(double bookingDateTime) {
		this.bookingDateTime = bookingDateTime;
	}

	public double getAppointmentDateTime() {
		return appointmentDateTime;
	}

	public void setAppointmentDateTime(double appointmentDateTime) {
		this.appointmentDateTime = appointmentDateTime;
	}
	
	
}
