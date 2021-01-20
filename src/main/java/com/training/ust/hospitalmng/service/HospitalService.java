/*Class Name  : HospitalService
 *Description : class for Hospital Service 
 *Date of Creation: 28/11/2020
 */
package com.training.ust.hospitalmng.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.training.ust.hospitalmng.dataacess.HospitalDAOImpl;
import com.training.ust.hospitalmng.model.Doctor;
import com.training.ust.hospitalmng.repo.DoctorRepo;
import com.training.ust.hospitalmng.repo.PatientRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *This class for employee service
 */


@Service
public class HospitalService{

	@Autowired
	private DoctorRepo repo;
	
	@Autowired
	private PatientRepo repo1;

	@Autowired
	private HospitalDAOImpl dao;
/*
 * Method for saveDoctor
 */
	
	public Doctor saveDoctor(Doctor doctor)  {
		Mono<Doctor> saveDoctor = null;
		saveDoctor = repo.save(doctor);

		return saveDoctor.block();

	}


}
