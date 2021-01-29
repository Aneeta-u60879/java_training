/*Class Name  : BookingDetailsService
 *Description : class for Booking Details Service
 *Date of Creation: 28/11/2020
 */
package com.training.ust.hospitalmng.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.training.ust.hospitalmng.dataacess.HospitalDAOImpl;
import com.training.ust.hospitalmng.exception.HospitalMgmtException;
import com.training.ust.hospitalmng.model.BookingDetails;
import com.training.ust.hospitalmng.repo.BookingDetailsRepo;
import reactor.core.publisher.Mono;

/**
 * This class for BookingDetails Service
 */

@Service
public class BookingDetailsService {

	@Autowired
	private HospitalDAOImpl dao;

	@Autowired
	private BookingDetailsRepo bookingRepo;

	/*
	 * Method for get Patient Report
	 */
	public List<BookingDetails> getPatientReport() {
		List<BookingDetails> patList = null;
		try {

			patList = dao.getPatientReport();
		} catch (HospitalMgmtException e) {
			System.out.println("patient not found");
		}
		return patList;
	}

	/*
	 * Method for appoinmentBooking
	 */

	public BookingDetails appoinmentBooking(BookingDetails bookingdetails) {
		Mono<BookingDetails> appoinmentBooking = null;
		appoinmentBooking = bookingRepo.save(bookingdetails);

		return appoinmentBooking.block();

	}

}
