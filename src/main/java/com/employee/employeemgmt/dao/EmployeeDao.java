package com.employee.employeemgmt.dao;
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
import com.employee.employeemgmt.common.SQLQuries;
import com.employee.employeemgmt.mapper.EmployeeMapper;
import com.employee.employeemgmt.model.EmployModel;
import com.employee.employeemgmt.model.ByAccountModel;

/**
 * serivice that performs the custom query
 * @author 144895
 *
 */
@Component
public class EmployeeDao {
	
	@Value("${azure.cosmosdb.database}")
	private String databaseName;

	@Autowired
	private CosmosClientBuilder cosmosClientBuilder;
	
	@Autowired
	private EmployeeMapper  mapper;
	
	private String collectionName = "employeeDetails";
	
	/**
	 * To get list of employee of particular account
	 * @param account
	 * @return List of employee of a particular account
	 */
	public List<ByAccountModel> getEmloyList(String account){
		String query = SQLQuries.FETCH_ALL_WITH_LIMIT;
		
		SqlQuerySpec querySpec = new SqlQuerySpec();
		querySpec.setQueryText(query);
		SqlParameter accountName = new SqlParameter("@account", account);
		List<SqlParameter> paramList = new ArrayList<>();
		paramList.add(accountName);
		querySpec.setParameters(paramList);
		
		CosmosPagedIterable<ByAccountModel> employeeList = cosmosClientBuilder
				.buildClient().getDatabase(databaseName)
				.getContainer(collectionName)
				.queryItems(querySpec, getQueryOptions(), ByAccountModel.class);
		
		System.out.println(employeeList);

		List<ByAccountModel> empList = employeeList.stream().collect(Collectors.toList());
		
		System.out.println(empList);
		return empList;
		
	}
	
	private CosmosQueryRequestOptions getQueryOptions() {
		CosmosQueryRequestOptions options = new CosmosQueryRequestOptions();
		options.setQueryMetricsEnabled(Boolean.FALSE);
		return options;
	}


}
