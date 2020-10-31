package com.example.employeemgmt.controller.annotation;

import static com.example.employeemgmt.common.EmployeeMgmtConstants.CONTENT_TYPE;
import static com.example.employeemgmt.common.EmployeeMgmtConstants.DATA_TYPE_STRING;
import static com.example.employeemgmt.common.EmployeeMgmtConstants.PARAM_HEADER;
import static com.example.employeemgmt.common.EmployeeMgmtConstants.RESPONSE_CONFLICT;
import static com.example.employeemgmt.common.EmployeeMgmtConstants.RESPONSE_NO_CONTENT;
import static com.example.employeemgmt.common.EmployeeMgmtConstants.RESPONSE_OK;
import static com.example.employeemgmt.common.EmployeeMgmtConstants.USER_ID;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.servlet.http.HttpServletResponse;

import com.example.employeemgmt.common.EmployeeMgmtConstants;

@Inherited
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ApiImplicitParams({
		@ApiImplicitParam(name = CONTENT_TYPE, value = CONTENT_TYPE, dataType = DATA_TYPE_STRING, paramType = PARAM_HEADER),
		@ApiImplicitParam(name = USER_ID, value = EmployeeMgmtConstants.USER_MANDITORY, dataType = DATA_TYPE_STRING, paramType = PARAM_HEADER) })
@ApiResponses(value = {
		@ApiResponse(code = HttpServletResponse.SC_OK, message = RESPONSE_OK),
		@ApiResponse(code = HttpServletResponse.SC_CONFLICT, message = RESPONSE_CONFLICT),
		@ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = RESPONSE_NO_CONTENT) })
public @interface SwaggerToken {

}
