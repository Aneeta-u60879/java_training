/*Class Name  : SQLQueries
 *Description : class for SQLQueries
 *Date of Creation: 28/11/2020
 */
package com.training.ust.employee.sql;

public class SQLQueries {
/*
 *  SQL queries to fetch data from database
 */
	public static String FETCH_ALL_WITH_LIMIT = "select * from emp where emp.accountName = @accountName offset @of ";
	public static String FETCH_ALL_WITH_JoiningDate = "	SELECT * FROM emp  WHERE joiningDate BETWEEN '2005-01-01' AND '2005-12-31'"; 

}
