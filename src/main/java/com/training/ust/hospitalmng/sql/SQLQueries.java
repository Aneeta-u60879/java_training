/*Class Name  : SQLQueries
 *Description : class for SQLQueries
 *Date of Creation: 8/01/2021
 */
package com.training.ust.hospitalmng.sql;

public class SQLQueries {

	/*
	 * SQL queries to fetch data from database
	 */
	public static String FETCH_ALL_WITH_LIMIT = "select * from patient where patient.consultingDoctorId= @doctorId  ";

	public static final String FETCH_ALL_DOCTORS_DEPT = "select name,phone,dept,specialization from doctor where doctor.dept= @dept";

	public static String FETCH_ALL_PATIENT_REPORT = "select * from bookingdetails  where bookingdetails.appointmentDateTime between '2020-01-01' AND '2020-12-31'";
}
