package com.example.employeemgmt.common;

/**
 * Class for holding common constants used in employeeMgmt.
 * 
 * @author 144900
 *
 */
public class EmployeeMgmtConstants {

	// Swagger Notes
	public static final String NOTES_FOR_SWAGGER = "Returns 200/204 Response";
	public static final String SWAGGER_NOTES = "Returns 201/409 Response";

	// ApiOperation value
	public static final String GET_EMPLOYEE_LIST_VALUE = "Fetch all employees";
	public static final String GET_EMPLOYEE_BY_ID = "Fetch employee by id";
	public static final String GET_EMPLOYEES_BY_BAND = "Fetch employees by band";
	public static final String ADD_AN_EMPLOYEE = "Add an employee";
	public static final String ADD_EMPLOYEES = "Add employees";
	public static final String DELETE_AN_EMPLOYEE_BY_ID = "Delete an  employee by id";
	public static final String DELETE_EMPLOYEES_BY_BAND = "Delete employees by Band";
	public static final String UPDATE_AN_EMPLOYEE = "Update experience and band of an employee by employeeid";
	public static final String ADD_ALLOWANCE_EMPLOYEES = "Add allowance to all employees";
	public static final String GET_EMPLOYEES_BY_ACCOUNTNAME = "Fetch employees by accountname";
	public static final String UPDATE_BONUS_EMPLOYEE = "update bonus to an employees";

	// pathvariables
	public static final String EMPLOYEE_ID = "employeeId";
	public static final String EMPLOYEE_BAND = "band";
	public static final String EMPLOYEE_ACCOUNT_NAME = "accountName";
	public static final String ACCOUNT_NAME = "name";
	public static final String OFFSET_LIMIT = "offset";
	public static final String LIMIT = "limit";
	public static final String EMPLOYEE_ALLOWANCE = "allowance";

	// Global exception status message
	public static final String CONFLICT_EMPLOYEE_EXIST = "Employee Already Exist";
	public static final String CONFLICT_EMPLOYEE_NOT_FOUND = "No such Employee Found";

	// ApiImplicitParams
	public static final String CONTENT_TYPE = "Content-Type";
	public static final String DATA_TYPE_STRING = "String";
	public static final String PARAM_HEADER = "header";
	public static final String USER_ID = "UserId";
	public static final String USER_MANDITORY = "User id is mandatory";

	// HttpServletResponse message
	public static final String RESPONSE_OK = "OK";
	public static final String RESPONSE_CONFLICT = "CONFLICT";
	public static final String RESPONSE_NO_CONTENT = "NO CONTENT";
}
