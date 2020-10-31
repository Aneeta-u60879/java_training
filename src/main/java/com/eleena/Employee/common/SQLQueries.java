package com.eleena.Employee.common;

public class SQLQueries {
	public static String FETCH_ALL_WITH_LIMIT = "select * from employeeMgmt where employeeMgmt.accountName = @account offset @offset limit @limit";

}
