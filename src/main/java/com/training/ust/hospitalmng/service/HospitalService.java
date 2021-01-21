/*Class Name  : HospitalService
 *Description : class for Hospital Service 
 *Date of Creation: 28/11/2020
 */
package com.training.ust.hospitalmng.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.ust.hospitalmng.exception.HospitalMgmtException;
import com.training.ust.hospitalmng.model.Doctor;
import com.training.ust.hospitalmng.model.Patient;
import com.training.ust.hospitalmng.repo.DoctorRepo;
import com.training.ust.hospitalmng.repo.PatientRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@Service
public class HospitalService{

	@Autowired
	private PatientRepo repo;
	
	
	@Autowired
	private DoctorRepo repo1;
/*
 * Method for saveDoctor
 */
	
	public Doctor saveDoctor(Doctor doctor)  {
		Mono<Doctor> saveDoctor = null;
		saveDoctor = repo1.save(doctor);

		return saveDoctor.block();

	}

	/*
	 * Method for savePatient
	 */
	
	public Patient savePatient(Patient patient)  {
		Mono<Patient> savePatient = null;
		savePatient = repo.save(patient);

		return savePatient.block();

	}
	public List<Doctor> getDoctorsByDepartment(String dept) {

		Flux<Doctor> doctorList = repo1.getDoctorsByDepartment(dept);
		if (doctorList == null) {
			throw new HospitalMgmtException("CONFLICT_DOCTOR_NOT_FOUND");
		} else {
			List<Doctor> empList = doctorList.collectList().block();

			return empList;
		}
	}
	
	
}
