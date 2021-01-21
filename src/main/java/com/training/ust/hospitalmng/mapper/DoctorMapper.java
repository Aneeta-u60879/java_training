package com.training.ust.hospitalmng.mapper;

import org.springframework.stereotype.Component;

import com.training.ust.hospitalmng.model.Doctor;


/**
 * @author 144895
 *
 */
@Component
public class DoctorMapper {
	
	public Doctor mapDoctorDetail(Doctor doctor) {
		Doctor doc = doctor;
		return doc;
	}

}
