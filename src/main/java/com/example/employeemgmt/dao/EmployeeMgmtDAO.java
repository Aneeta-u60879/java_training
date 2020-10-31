package com.example.employeemgmt.dao;

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
import com.example.employeemgmt.common.SQLQueries;
import com.example.employeemgmt.mapper.EmployeeMgmtMapper;
import com.example.employeemgmt.model.Employee;

/**
 * Dao class for employeeMgmt.
 * 
 * @author 144900
 *
 */
@Component
public class EmployeeMgmtDAO {

	@Value("${azure.cosmosdb.database}")
	private String databaseName;

	@Autowired
	private CosmosClientBuilder cosmosClientBuilder;

	@Autowired
	private EmployeeMgmtMapper mapper;

	private String containerName = "employeeMgmt";

	/**
	 * Method for fetching employees from DB using account name within in a
	 * limit.
	 * 
	 * @param account
	 * @param offset
	 * @param limit
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
				.getContainer(containerName)
				.queryItems(querySpec, getQueryOptions(), Employee.class);

		List<Employee> empList = employeeList.stream()
				.map(mapper::mapEmployeeDetail).collect(Collectors.toList());
		return empList;
	}

	/**
	 * Method configuring query options.
	 * 
	 * @return CosmosQueryRequestOptions
	 */
	private CosmosQueryRequestOptions getQueryOptions() {
		CosmosQueryRequestOptions options = new CosmosQueryRequestOptions();
		options.setQueryMetricsEnabled(Boolean.FALSE);
		return options;
	}

	/**
	 * Method for fetching an employee from DB using employeeId.
	 * 
	 * @param employeeId
	 * @return Employee
	 */
	public Employee getEmployeesWithId(String employeeId) {
		String query = SQLQueries.FETCH_EMPLOYEE_WITH_ID;
		SqlQuerySpec querySpec = new SqlQuerySpec();
		querySpec.setQueryText(query);
		SqlParameter empId = new SqlParameter("@employeeId", employeeId);
		List<SqlParameter> paramList = new ArrayList<>();
		paramList.add(empId);
		querySpec.setParameters(paramList);

		CosmosPagedIterable<Employee> employeeList = cosmosClientBuilder
				.buildClient().getDatabase(databaseName)
				.getContainer(containerName)
				.queryItems(querySpec, getQueryOptions(), Employee.class);

		Employee emp = employeeList.stream().map(mapper::mapEmployeeDetail)
				.findAny().orElse(null);
		return emp;
	}

}
