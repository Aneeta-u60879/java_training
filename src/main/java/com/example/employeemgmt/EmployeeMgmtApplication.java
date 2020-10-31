package com.example.employeemgmt;

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
 * Main class for starting Employeemgmt Service.
 * 
 * @author 144900
 *
 */
@SpringBootApplication
@EnableSwagger2
@Configuration
public class EmployeeMgmtApplication extends WebMvcConfigurerAdapter {

	@Value("${info.app.version}")
	private String version;

	@Value("${info.app.description}")
	private String description;

	@Value("${info.app.name}")
	private String appName;

	public static void main(String[] args) {
		SpringApplication.run(EmployeeMgmtApplication.class, args);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(metaData())
				.select()
				.apis(RequestHandlerSelectors
						.basePackage("com.example.employeemgmt.controller"))
				.paths(PathSelectors.any()).build();
	}

	private ApiInfo metaData() {
		return new ApiInfoBuilder().title(appName).description(description)
				.version(version).build();
	}

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

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/documentation/**").addResourceLocations(
				"classpath:/META-INF/resources/");
	}
}
