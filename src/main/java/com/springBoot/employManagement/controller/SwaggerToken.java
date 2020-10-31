package com.springBoot.employManagement.controller;

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

@Inherited
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ApiImplicitParams({
		@ApiImplicitParam(name = "Content-Type", value = "Content type", dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "UserId", value = "User id is mandatory", dataType = "String", paramType = "header") })
@ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK") })
public @interface SwaggerToken {

}
