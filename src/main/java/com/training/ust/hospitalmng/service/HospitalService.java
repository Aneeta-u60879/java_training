/*Class Name  : HospitalService
 *Description : class for Hospital Service 
 *Date of Creation: 28/11/2020
 */
package com.training.ust.hospitalmng.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.training.ust.hospitalmng.dataacess.HospitalDAOImpl;
import com.training.ust.hospitalmng.exception.HospitalMgmtException;
import com.training.ust.hospitalmng.model.BookingDetails;
import com.training.ust.hospitalmng.model.Doctor;
import com.training.ust.hospitalmng.model.Patient;
import com.training.ust.hospitalmng.repo.BookingDetailsRepo;
import com.training.ust.hospitalmng.repo.DoctorRepo;
import com.training.ust.hospitalmng.repo.PatientRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class HospitalService {

	@Autowired
	private PatientRepo repo;

	@Autowired
	private HospitalDAOImpl dao;

	@Autowired
	private DoctorRepo repo1;

	@Autowired
	private BookingDetailsRepo repo2;
	/*
	 * Method for saveDoctor
	 */

	public Doctor saveDoctor(Doctor doctor) {
		Mono<Doctor> saveDoctor = null;
		saveDoctor = repo1.save(doctor);

		return saveDoctor.block();

	}

	/*
	 * Method for savePatient
	 */

	public Patient savePatient(Patient patient) {
		Mono<Patient> savePatient = null;
		savePatient = repo.save(patient);

		return savePatient.block();

	}

	public List<Doctor> getDoctorsByDepartment(String dept) {

		Flux<Doctor> doctorList = repo1.getDoctorsByDepartment(dept);
		if (doctorList == null) {
			throw new HospitalMgmtException("CONFLICT_DOCTOR_NOT_FOUND");
		} else {
			List<Doctor> docList = doctorList.collectList().block();

			return docList;
		}
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
	 * Method for get Patient Report
	 */
	public List<BookingDetails> getPatientReport() {
		List<BookingDetails> patList = null;
		try {

			patList = dao.getPatientReport();
		} catch (HospitalMgmtException e) {
			System.out.println("patient not found");
		}
		return patList;
	}

	/*
	 * Method for update doctor
	 */
	public Doctor updateDoctor(Doctor doctor) {
		Mono<Doctor> response = repo1.findById(doctor.getDoctorId());
		if (response.block() == null) {
			throw new HospitalMgmtException("CONFLICT_DOCTOR_NOT_FOUND");
		} else {
			Doctor updatDoc = response.block();

			updatDoc.setDoctorId(doctor.getDoctorId());
			updatDoc.setDept(doctor.getDept());
			updatDoc.setName(doctor.getName());
			updatDoc.setAddress(doctor.getAddress());
			Mono<Doctor> savedDoctor = repo1.save(updatDoc);
			return savedDoctor.block();

		}
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
	 * Method for appoinmentBooking
	 */

	public BookingDetails appoinmentBooking(BookingDetails bookingdetails) {
		Mono<BookingDetails> appoinmentBooking = null;
		appoinmentBooking = repo2.save(bookingdetails);

		return appoinmentBooking.block();

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

	/*
	 * Method for delete doctor
	 */
	public Mono<Doctor> deleteDoctor(String doctorId) throws HospitalMgmtException {
		Mono<Doctor> response = repo1.findById(doctorId);
		if (response.block() == null) {
			throw new HospitalMgmtException("doctor not found");
		} else {
			Mono<Doctor> deleteResponse = response.flatMap(doc -> repo1.delete(doc).then(Mono.just(doc)));

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
