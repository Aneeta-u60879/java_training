package com.eleena.Employee.dao;

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
import com.eleena.Employee.common.SQLQueries;
import com.eleena.Employee.mapper.EmployeeMapper;
import com.eleena.Employee.model.EmployeeMngmnt;
import com.eleena.Employee.model.EmployeeQueryModel;

@Component
public class EmployeeDAO {
	@Value("${azure.cosmosdb.database}")
	private String databaseName;

	@Autowired
	private CosmosClientBuilder cosmosClientBuilder;

	@Autowired
	private EmployeeMapper mapper;

	private String collectionName = "employeeMgmt";

	public List<EmployeeMngmnt> getEmployeesWithLimit(String account,
			Integer offset, Integer limit) {
		String query = SQLQueries.FETCH_ALL_WITH_LIMIT;

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

		CosmosPagedIterable<EmployeeMngmnt> employeeList = cosmosClientBuilder
				.buildClient().getDatabase(databaseName)
				.getContainer(collectionName)
				.queryItems(querySpec, getQueryOptions(), EmployeeMngmnt.class);

		List<EmployeeMngmnt> empList = employeeList.stream()
				.map(mapper::mapEmployeeDetail).collect(Collectors.toList());
		return empList;
	}

	public List<EmployeeQueryModel> getEmployeesWithoutLimit(String account) {
		SqlQuerySpec querySpec = new SqlQuerySpec();
		String queryNew = "select emp.id ,emp.name"
				+ " from employeeMgmt emp where emp.accountName = @account";
		querySpec.setQueryText(queryNew);
		List<SqlParameter> paramList = new ArrayList<>();
		SqlParameter accountName = new SqlParameter("@account", account);
		paramList.add(accountName);
		querySpec.setParameters(paramList);

		CosmosPagedIterable<EmployeeQueryModel> employeeList = cosmosClientBuilder
				.buildClient().getDatabase(databaseName)
				.getContainer(collectionName)
				.queryItems(querySpec, getQueryOptions(), EmployeeQueryModel.class);

		List<EmployeeQueryModel> empList = employeeList.stream().collect(
				Collectors.toList());
		return empList;

	}

	private CosmosQueryRequestOptions getQueryOptions() {
		CosmosQueryRequestOptions options = new CosmosQueryRequestOptions();
		options.setQueryMetricsEnabled(Boolean.FALSE);
		return options;
	}
}
