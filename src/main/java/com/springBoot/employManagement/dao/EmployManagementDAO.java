package com.springBoot.employManagement.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.azure.cosmos.CosmosClientBuilder;
import com.azure.cosmos.models.CosmosQueryRequestOptions;
import com.azure.cosmos.models.SqlParameter;
import com.azure.cosmos.models.SqlQuerySpec;
import com.azure.cosmos.util.CosmosPagedIterable;
import com.springBoot.employManagement.common.SQLQuerries;
import com.springBoot.employManagement.mapper.EmployManagementMapper;
import com.springBoot.employManagement.model.Employee;
import com.springBoot.employManagement.model.Model;

@Component
public class EmployManagementDAO {
	@Value("${azure.cosmosdb.database}")
	private String databaseName;

	@Autowired 
	private CosmosClientBuilder cosmosClientBuilder;

	@Autowired
	private EmployManagementMapper mapper;

	private String collectionName = "employee_data";

	public List<Employee> getEmployeesWithLimit(String account, Integer offset,
			Integer limit) {
		String query = SQLQuerries.FETCH_ALL_WITH_LIMIT;

		SqlQuerySpec querySpec = new SqlQuerySpec();
		querySpec.setQueryText(query);
		SqlParameter accountName = new SqlParameter("@account", account);
		SqlParameter offsetParam = new SqlParameter("@offset", offset);
		SqlParameter limitParam = new SqlParameter("@limit", limit);
		List<SqlParameter> paramList = new ArrayList<>();
		paramList.add(accountName);
		paramList.add(offsetParam);
		paramList.add(limitParam);
		querySpec.setParameters(paramList);

		CosmosPagedIterable<Employee> employeeList = cosmosClientBuilder
				.buildClient().getDatabase(databaseName)
				.getContainer(collectionName)
				.queryItems(querySpec, getQueryOptions(), Employee.class);

		List<Employee> empList = employeeList.stream()
				.map(mapper::mapEmployeeDetail).collect(Collectors.toList());
		return empList;
	}

	public List<Model> getEmployWithLimit(String account, Integer offset,
			Integer limit) {
		String query = SQLQuerries.FETCH_ID_WITH_LIMIT;

		SqlQuerySpec querySpec = new SqlQuerySpec();
		querySpec.setQueryText(query);
		SqlParameter accountName = new SqlParameter("@account", account);
		SqlParameter offsetParam = new SqlParameter("@offset", offset);
		SqlParameter limitParam = new SqlParameter("@limit", limit);
		List<SqlParameter> paramList = new ArrayList<>();
		paramList.add(accountName);
		paramList.add(offsetParam);
		paramList.add(limitParam);
		querySpec.setParameters(paramList);

		CosmosPagedIterable<Model> employeeList = cosmosClientBuilder
				.buildClient().getDatabase(databaseName)
				.getContainer(collectionName)
				.queryItems(querySpec, getQueryOptions(), Model.class);

		List<Model> empList = employeeList.stream().collect(Collectors.toList());
		return empList;
	}

	private CosmosQueryRequestOptions getQueryOptions() {
		CosmosQueryRequestOptions options = new CosmosQueryRequestOptions();
		options.setQueryMetricsEnabled(Boolean.FALSE);
		return options;
	}
}
