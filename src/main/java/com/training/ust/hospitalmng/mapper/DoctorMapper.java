/*Class Name  : DoctorMapper
 *Description : DoctorMapper class  of hospital 
 *Date of Creation: 08/01/2021
 */
package com.training.ust.hospitalmng.mapper;

import org.springframework.stereotype.Component;

import com.training.ust.hospitalmng.model.Doctor;

/**
 * class for DoctorMapper operations
 * 
 * @author 87094
 *
 */
@Component
public class DoctorMapper {

	/*
	 * Method for map Doctor
	 */
	public Doctor mapDoctorDetail(Doctor doctor) {
		Doctor doc = doctor;
		return doc;
	}

}
