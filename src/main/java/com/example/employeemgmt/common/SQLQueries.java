package com.example.employeemgmt.common;

/**
 * This class holds custom SQL queries.
 * 
 * @author 144900
 *
 */
public class SQLQueries {

	public static String FETCH_ALL_WITH_LIMIT = "select * from employeeMgmt where employeeMgmt.accountName = @account offset @offset limit @limit";
	public static String FETCH_EMPLOYEE_WITH_ID = "select * from employeeMgmt where employeeMgmt.employeeId = @employeeId";
}
