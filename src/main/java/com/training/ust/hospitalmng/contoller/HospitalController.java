/*Class Name  : HospitalController
 *Description : Controller class  of Hospital 
 *Date of Creation: 18/01/2021
 */
package com.training.ust.hospitalmng.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.training.ust.hospitalmng.exception.HospitalMgmtException;
import com.training.ust.hospitalmng.model.BookingDetails;
import com.training.ust.hospitalmng.model.Doctor;
import com.training.ust.hospitalmng.model.Patient;
import com.training.ust.hospitalmng.service.HospitalService;

/**
 * Controller for the CRED operations
 * 
 * @author 87094
 *
 */

@RestController
public class HospitalController {

	@Autowired
	private HospitalService service;

	/**
	 * Method for add doctor
	 * 
	 * @param doctor
	 * @param role
	 * @return The doctor added or status code 409
	 */
	@PostMapping(value = "/addDoctor/{role}")

	public ResponseEntity<String> saveDoctor(@RequestBody Doctor doctor, @PathVariable String role) {

		if (role == "admin") {
			service.saveDoctor(doctor);

		}
		if (doctor.getDoctorId() != null) {
			return new ResponseEntity<String>("Added", HttpStatus.CREATED);
		}

		return new ResponseEntity<String>("not Added", HttpStatus.NOT_FOUND);

	}

	/**
	 * Method for add Patient
	 * 
	 * @param patient
	 * @param role
	 * @return The patient added or status code 409
	 */
	@PostMapping(value = "/addPatient/{role}")

	public ResponseEntity<String> savePatient(@RequestBody Patient patient, @PathVariable String role) {

		if (role == "admin") {
			service.savePatient(patient);
		}

		if (patient.getPatientId() != null) {
			return new ResponseEntity<String>("Added", HttpStatus.CREATED);
		}

		return new ResponseEntity<String>("not Added", HttpStatus.NOT_FOUND);

	}

	/**
	 * Method to get doctors by department
	 * 
	 * @param dept
	 * @return list of doctors
	 */
	@GetMapping(value = "/getDoctor/{dept}")

	public ResponseEntity<List<Doctor>> getDoctorsByDepartment(@PathVariable((String) "dept") String dept) {
		List<Doctor> doctor = service.getDoctorsByDepartment(dept);

		if (doctor.size() > 0) {
			return new ResponseEntity<List<Doctor>>(doctor, HttpStatus.OK);
		} else
			return new ResponseEntity<List<Doctor>>(doctor, HttpStatus.NO_CONTENT);

	}

	/**
	 * Method to get patients of a doctor
	 * 
	 * @param doctorId
	 * @return list of patients
	 */
	@GetMapping(value = "/getPatients/{doctorId}")

	public ResponseEntity<List<Patient>> getPatientList(@PathVariable String doctorId) {
		List<Patient> patient = service.getPatientList(doctorId);
		if (patient.size() > 0) {
			return new ResponseEntity<List<Patient>>(patient, HttpStatus.OK);
		} else
			return new ResponseEntity<List<Patient>>(patient, HttpStatus.NO_CONTENT);
	}

	/**
	 * Method to update doctor
	 * 
	 * @param doctor
	 * @return updated object of doctor or bad request
	 */
	@PutMapping(value = "/updateDoctor")

	public ResponseEntity<String> updateDoctor(@RequestBody Doctor doctor) {

		try {
			if (service.updateDoctor(doctor) != null) {
				return new ResponseEntity<String>("updated", HttpStatus.OK);
			}
		} catch (HospitalMgmtException e) {

			return new ResponseEntity<String>(" Not updated", HttpStatus.NOT_FOUND);

		}
		return null;
	}

	/**
	 * Method to update patient
	 * 
	 * @param patient
	 * @return updated object of patient or bad request
	 */
	@PutMapping(value = "/updatePatient")

	public ResponseEntity<String> updatePatient(@RequestBody Patient patient) {

		try {
			if (service.updatePatient(patient) != null) {
				return new ResponseEntity<String>("updated", HttpStatus.OK);
			}
		} catch (HospitalMgmtException e) {

			return new ResponseEntity<String>(" Not updated", HttpStatus.NOT_FOUND);

		}
		return null;
	}

	/**
	 * Method for add Appointments
	 * 
	 * @param bookingdetails
	 * @return The bookingdetails added or status code 409
	 */
	@PostMapping(value = "/addAppoinment")

	public ResponseEntity<String> addAppoinment(@RequestBody BookingDetails bookingdetails) {

		service.appoinmentBooking(bookingdetails);
		if (bookingdetails.getBookingId() != null) {
			return new ResponseEntity<String>("Appoinment Booked", HttpStatus.CREATED);
		}

		return new ResponseEntity<String>(" Appoinment Not Booked", HttpStatus.NOT_FOUND);

	}

	/**
	 * Method to get doctor details by department
	 * 
	 * @param dept
	 * @return list of doctors
	 */
	@GetMapping(value = "/getDoctorDetails/{dept}")

	public ResponseEntity<List<Doctor>> getDoctorDetails(@PathVariable((String) "dept") String dept) {
		List<Doctor> doctor = service.getDoctorDetails(dept);

		if (doctor.size() > 0) {
			return new ResponseEntity<List<Doctor>>(doctor, HttpStatus.OK);
		} else
			return new ResponseEntity<List<Doctor>>(doctor, HttpStatus.NO_CONTENT);

	}

	/**
	 * Method for Patient Report
	 * 
	 * @return list of patients
	 */
	@GetMapping(value = "/getPatientReport")

	public ResponseEntity<List<BookingDetails>> getPatientReport() {
		List<BookingDetails> patientList = service.getPatientReport();

		if (patientList.size() > 0) {
			return new ResponseEntity<List<BookingDetails>>(patientList, HttpStatus.OK);
		} else
			return new ResponseEntity<List<BookingDetails>>(patientList, HttpStatus.NO_CONTENT);

	}
}
