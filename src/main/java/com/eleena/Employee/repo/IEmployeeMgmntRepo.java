package com.eleena.Employee.repo;
import java.util.List;

import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import com.eleena.Employee.bo.EmployeeMngmntBo;
import com.eleena.Employee.model.EmployeeMngmnt;
//@Repository
public interface IEmployeeMgmntRepo  
extends
//string is type of partition key
ReactiveCosmosRepository<EmployeeMngmnt, String>  
{
//	public List<EmployeeMngmntBo> fetchEmployeeList();
	Flux<EmployeeMngmnt> findByAccountName(String name);

	Mono<EmployeeMngmnt> save(EmployeeMngmnt emp);

	Mono<EmployeeMngmnt> findById(Integer employeeId);
	
}
