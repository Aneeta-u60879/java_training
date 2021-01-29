/*Class Name  : PatientController
 *Description : Controller class  of Patient 
 *Date of Creation: 18/01/2021
 */
package com.training.ust.hospitalmng.contoller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.training.ust.hospitalmng.exception.HospitalMgmtException;
import com.training.ust.hospitalmng.model.Patient;
import com.training.ust.hospitalmng.service.PatientService;
import reactor.core.publisher.Mono;

/**
 * Controller for the CRED operations
 * 
 * @author 87094
 *
 */

@RestController
public class PatientController {

	@Autowired
	private PatientService patientService;

	/**
	 * Method for add Patient
	 * 
	 * @param patient
	 * @param role
	 * @return The patient added or status code 409
	 */
	@PostMapping(value = "/addPatient/{role}")

	public ResponseEntity<String> savePatient(@RequestBody Patient patient, @PathVariable String role) {
		Patient pat = null;
		if (role.equalsIgnoreCase("admin")) {
			pat = patientService.savePatient(patient);
		}

		if (patient.getPatientId() != null) {
			return new ResponseEntity<String>("Added", HttpStatus.CREATED);
		}

		return new ResponseEntity<String>("not Added", HttpStatus.NOT_FOUND);

	}

	/**
	 * Method to get patients of a doctor
	 * 
	 * @param doctorId
	 * @return list of patients
	 */
	@GetMapping(value = "/getPatients/{doctorId}")

	public ResponseEntity<List<Patient>> getPatientList(@PathVariable String doctorId) {
		List<Patient> patient = patientService.getPatientList(doctorId);
		if (patient.size() > 0) {
			return new ResponseEntity<List<Patient>>(patient, HttpStatus.OK);
		} else
			return new ResponseEntity<List<Patient>>(patient, HttpStatus.NO_CONTENT);
	}

	/**
	 * Method to update patient
	 * 
	 * @param patient
	 * @return updated object of patient or bad request
	 */
	@PutMapping(value = "/updatePatient")

	public ResponseEntity<String> updatePatient(@RequestBody Patient patient) {

		try {
			if (patientService.updatePatient(patient) != null) {
				return new ResponseEntity<String>("updated", HttpStatus.OK);
			}
		} catch (HospitalMgmtException e) {

			return new ResponseEntity<String>(" Not updated", HttpStatus.NOT_FOUND);

		}
		return null;
	}

	/**
	 * Method for deleting doctor by patientId
	 * 
	 * @param patientId
	 * @return the patient deleted or bad request
	 */

	@DeleteMapping(value = "/deletePatient/{patientId}")
	public ResponseEntity<String> deletePatient(@PathVariable("patientId") String patientId) {
		Mono<Patient> resp = patientService.deletePatient(patientId);
		System.out.println("block" + resp.block());
		if (resp.block() != null) {
			return new ResponseEntity<String>("deleted", HttpStatus.OK);
		} else

			return new ResponseEntity<String>("not deleted", HttpStatus.NOT_FOUND);

	}
}
