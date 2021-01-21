package com.training.ust.hospitalmng.mapper;

import org.springframework.stereotype.Component;

import com.training.ust.hospitalmng.model.Patient;


/**
 * @author 144895
 *
 */
@Component
public class PatientMapper {
	
	public Patient mapPatientDetail(Patient patient) {
		Patient pat = patient;
		return pat;
	}

}
