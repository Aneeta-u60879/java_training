/*Class Name  : EmployeeDAOImpl
 *Description : class for EmployeeDAOI  Implementation
 *Date of Creation: 28/11/2020
 */
package com.training.ust.employee.dataacess;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.azure.cosmos.CosmosClientBuilder;
import com.azure.cosmos.models.CosmosQueryRequestOptions;
import com.azure.cosmos.models.SqlParameter;
import com.azure.cosmos.models.SqlQuerySpec;
import com.azure.cosmos.util.CosmosPagedIterable;
import com.training.ust.employee.sql.SQLQueries;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.training.ust.employee.mapper.EmployeeMapper;
import com.training.ust.employee.model.Employee;
import com.training.ust.employee.repo.EmployeeRepo;

/**
 * class for EmployeeDAOI Implementation
 */

@Component
public class EmployeeDAOImpl implements EmployeeDAO {



	@Autowired
	private EmployeeRepo repo;

	@Value("${azure.cosmosdb.database}")
	private String databaseName;

	@Autowired
	private EmployeeMapper mapper;

	private String collectionName = "employee";

	@Autowired
	private CosmosClientBuilder cosmosClientBuilder;
	
	

/**
 * Method for get Employees With Limit
 */
	public List<Employee> getEmployeesWithLimit(String accountName,Integer offset,Integer limit ){
		String query = SQLQueries.FETCH_ALL_WITH_LIMIT;

		SqlQuerySpec querySpec = new SqlQuerySpec();
		querySpec.setQueryText(query);
		SqlParameter accout = new SqlParameter("@accountName", accountName);
		SqlParameter offsetParam = new SqlParameter("@offset", offset);
		SqlParameter limit1 = new SqlParameter("@limit", limit);
		
		
		
		List<SqlParameter> paramList = new ArrayList<>();
		paramList.add(accout);
		paramList.add(offsetParam);
		
		paramList.add(limit1);
	
		querySpec.setParameters(paramList);
		
//		String queryNew = "select * from employee";
//
//		 CosmosPagedIterable<Employee> employeeList = cosmosClientBuilder
//		 .buildClient().getDatabase(databaseName)
//		 .getContainer(collectionName)
//		 .queryItems(queryNew, getQueryOptions(), Employee.class);

		CosmosPagedIterable<Employee> employeeList = cosmosClientBuilder
				.buildClient().getDatabase(databaseName)
				.getContainer(collectionName)
				.queryItems(querySpec, getQueryOptions(), Employee.class);

		List<Employee> empList = employeeList.stream()
				.map(mapper::mapEmployeeDetail).collect(Collectors.toList());
		return empList;
	}

	
	/**
	 * Method for CosmosQueryRequestOptions
	 */

	private CosmosQueryRequestOptions getQueryOptions() {
		CosmosQueryRequestOptions options = new CosmosQueryRequestOptions();
		options.setQueryMetricsEnabled(Boolean.FALSE);
		return options;
	}

}
