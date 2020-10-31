package com.employee.employeemgmt.repo;

import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import com.employee.employeemgmt.model.EmployModel;

/**
 * Connection to the Db 
 * @author 144895
 *
 */
@Repository
public interface EmployeeMgmtRepo extends ReactiveCosmosRepository<EmployModel , String> {

	/**
	 * employee of particular employeId
	 * @param employeeId
	 * @return Mono<EmployModel> of the given id
	 */
	Mono<EmployModel> findByEmployeeId(String employeeId);

	/**
	 * List of employee of particular account name
	 * @param accountName
	 * @return  Flux<EmployModel> of particular account
	 */
	Flux<EmployModel> findByAccountName(String accountName);

}
