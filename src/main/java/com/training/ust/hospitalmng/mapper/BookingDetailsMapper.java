/*Class Name  : BookingDetailsMapper
 *Description : BookingDetailsMapper class  of hospital 
 *Date of Creation: 8/01/2021
 */
package com.training.ust.hospitalmng.mapper;

import org.springframework.stereotype.Component;

import com.training.ust.hospitalmng.model.BookingDetails;

/**
 * Class for BookingDetailsMapper operations
 * 
 * @author 870994
 *
 */
@Component
public class BookingDetailsMapper {

	/*
	 * Method for map BookingDetails
	 */

	public BookingDetails mapBookingDetails(BookingDetails bookingDetails) {
		BookingDetails booking = bookingDetails;
		return booking;
	}

}
