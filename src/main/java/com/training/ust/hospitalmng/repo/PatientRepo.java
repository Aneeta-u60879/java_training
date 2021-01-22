/*Class Name  : PatientRepo
 *Description : PatientRepo  of Patient class 
 *Date of Creation: 8/01/2021
 */
package com.training.ust.hospitalmng.repo;

import org.springframework.stereotype.Repository;

import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import com.training.ust.hospitalmng.model.Patient;

/**
 * Repository class of Patient
 * 
 * @author 87094
 *
 */

@Repository
public interface PatientRepo extends ReactiveCosmosRepository<Patient, String> {

}
