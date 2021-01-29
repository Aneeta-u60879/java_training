/*Class Name  : BookingDetailsController
 *Description : Controller class  of BookingDetails 
 *Date of Creation: 18/01/2021
 */
package com.training.ust.hospitalmng.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.training.ust.hospitalmng.model.BookingDetails;
import com.training.ust.hospitalmng.service.BookingDetailsService;

/**
 * Controller for the CRED operations
 * 
 * @author 87094
 *
 */

@RestController
public class BookingDetailsController {

	@Autowired
	private BookingDetailsService bookingService;

	/**
	 * Method for add Appointments
	 * 
	 * @param bookingdetails
	 * @return The bookingdetails added or status code 409
	 */
	@PostMapping(value = "/addAppoinment")

	public ResponseEntity<String> addAppoinment(@RequestBody BookingDetails bookingdetails) {

		bookingService.appoinmentBooking(bookingdetails);
		if (bookingdetails.getBookingId() != null) {
			return new ResponseEntity<String>("Appoinment Booked", HttpStatus.CREATED);
		}

		return new ResponseEntity<String>(" Appoinment Not Booked", HttpStatus.NOT_FOUND);

	}

	/**
	 * Method for Patient Report
	 * 
	 * @return list of patients
	 */
	@GetMapping(value = "/getPatientReport")

	public ResponseEntity<List<BookingDetails>> getPatientReport() {
		List<BookingDetails> patientList = bookingService.getPatientReport();

		if (patientList.size() > 0) {
			return new ResponseEntity<List<BookingDetails>>(patientList, HttpStatus.OK);
		} else
			return new ResponseEntity<List<BookingDetails>>(patientList, HttpStatus.NO_CONTENT);

	}
}
