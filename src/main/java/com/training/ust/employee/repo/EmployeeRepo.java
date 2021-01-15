/*Class Name  : EmployeeRepo
 *Description : EmployeeRepo  of employee class 
 *Date of Creation: 28/11/2020
 */
package com.training.ust.employee.repo;

import org.springframework.stereotype.Repository;

import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import com.training.ust.employee.model.Employee;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
/**
 * Repository class of employee
 * @author USER
 *
 */
@Repository
public interface EmployeeRepo extends
		ReactiveCosmosRepository<Employee, String> {

/*
 * Methods for access field in employee Repository
 */
    Flux<Employee> findByAccountName(String accountName);

	Flux<Employee> findByBand(String band);



}
