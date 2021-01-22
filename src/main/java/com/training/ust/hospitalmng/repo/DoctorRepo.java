/*Class Name  : DoctorRepo
 *Description : DoctorRepo  of Doctor class 
 *Date of Creation: 8/01/2021
 */
package com.training.ust.hospitalmng.repo;

import org.springframework.stereotype.Repository;

import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;

import com.training.ust.hospitalmng.model.Doctor;

import reactor.core.publisher.Flux;

/**
 * Repository class of Doctor
 * 
 * @author 87094
 *
 */

@Repository
public interface DoctorRepo extends ReactiveCosmosRepository<Doctor, String> {

	/*
	 * Methods for access field in Doctor Repository
	 */

	Flux<Doctor> getDoctorsByDepartment(String dept);
}
