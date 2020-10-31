package com.example.employeemanagement.dao;

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
import com.example.employeemanagement.common.SQLQueries;
import com.example.employeemanagement.mapper.EmployeeMapper;
import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.model.EmployeeNames;

/**
 * EmployeeDao for EmployeeManagementClass
 * 
 * @author 144892
 *
 */
@Component
public class EmployeeDAO {

	/*
	 * @Value used for assigning default database name
	 */
	@Value("${azure.cosmosdb.database}")
	private String databaseName;

	/*
	 * CosmosClientBuilder Dependency Injected using @Autowired
	 */
	@Autowired
	private CosmosClientBuilder cosmosClientBuilder;

	/*
	 * EmployeeMapper Dependency Injected using @Autowired
	 */
	@Autowired
	private EmployeeMapper mapper;

	/*
	 * collectionName = CosmosDB containerName(here it is "details_employee)
	 */
	private String collectionName = "details_employee";

	/*
	 * Custom Query Method to fetch employees from an account according to given
	 * range from the list
	 * 
	 * @param String account
	 * 
	 * @param String offset
	 * 
	 * @param String limit
	 * 
	 * @return List<Employee>
	 */
	public List<Employee> getEmployeesWithLimit(String account, Integer offset,
			Integer limit) {
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

		CosmosPagedIterable<Employee> employeeList = cosmosClientBuilder
				.buildClient().getDatabase(databaseName)
				.getContainer(collectionName)
				.queryItems(querySpec, getQueryOptions(), Employee.class);

		List<Employee> empList = employeeList.stream()
				.map(mapper::mapEmployeeDetail).collect(Collectors.toList());
		return empList;
	}

	/*
	 * Custom Query Method to fetch employee id, name and joiningDate from a
	 * specific band from the list
	 * 
	 * @param String band
	 * 
	 * @return List<EmployeeNames>
	 */
	public List<EmployeeNames> getEmployeeByBand(String band) {
		SqlQuerySpec querySpec = new SqlQuerySpec();
		String query = "select details_employee.empId, details_employee.empName, details_employee.joiningDate from details_employee where "
				+ "details_employee.band = @band";
		querySpec.setQueryText(query);
		List<SqlParameter> paramList = new ArrayList<>();
		SqlParameter bandName = new SqlParameter("@band", band);
		paramList.add(bandName);
		querySpec.setParameters(paramList);

		CosmosPagedIterable<EmployeeNames> employeeList = cosmosClientBuilder
				.buildClient().getDatabase(databaseName)
				.getContainer(collectionName)
				.queryItems(querySpec, getQueryOptions(), EmployeeNames.class);

		List<EmployeeNames> empList = employeeList.stream().collect(
				Collectors.toList());
		return empList;
	}

	/*
	 * Method to get Query Options
	 */
	private CosmosQueryRequestOptions getQueryOptions() {
		CosmosQueryRequestOptions options = new CosmosQueryRequestOptions();
		options.setQueryMetricsEnabled(Boolean.FALSE);
		return options;
	}
}
