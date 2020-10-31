package com.neena.employee.common;

public class SQLQueries {

	public static String FETCH_ALL_WITH_LIMIT = "select *"
			+ " from employeemangmnt where employeemangmnt.accountName = @account offset @offset limit @limit";
	                                     
	
	
	public static String FETCH_ID_WITH_LIMIT = "select "
			+ "emp.id ,emp.employeeId"
			
			+ " from employeemangmnt emp where emp.accountName = @account offset @offset limit @limit";
	
}
 