package com.springBoot.employManagement.common;

public class SQLQuerries {
	public static String FETCH_ALL_WITH_LIMIT = "select * from "
			+ "employee_data where employee_data.accountName = @account offset @offset limit @limit";
	public static String FETCH_ID_WITH_LIMIT = "select emp.id, emp.employId from employee_data emp where "
			+ "emp.accountName = @account offset @offset limit @limit";
}
