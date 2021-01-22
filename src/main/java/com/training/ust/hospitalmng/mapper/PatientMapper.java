/*Class Name  : PatientMapper
 *Description : PatientMapper class  of hospital 
 *Date of Creation: 08/01/2021
 */

package com.training.ust.hospitalmng.mapper;

import org.springframework.stereotype.Component;

import com.training.ust.hospitalmng.model.Patient;

/**
 * class for PatientMapper operations
 * 
 * @author 87094
 *
 */
@Component
public class PatientMapper {
	/*
	 * Method for map Patient
	 */
	public Patient mapPatientDetail(Patient patient) {
		Patient pat = patient;
		return pat;
	}

}
