/*Class Name  : BookingDetails
 *Description : Model class  of BookingDetails 
 *Date of Creation: 8/01/2021
 */
package com.training.ust.hospitalmng.model;

import org.springframework.data.annotation.Id;

import com.azure.spring.data.cosmos.core.mapping.Container;

import lombok.Data;
/**
 * Class for BookingDetails operations
 * @author 87094
 */
@Container(containerName = "bookingdetails")
@Data
public class BookingDetails {

	@Id
	private String bookingId;
	
	protected String patientId;
	
	private String doctorId;
	
	private String bookingDateTime;
	
    private String appointmentDateTime;

    
    /*
	 * Default constructor of BookingDetails
	 */
    public BookingDetails(){
    	super();
    }
    
    /*
	 * Parameterized  constructor of BookingDetails
	 */
	public BookingDetails(String bookingId, String patientId, String doctorId, String bookingDateTime,
			String appointmentDateTime) {
		super();
		this.bookingId = bookingId;
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.bookingDateTime = bookingDateTime;
		this.appointmentDateTime = appointmentDateTime;

	}
	/*
	 * Method for toString
	 */
	@Override
	public String toString() {
		return "BookingDetails [bookingId=" + bookingId + ", patientId=" + patientId + ", doctorId=" + doctorId
				+ ", bookingDateTime=" + bookingDateTime + ", appointmentDateTime=" + appointmentDateTime + "]";
	}

	/*
	 * Assessor method for bookingId
	 */
	public String getBookingId() {
		return bookingId;
	}

	/*
	 * Assessor method for bookingId
	 */
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	/*
	 * Assessor method for patientId
	 */
	public String getPatientId() {
		return patientId;
	}

	/*
	 * Assessor method for patientId
	 */
	public void setPatientId(String patientId) {
		this.patientId = patientId;
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
	 * Assessor method for bookingDateTime
	 */
	public String getBookingDateTime() {
		return bookingDateTime;
	}

	/*
	 * Assessor method for bookingDateTime
	 */
	public String getAppointmentDateTime() {
		return appointmentDateTime;
	}

	/*
	 * Assessor method for appointmentDateTime
	 */
	public void setAppointmentDateTime(String appointmentDateTime) {
		this.appointmentDateTime = appointmentDateTime;
	}

	/*
	 * Assessor method for appointmentDateTime
	 */
	public void setBookingDateTime(String bookingDateTime) {
		this.bookingDateTime = bookingDateTime;
	}

	
	
}
