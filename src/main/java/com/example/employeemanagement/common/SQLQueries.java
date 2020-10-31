package com.example.employeemanagement.common;

/**
 * Class for SQL Custom Queries
 * 
 * @author 144892
 *
 */
public class SQLQueries {
	
	/*
	 * Query to fetch employees from an account according to given range 
	 */
	public static String FETCH_ALL_WITH_LIMIT = "select * from details_employee where "
			+ "details_employee.accName = @account offset @offset limit @limit";
}
