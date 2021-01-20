/*Class Name  : EmployeeRepo
 *Description : EmployeeRepo  of employee class 
 *Date of Creation: 28/11/2020
 */
package com.training.ust.hospitalmng.repo;

import org.springframework.stereotype.Repository;

import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import com.training.ust.hospitalmng.model.BookingDetails;


@Repository
public interface BookingDetailsRepo extends
		ReactiveCosmosRepository<BookingDetails, String> {




}
