/*Class Name  : BookingDetailsRepo
 *Description : BookingDetailsRepo  of BookingDetails class 
 *Date of Creation: 08/01/2021
 */
package com.training.ust.hospitalmng.repo;

import org.springframework.stereotype.Repository;

import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import com.training.ust.hospitalmng.model.BookingDetails;

/**
 * Repository class of BookingDetails
 * 
 * @author 87094
 *
 */

@Repository
public interface BookingDetailsRepo extends
		ReactiveCosmosRepository<BookingDetails, String> {




}
