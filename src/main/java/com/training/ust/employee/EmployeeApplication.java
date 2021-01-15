/*Class Name  : EmployeeApplication
 *Description :  class  of Employee Application
 *Date of Creation: 28/11/2020
 */
package com.training.ust.employee;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *EmployeeApplication class of employee
 */
@SpringBootApplication

@EnableSwagger2
@Configuration
public class EmployeeApplication extends WebMvcConfigurerAdapter {
/*
 * Main method for Employee application
 */
	
	@Value("${info.app.version}")
	private String version;

	@Value("${info.app.description}")
	private String description;

	@Value("${info.app.name}")
	private String appName;
	
	public static void main(String[] args) {
		SpringApplication.run(EmployeeApplication.class, args);
	}
	
	/*
	 * Method for get Docket api
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(metaData())
				.select()
				.apis(RequestHandlerSelectors
						.basePackage("com.training.ust.employee.contoller"))
				.paths(PathSelectors.any()).build();
	}
	
	/*
	 * Method for get ApiInfo metaData
	 */
	private ApiInfo metaData() {
		return new ApiInfoBuilder().title(appName).description(description)
				.version(version)
				// .termsOfServiceUrl("Terms of service")
				// .contact(new Contact("name", "url", "email"))
				.build();
	}
	

	/*
	 * Method for addViewControllers
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/documentation/v2/api-docs",
				"/v2/api-docs").setKeepQueryParams(true);
		registry.addRedirectViewController(
				"/documentation/swagger-resources/configuration/ui",
				"/swagger-resources/configuration/ui");
		registry.addRedirectViewController(
				"/documentation/swagger-resources/configuration/security",
				"/swagger-resources/configuration/security");
		registry.addRedirectViewController("/documentation/swagger-resources",
				"/swagger-resources");
	}
	
	/*
	 * Method for addViewControllers
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/documentation/**").addResourceLocations(
				"classpath:/META-INF/resources/");
	}
	



}
