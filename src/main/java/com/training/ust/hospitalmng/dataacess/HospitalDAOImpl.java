package com.training.ust.hospitalmng.dataacess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.training.ust.hospitalmng.mapper.PatientMapper;
import com.azure.cosmos.CosmosClientBuilder;
import com.azure.cosmos.models.CosmosQueryRequestOptions;
import com.azure.cosmos.models.SqlParameter;
import com.azure.cosmos.models.SqlQuerySpec;
import com.azure.cosmos.util.CosmosPagedIterable;

import com.training.ust.hospitalmng.model.Patient;
import com.training.ust.hospitalmng.repo.DoctorRepo;
import com.training.ust.hospitalmng.sql.SQLQueries;


@Component
public class HospitalDAOImpl {
	
	@Autowired
	private PatientMapper mapper;

	@Autowired
	private DoctorRepo repo;

	@Value("${azure.cosmosdb.database}")
	private String databaseName;

	private String collectionName = "patient";

	@Autowired
	private CosmosClientBuilder cosmosClientBuilder;
	
	public List<Patient> getPatientList(String doctorId ){
		String query = SQLQueries.FETCH_ALL_WITH_LIMIT;

		SqlQuerySpec querySpec = new SqlQuerySpec();
		querySpec.setQueryText(query);
		SqlParameter doctId = new SqlParameter("@doctorId", doctorId);

		List<SqlParameter> paramList = new ArrayList<>();
		paramList.add(doctId);
	
		querySpec.setParameters(paramList);
	
		CosmosPagedIterable<Patient> patientList = cosmosClientBuilder
				.buildClient().getDatabase(databaseName)
				.getContainer(collectionName)
				.queryItems(querySpec, getQueryOptions(), Patient.class);

		List<Patient> patList = patientList.stream()
				.map(mapper::mapPatientDetail).collect(Collectors.toList());
		return patList;
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
