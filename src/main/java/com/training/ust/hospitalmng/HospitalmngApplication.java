/*Class Name  : HospitalmngApplication
 *Description :  class  of Hospital Management Application 
 *Date of Creation: 8/01/2021
 */
package com.training.ust.hospitalmng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * HospitalmngApplication class of hospital
 */
@SpringBootApplication
@EnableAutoConfiguration
public class HospitalmngApplication {
	/*
	 * Main method for Hospitalmng Application
	 */

	public static void main(String[] args) {
		SpringApplication.run(HospitalmngApplication.class, args);
	}

}
