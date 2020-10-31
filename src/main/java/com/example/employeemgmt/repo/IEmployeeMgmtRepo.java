package com.example.employeemgmt.repo;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import com.example.employeemgmt.model.Employee;

/**
 * Interface EmployeeMgmt Repo.
 * 
 * @author 144900
 *
 */
@Repository
public interface IEmployeeMgmtRepo extends
		ReactiveCosmosRepository<Employee, String> {

	/**
	 * Function for fetch employees using band from DB.
	 * 
	 * @param band
	 * @return Flux<Employee>
	 */
	Flux<Employee> getEmpByBand(String band);

	/**
	 * Function for fetch employees using AccountName from DB.
	 * 
	 * @param accountName
	 * @return Flux<Employee>
	 */
	Flux<Employee> getEmpByAccountName(String accountName);

	/**
	 * Function for fetch employees using AccountName and band from DB.
	 * 
	 * @param accountName
	 * @param band
	 * @return Flux<Employee>
	 */
	Flux<Employee> getEmpByAccountNameAndBand(String accountName, String band);

}
