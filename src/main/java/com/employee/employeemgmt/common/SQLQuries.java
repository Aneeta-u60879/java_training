package com.employee.employeemgmt.common;

/**
 * Sql queries 
 * @author 144895
 *
 */
public class SQLQuries {
	public static String FETCH_ALL_WITH_LIMIT = "select"
			+ " emp.employeeId , emp.name"
			+ " from employeeDetails emp where emp.accountName = @account";
	
	public static String FETCH_BY_ID = "select * from employeDetails";
}
