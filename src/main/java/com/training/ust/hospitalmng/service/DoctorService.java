/*Class Name  : DoctorService
 *Description : class for Doctor Service
 *Date of Creation: 28/11/2020
 */
package com.training.ust.hospitalmng.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.training.ust.hospitalmng.dataacess.HospitalDAOImpl;
import com.training.ust.hospitalmng.exception.HospitalMgmtException;
import com.training.ust.hospitalmng.model.Doctor;
import com.training.ust.hospitalmng.repo.DoctorRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * This class for doctor service
 */
@Service
public class DoctorService {

	@Autowired
	private HospitalDAOImpl dao;

	@Autowired
	private DoctorRepo doctorRepo;

	/*
	 * Method for saveDoctor
	 */

	public Doctor saveDoctor(Doctor doctor) {
		Mono<Doctor> saveDoctor = null;
		saveDoctor = doctorRepo.save(doctor);

		return saveDoctor.block();

	}

	/*
	 * Method for get Doctors By Department
	 */

	public List<Doctor> getDoctorsByDepartment(String dept) {

		Flux<Doctor> doctorList = doctorRepo.getDoctorsByDept(dept);
		if (doctorList == null) {
			throw new HospitalMgmtException("CONFLICT_DOCTOR_NOT_FOUND");
		} else {
			List<Doctor> docList = doctorList.collectList().block();

			return docList;
		}
	}

	/*
	 * Method for update doctor
	 */
	public Doctor updateDoctor(Doctor doctor) {
		Mono<Doctor> response = doctorRepo.findById(doctor.getDoctorId());
		if (response.block() == null) {
			throw new HospitalMgmtException("CONFLICT_DOCTOR_NOT_FOUND");
		} else {
			Doctor updatDoc = response.block();

			updatDoc.setDoctorId(doctor.getDoctorId());
			updatDoc.setDept(doctor.getDept());
			updatDoc.setName(doctor.getName());
			updatDoc.setAddress(doctor.getAddress());
			Mono<Doctor> savedDoctor = doctorRepo.save(updatDoc);
			return savedDoctor.block();

		}
	}

	/*
	 * Method for delete doctor
	 */
	public Mono<Doctor> deleteDoctor(String doctorId) throws HospitalMgmtException {
		Mono<Doctor> response = doctorRepo.findById(doctorId);
		if (response.block() == null) {
			throw new HospitalMgmtException("doctor not found");
		} else {
			Mono<Doctor> deleteResponse = response.flatMap(doc -> doctorRepo.delete(doc).then(Mono.just(doc)));

			return deleteResponse;
		}
	}

	/*
	 * Method for get Patient list By doctor
	 */
	public List<Doctor> getDoctorDetails(String dept) {
		List<Doctor> docList = null;
		try {

			docList = dao.getDoctorDetails(dept);
		} catch (HospitalMgmtException e) {
			System.out.println("doctor not found");
		}
		return docList;
	}
}
