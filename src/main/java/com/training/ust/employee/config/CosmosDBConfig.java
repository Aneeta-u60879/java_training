/*Class Name  : CosmosDBConfig
 *Description : class for CosmosDB Configration
 *Date of Creation: 28/11/2020
 */
package com.training.ust.employee.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.azure.cosmos.CosmosClientBuilder;
import com.azure.cosmos.DirectConnectionConfig;
import com.azure.cosmos.GatewayConnectionConfig;
import com.azure.spring.data.cosmos.config.AbstractCosmosConfiguration;
import com.azure.spring.data.cosmos.repository.config.EnableReactiveCosmosRepositories;

/**
 *Class for implementing cosmosdb configurations
 */
@Configuration
@EnableReactiveCosmosRepositories(basePackages = "com.training.ust.employee")
public class CosmosDBConfig extends AbstractCosmosConfiguration {

	@Value("${azure.cosmosdb.uri}")
	private String cosmosDbUrl;

	@Value("${azure.cosmosdb.key}")
	private String cosmosDbKey;

	@Value("${azure.cosmosdb.database}")
	private String databaseName;

	/*
	 * Method to get CosmosClientBuilder 
	 */
	@Bean
	public CosmosClientBuilder getCosmosClientBuilder() {
		return new CosmosClientBuilder()
				.endpoint(cosmosDbUrl)
				.key(cosmosDbKey)
				.directMode(new DirectConnectionConfig(),
						new GatewayConnectionConfig());

	}

	/*
	 * Method to get DatabaseName 
	 */
	@Override
	protected String getDatabaseName() {
		return databaseName;
	}

}
