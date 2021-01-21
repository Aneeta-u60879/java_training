/*Class Name  : EmployeeController
 *Description : Controller class  of employee 
 *Date of Creation: 28/11/2020
 */
package com.training.ust.hospitalmng.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;
import com.training.ust.hospitalmng.model.Doctor;
import com.training.ust.hospitalmng.model.Patient;
import com.training.ust.hospitalmng.service.HospitalService;

import reactor.core.publisher.Flux;




@RestController
public class HospitalController {

	@Autowired
	private HospitalService service;
	
	
	@PostMapping(value = "/addDoctor")
	public ResponseEntity<String> saveDoctor(@RequestBody Doctor doctor)  {

	    
		service.saveDoctor(doctor);
		
		
		if (doctor.getDoctorId() != null) {
			return new ResponseEntity<String>("Added", HttpStatus.CREATED);
		}

		return new ResponseEntity<String>("not Added", HttpStatus.NOT_FOUND);
	

	

}
	
	
	@PostMapping(value = "/addPatient")
	public ResponseEntity<String> savePatient(@RequestBody Patient patient)  {

	    
		service.savePatient(patient);
		
		
		if (patient.getPatientId() != null) {
			return new ResponseEntity<String>("Added", HttpStatus.CREATED);
		}

		return new ResponseEntity<String>("not Added", HttpStatus.NOT_FOUND);
	

	

}
	
	@GetMapping(value = "/getDoctor/{dept}")
	public ResponseEntity<List<Doctor>> getDoctorsByDepartment(@PathVariable((String) "dept") String dept) {
		List<Doctor> doctor = service.getDoctorsByDepartment(dept);

		if (doctor.size() > 0) {
			return new ResponseEntity<List<Doctor>>(doctor, HttpStatus.OK);
		} else
			return new ResponseEntity<List<Doctor>>(doctor, HttpStatus.NO_CONTENT);

	}
	
	
}
