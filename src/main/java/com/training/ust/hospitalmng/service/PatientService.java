/*Class Name  : PatientService
 *Description : class for Patient` Service 
 *Date of Creation: 28/11/2020
 */
package com.training.ust.hospitalmng.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.training.ust.hospitalmng.dataacess.HospitalDAOImpl;
import com.training.ust.hospitalmng.exception.HospitalMgmtException;
import com.training.ust.hospitalmng.model.Patient;
import com.training.ust.hospitalmng.repo.PatientRepo;
import reactor.core.publisher.Mono;

/**
 * This class for patient service
 */

@Service
public class PatientService {

	@Autowired
	private PatientRepo repo;

	@Autowired
	private HospitalDAOImpl dao;

	/*
	 * Method for savePatient
	 */

	public Patient savePatient(Patient patient) {
		Mono<Patient> savePatient = null;
		savePatient = repo.save(patient);

		return savePatient.block();

	}

	/*
	 * Method for get Patient list By doctor
	 */
	public List<Patient> getPatientList(String doctorId) {
		List<Patient> patList = null;
		try {

			patList = dao.getPatientList(doctorId);
		} catch (HospitalMgmtException e) {
			System.out.println("patient not found");
		}
		return patList;
	}

	/*
	 * Method for update patient
	 */
	public Patient updatePatient(Patient patient) {
		Mono<Patient> response = repo.findById(patient.getPatientId());
		if (response.block() == null) {
			throw new HospitalMgmtException("CONFLICT_PATIENT_NOT_FOUND");
		} else {
			Patient updatPat = response.block();

			updatPat.setPatientId(patient.getPatientId());
			updatPat.setAge(patient.getAge());
			updatPat.setName(patient.getName());
			updatPat.setAddress(patient.getAddress());
			Mono<Patient> savedPatient = repo.save(updatPat);
			return savedPatient.block();

		}
	}

	/*
	 * Method for delete patient
	 */
	public Mono<Patient> deletePatient(String patientId) throws HospitalMgmtException {
		Mono<Patient> response = repo.findById(patientId);
		if (response.block() == null) {
			throw new HospitalMgmtException("patient not found");
		} else {
			Mono<Patient> deleteResponse = response.flatMap(pat -> repo.delete(pat).then(Mono.just(pat)));

			return deleteResponse;
		}
	}

}
