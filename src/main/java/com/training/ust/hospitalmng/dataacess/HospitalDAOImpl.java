/*Class Name  : HospitalDAOImpl
 *Description : class for HospitalDAOImpl  Implementation
 *Date of Creation: 8/01/2021
 */
package com.training.ust.hospitalmng.dataacess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.training.ust.hospitalmng.mapper.BookingDetailsMapper;
import com.training.ust.hospitalmng.mapper.DoctorMapper;
import com.training.ust.hospitalmng.mapper.PatientMapper;
import com.azure.cosmos.CosmosClientBuilder;
import com.azure.cosmos.models.CosmosQueryRequestOptions;
import com.azure.cosmos.models.SqlParameter;
import com.azure.cosmos.models.SqlQuerySpec;
import com.azure.cosmos.util.CosmosPagedIterable;
import com.training.ust.hospitalmng.model.BookingDetails;
import com.training.ust.hospitalmng.model.Doctor;
import com.training.ust.hospitalmng.model.Patient;
import com.training.ust.hospitalmng.repo.DoctorRepo;
import com.training.ust.hospitalmng.sql.SQLQueries;

/**
 * class for HospitalDAOImpl Implementation
 */

@Component
public class HospitalDAOImpl {

	@Autowired
	private PatientMapper mapper;

	@Autowired
	private DoctorMapper mapper1;

	@Autowired
	private BookingDetailsMapper mapper2;

	@Value("${azure.cosmosdb.database}")
	private String databaseName;

	private String collectionName = "patient";

	@Autowired
	private CosmosClientBuilder cosmosClientBuilder;

	/**
	 * Method for get Patient List by doctorId
	 */
	public List<Patient> getPatientList(String doctorId) {
		String query = SQLQueries.FETCH_ALL_WITH_LIMIT;

		SqlQuerySpec querySpec = new SqlQuerySpec();
		querySpec.setQueryText(query);
		SqlParameter doctId = new SqlParameter("@doctorId", doctorId);

		List<SqlParameter> paramList = new ArrayList<>();
		paramList.add(doctId);

		querySpec.setParameters(paramList);

		CosmosPagedIterable<Patient> patientList = cosmosClientBuilder.buildClient().getDatabase(databaseName)
				.getContainer(collectionName).queryItems(querySpec, getQueryOptions(), Patient.class);

		List<Patient> patList = patientList.stream().map(mapper::mapPatientDetail).collect(Collectors.toList());
		return patList;
	}

	/**
	 * Method for get Doctor details by department
	 */
	public List<Doctor> getDoctorDetails(String dept) {
		String query = SQLQueries.FETCH_ALL_DOCTORS_DEPT;

		SqlQuerySpec querySpec = new SqlQuerySpec();
		querySpec.setQueryText(query);
		SqlParameter department = new SqlParameter("@dept", dept);

		List<SqlParameter> paramList = new ArrayList<>();
		paramList.add(department);

		querySpec.setParameters(paramList);

		CosmosPagedIterable<Doctor> doctorList = cosmosClientBuilder.buildClient().getDatabase(databaseName)
				.getContainer(collectionName).queryItems(querySpec, getQueryOptions(), Doctor.class);

		List<Doctor> docList = doctorList.stream().map(mapper1::mapDoctorDetail).collect(Collectors.toList());
		return docList;
	}

	/**
	 * Method for get Patient Report
	 */

	public List<BookingDetails> getPatientReport() {
		String query = SQLQueries.FETCH_ALL_DOCTORS_DEPT;

		SqlQuerySpec querySpec = new SqlQuerySpec();
		querySpec.setQueryText(query);

		CosmosPagedIterable<BookingDetails> patientList = cosmosClientBuilder.buildClient().getDatabase(databaseName)
				.getContainer(collectionName).queryItems(querySpec, getQueryOptions(), BookingDetails.class);

		List<BookingDetails> patList = patientList.stream().map(mapper2::mapBookingDetails)
				.collect(Collectors.toList());
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
