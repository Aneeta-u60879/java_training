package com.example.employeemanagement.repo;

import org.springframework.stereotype.Repository;

import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import com.example.employeemanagement.model.Employee;

/**
 * EmployeeRepo Interface for EmployeeManagementClass
 * 
 * @author 144892
 *
 * extending ReactiveCosmosRepository with Partition Key of type String
 */
@Repository
public interface IEmployeeManagementRepo extends
		ReactiveCosmosRepository<Employee, String> {

}
