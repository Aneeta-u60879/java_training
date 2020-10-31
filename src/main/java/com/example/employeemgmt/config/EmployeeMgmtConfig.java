package com.example.employeemgmt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.azure.cosmos.CosmosClientBuilder;
import com.azure.cosmos.DirectConnectionConfig;
import com.azure.cosmos.GatewayConnectionConfig;
import com.azure.spring.data.cosmos.config.AbstractCosmosConfiguration;
import com.azure.spring.data.cosmos.repository.config.EnableReactiveCosmosRepositories;

/**
 * Configuration Class for EmployeeMgmt
 * 
 * @author 144900
 *
 */
@Configuration
@EnableReactiveCosmosRepositories(basePackages = "com.example.employeemgmt")
public class EmployeeMgmtConfig extends AbstractCosmosConfiguration {

	@Value("${azure.cosmosdb.uri}")
	private String cosmosDbUrl;

	@Value("${azure.cosmosdb.key}")
	private String cosmosDbKey;

	@Value("${azure.cosmosdb.database}")
	private String databaseName;

	/**
	 * Method for Configuring Cosmos DB connection.
	 * 
	 * @return CosmosClientBuilder
	 */
	@Bean
	public CosmosClientBuilder getCosmosClientBuilder() {
		return new CosmosClientBuilder()
				.endpoint(cosmosDbUrl)
				.key(cosmosDbKey)
				.directMode(new DirectConnectionConfig(),
						new GatewayConnectionConfig());

	}

	@Override
	protected String getDatabaseName() {
		return databaseName;
	}
}
