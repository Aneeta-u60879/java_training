/*Class Name  : SQLQueries
 *Description : class for SQLQueries
 *Date of Creation: 28/11/2020
 */
package com.training.ust.hospitalmng.sql;

public class SQLQueries {
/*
 *  SQL queries to fetch data from database
 */
	public static String FETCH_ALL_WITH_LIMIT = "select * from patient where patient.doctorId= @doctorId  ";
	

}
