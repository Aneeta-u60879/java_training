package com.neena.employee.repo;

import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;

import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import com.neena.employee.model.Employee;

@Repository
public interface IEmployeeMangmntRepo extends ReactiveCosmosRepository<Employee, String> {

	Flux<Employee> findByAccountName(String accountName);
	
//	Mono<Employee> dltEmploy(String id);

	
	
	
	
}
