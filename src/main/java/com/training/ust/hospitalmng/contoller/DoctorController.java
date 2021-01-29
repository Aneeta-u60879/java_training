/*Class Name  : DoctorController
 *Description : Controller class  of Doctor 
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
import com.training.ust.hospitalmng.model.Doctor;
import com.training.ust.hospitalmng.service.DoctorService;
import reactor.core.publisher.Mono;

/**
 * Controller for the CRED operations
 * 
 * @author 87094
 *
 */

@RestController
public class DoctorController {

	@Autowired
	private DoctorService doctorService;

	/**
	 * Method for add doctor
	 * 
	 * @param doctor
	 * @param role
	 * @return The doctor added or status code 409
	 */
	@PostMapping(value = "/addDoctor/{role}")

	public ResponseEntity<String> saveDoctor(@RequestBody Doctor doctor, @PathVariable String role) {
		Doctor doc = null;
		if (role.equalsIgnoreCase("admin")) {
			doc = doctorService.saveDoctor(doctor);

		}
		if (doc.getDoctorId() != null) {
			return new ResponseEntity<String>("Added", HttpStatus.CREATED);
		}

		return new ResponseEntity<String>("not Added", HttpStatus.NOT_FOUND);

	}

	/**
	 * Method to get doctors by department
	 * 
	 * @param dept
	 * @return list of doctors
	 */
	@GetMapping(value = "/getDoctor/{dept}")

	public ResponseEntity<List<Doctor>> getDoctorsByDepartment(@PathVariable((String) "dept") String dept) {
		List<Doctor> doctor = doctorService.getDoctorsByDepartment(dept);

		if (doctor.size() > 0) {
			return new ResponseEntity<List<Doctor>>(doctor, HttpStatus.OK);
		} else
			return new ResponseEntity<List<Doctor>>(doctor, HttpStatus.NO_CONTENT);

	}

	/**
	 * Method to update doctor
	 * 
	 * @param doctor
	 * @return updated object of doctor or bad request
	 */
	@PutMapping(value = "/updateDoctor")

	public ResponseEntity<String> updateDoctor(@RequestBody Doctor doctor) {

		try {
			if (doctorService.updateDoctor(doctor) != null) {
				return new ResponseEntity<String>("updated", HttpStatus.OK);
			}
		} catch (HospitalMgmtException e) {

			return new ResponseEntity<String>(" Not updated", HttpStatus.NOT_FOUND);

		}
		return null;
	}

	/**
	 * Method to get doctor details by department
	 * 
	 * @param dept
	 * @return list of doctors
	 */
	@GetMapping(value = "/getDoctorDetails/{dept}")

	public ResponseEntity<List<Doctor>> getDoctorDetails(@PathVariable((String) "dept") String dept) {
		List<Doctor> doctor = doctorService.getDoctorDetails(dept);

		if (doctor.size() > 0) {
			return new ResponseEntity<List<Doctor>>(doctor, HttpStatus.OK);
		} else
			return new ResponseEntity<List<Doctor>>(doctor, HttpStatus.NO_CONTENT);

	}

	/**
	 * Method for deleting doctor by doctorId
	 * 
	 * @param doctorId
	 * @return the doctor deleted or bad request
	 */

	@DeleteMapping(value = "/deleteDoctor/{id}")
	public ResponseEntity<String> deleteDoctor(@PathVariable("doctorId") String doctorId) {
		Mono<Doctor> resp = doctorService.deleteDoctor(doctorId);
		System.out.println("block" + resp.block());
		if (resp.block() != null) {
			return new ResponseEntity<String>("deleted", HttpStatus.OK);
		} else

			return new ResponseEntity<String>("not deleted", HttpStatus.NOT_FOUND);

	}
}
