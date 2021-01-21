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
	
	private String bookingDateTime;
	
    private String appointmentDateTime;

    

    public BookingDetails(){
    	
    }
	public BookingDetails(String bookingId, String patientId, String doctorId, String bookingDateTime,
			String appointmentDateTime) {
		super();
		this.bookingId = bookingId;
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.bookingDateTime = bookingDateTime;
		this.appointmentDateTime = appointmentDateTime;

	}
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

	public String getBookingDateTime() {
		return bookingDateTime;
	}

	public String getAppointmentDateTime() {
		return appointmentDateTime;
	}

	public void setAppointmentDateTime(String appointmentDateTime) {
		this.appointmentDateTime = appointmentDateTime;
	}

	public void setBookingDateTime(String bookingDateTime) {
		this.bookingDateTime = bookingDateTime;
	}

	
	
}
