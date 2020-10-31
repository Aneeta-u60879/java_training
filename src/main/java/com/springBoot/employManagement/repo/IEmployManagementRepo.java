package com.springBoot.employManagement.repo;

import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;

import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import com.springBoot.employManagement.model.Employee;

/**
 * Repository for CRUD operations
 * 
 * @author 144785
 *
 */
@Repository
public interface IEmployManagementRepo extends
		ReactiveCosmosRepository<Employee, String> {

	/**
	 * Fetch employee by account name
	 * 
	 * @param accountName
	 * @return Flux<Employee>
	 */
	public Flux<Employee> getEmpByAccountName(String accountName);

	/**
	 * Fetch employee b band
	 * 
	 * @param band
	 * @return Flux<Employee>
	 */
	public Flux<Employee> getEmpByBand(String band);

	/**
	 * Fetch employee by employId
	 * 
	 * @param employId
	 * @return Flux<Employee>
	 */
	public Flux<Employee> getEmpByEmployId(Integer employId);

	/**
	 * fetch employee by accountName and band
	 * 
	 * @param accountName
	 * @param band
	 * @return Flux<Employee>
	 */
	public Flux<Employee> getEmpByAccountNameAndBand(String accountName,
			String band);
}
