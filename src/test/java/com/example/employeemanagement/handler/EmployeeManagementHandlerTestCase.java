package com.example.employeemanagement.handler;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.model.EmployeeNames;
import com.example.employeemanagement.bo.EmployeeBO;
import com.example.employeemanagement.bo.UpdateEmployeeBO;
import com.example.employeemanagement.dao.EmployeeDAO;
import com.example.employeemanagement.exception.EmployeeManagementException;
import com.example.employeemanagement.handler.impl.EmployeeManagementHandlerImpl;
import com.example.employeemanagement.repo.IEmployeeManagementRepo;

import junit.framework.TestCase;

/**
 * Test Class for Employee Handler
 * 
 * @author 144892
 *
 */
@RunWith(SpringRunner.class)
public class EmployeeManagementHandlerTestCase extends TestCase {
	
	/*
	 * Inject Mock of EmployeeManagementHandlerImpl
	 */
	@InjectMocks
	private EmployeeManagementHandlerImpl empHandler;

	/*
	 * Mocking EmployeeManagementHandlerImpl
	 */
	@Mock
	private IEmployeeManagementRepo empRepo;


	/*
	 * Mocking EmployeeDao
	 */
	@Mock
	private EmployeeDAO empDao;

	/*
	 * Creating Mock Employee, EmployeeBO, UpdateEmployeeBO and
	 * EmployeeNames objects
	 */
	Employee employee1 = new Employee(100, "Thomas", "2016-2-15", "walmart",
			50000.0, 4.0, "A");
	Employee employee2 = new Employee(101, "Neena", "2019-12-20", "anthem",
			20000.0, 1.0, "B");
	EmployeeBO employee3 = new EmployeeBO(100, "Thomas", "2016-2-15",
			"walmart", 50000.0, 4.0, "A");
	EmployeeBO employee4 = new EmployeeBO(101, "Neena", "2019-12-20", "anthem",
			20000.0, 1.0, "B");
	UpdateEmployeeBO updateEmployee = new UpdateEmployeeBO(100, 500.0);
	EmployeeNames employee = new EmployeeNames(100, "Thomas", "2016-2-15");
	List<EmployeeBO> employeeListBO = new ArrayList<EmployeeBO>();

	/*
	 * Mocking EmployeeManagementHandlerImpl
	 */
	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	/*
	 * Test for getEmployeeList method
	 */
	@Test
	public void testgetEmployeeList() {
		Flux<Employee> employeeDetailsList = Flux.just(employee1);
		Mockito.when(empRepo.findAll()).thenReturn(employeeDetailsList);
		List<EmployeeBO> response = empHandler.getEmployeeList();
		Assert.assertEquals("Thomas", response.get(0).getEmpName());
	}

	/*
	 * Test for getEmployeeList method if list is empty case
	 */
	@Test(expected = EmployeeManagementException.class)
	public void testEmployeeListEmpty() {
		Flux<Employee> employeeDetailsList = Flux.just();
		Mockito.when(empRepo.findAll()).thenReturn(employeeDetailsList);
		empHandler.getEmployeeList();
	}

	/*
	 * Test for saveEmployee method
	 */
	@Test
	public void testsaveEmployee() {
		Mono<Employee> employee = Mono.just(employee1);
		Mockito.when(empRepo.save(Mockito.any())).thenReturn(employee);
		EmployeeBO response = empHandler.saveEmployee(employee3);
		Assert.assertEquals(employee.block().getEmpName(),
				response.getEmpName());
	}

	/*
	 * Test for deleteEmployee method
	 */
	@Test
	public void testdeleteEmployee() {
		Mono<Employee> employee = Mono.just(employee1);
		Mockito.when(empRepo.findById(Mockito.any())).thenReturn(employee);
		Mockito.when(empRepo.delete(Mockito.any())).thenReturn(Mono.empty());
		EmployeeBO response = empHandler.deleteEmployee("100");
		Assert.assertEquals(employee.block().getEmpId(), response.getEmpId());
	}

	/*
	 * Test for deleteEmployeeNotFound method if employee not found
	 */
	@Test(expected = EmployeeManagementException.class)
	public void testdeleteEmployeeNotFound() {
		Mockito.when(empRepo.findById(Mockito.any())).thenReturn(Mono.empty());
		empHandler.deleteEmployee(null);
	}

	/*
	 * Test for getEmployeesFromBand method
	 */
	@Test
	public void testgetEmployeesFromBand() {
		Flux<Employee> employeeDetailsList = Flux.just(employee1);
		Mockito.when(empRepo.findAll()).thenReturn(employeeDetailsList);
		List<EmployeeBO> response = empHandler.getEmployeesFromBand("A");
		Assert.assertEquals("A", response.get(0).getBand());
	}

	/*
	 * Test for getEmployeeFromBandListEmpty method if list is empty case
	 */
	@Test(expected = EmployeeManagementException.class)
	public void testgetEmployeeFromBandListEmpty() {
		Flux<Employee> employeeDetailsList = Flux.just();
		Mockito.when(empRepo.findAll()).thenReturn(employeeDetailsList);
		empHandler.getEmployeesFromBand("A");
	}

	/*
	 * Test for getByAcc method
	 */
	@Test
	public void testgetByAcc() {
		Flux<Employee> employeeDetailsList = Flux.just(employee1);
		Mockito.when(empRepo.findAll()).thenReturn(employeeDetailsList);
		List<EmployeeBO> response = empHandler.getByAcc("walmart");
		Assert.assertEquals("walmart", response.get(0).getAccName());
	}

	/*
	 * Test for getByAccListEmpty method if list is empty case
	 */
	@Test(expected = EmployeeManagementException.class)
	public void testgetByAccListEmpty() {
		Flux<Employee> employeeDetailsList = Flux.just();
		Mockito.when(empRepo.findAll()).thenReturn(employeeDetailsList);
		empHandler.getByAcc("walmart");
	}

	/*
	 * Test for deleteByAcc method
	 */
	@Test
	public void testdeleteByAcc() {
		Flux<Employee> employeeDetailsList = Flux.just(employee1);
		Mockito.when(empRepo.findAll()).thenReturn(employeeDetailsList);
		Mockito.when(empRepo.delete(employee1)).thenReturn(Mono.empty());
		List<EmployeeBO> response = empHandler.deleteByAcc("walmart", "A");
		Assert.assertEquals("Thomas", response.get(0).getEmpName());
	}

	/*
	 * Test for deleteByAccListEmpty method if list is empty case
	 */
	@Test(expected = EmployeeManagementException.class)
	public void testdeleteByAccListEmpty() {
		Flux<Employee> employeeDetailsList = Flux.just();
		Mockito.when(empRepo.findAll()).thenReturn(employeeDetailsList);
		empHandler.deleteByAcc("walmart", "A");
	}

	/*
	 * Test for updateEmployee method
	 */
	@Test
	public void testupdateEmployee() {
		Mono<Employee> employee = Mono.just(employee1);
		Mockito.when(empRepo.findById(Mockito.any())).thenReturn(employee);
		Mockito.when(empRepo.save(Mockito.any())).thenReturn(employee);
		EmployeeBO response = empHandler.updateEmployee(employee3);
		Assert.assertEquals(employee.block().getBand(), response.getBand());
	}

	/*
	 * Test for updateEmployee method if employee not found
	 */
	@Test(expected = EmployeeManagementException.class)
	public void testupdateEmployeeNotFound() {
		Mockito.when(empRepo.findById(Mockito.any())).thenReturn(Mono.empty());
		empHandler.updateEmployee(employee3);
	}

	/*
	 * Test for addBonus method
	 */
	@Test
	public void testaddBonus() {
		employee1.setSalary(employee1.getSalary() + 500.0);
		Mono<Employee> employee = Mono.just(employee1);
		Mockito.when(empRepo.findById(Mockito.any())).thenReturn(employee);
		Mockito.when(empRepo.save(Mockito.any())).thenReturn(employee);
		EmployeeBO response = empHandler.addBonus(updateEmployee);
		Assert.assertEquals(employee.block().getSalary(), response.getSalary());
	}

	/*
	 * Test for adddBonus method if employee not found
	 */
	@Test(expected = EmployeeManagementException.class)
	public void testaddBonusNotFound() {
		Mockito.when(empRepo.findById(Mockito.any())).thenReturn(Mono.empty());
		empHandler.addBonus(updateEmployee);
	}

	/*
	 * Test for addAllowance method
	 */
	@Test
	public void testaddAllowance() {
		Double allowances = (employee1.getSalary() * 12) / 100.0;
		employee1.setSalary(employee1.getSalary() + allowances);
		Flux<Employee> employeeDetailsList = Flux.just(employee1);
		Mockito.when(empRepo.findAll()).thenReturn(employeeDetailsList);
		Mockito.when(empRepo.save(Mockito.any())).thenReturn(
				Mono.just(employee1));
		List<EmployeeBO> response = empHandler.addAllowance(12.0);
		Assert.assertEquals("Thomas", response.get(0).getEmpName());
	}

	/*
	 * Test for addAllowance method if list is empty case
	 */
	@Test(expected = EmployeeManagementException.class)
	public void testaddAllowanceListEmpty() {
		Flux<Employee> employeeDetailsList = Flux.just();
		Mockito.when(empRepo.findAll()).thenReturn(employeeDetailsList);
		empHandler.addAllowance(12.0);
	}

	/*
	 * Test for addNewEmployees method
	 */
	@Test
	public void testaddNewEmployees() {
		employeeListBO.add(employee3);
		employeeListBO.add(employee4);
		Flux<Employee> employeeDetailsList = Flux.just(employee1, employee2);
		Mockito.when(empRepo.saveAll(Mockito.anyCollection())).thenReturn(
				employeeDetailsList);
		List<EmployeeBO> response = empHandler.addNewEmployees(employeeListBO);
		Assert.assertEquals("Thomas", response.get(0).getEmpName());
	}

	/*
	 * Test for getEmployeeFromBandList method if list is empty case
	 */
	@Test(expected = EmployeeManagementException.class)
	public void testaddNewEmployeesListEmpty() {
		Flux<Employee> employeeDetailsList = Flux.just();
		Mockito.when(empRepo.findAll()).thenReturn(employeeDetailsList);
		empHandler.addAllowance(12.0);
	}

	/*
	 * Test for getEmpByNameQuery method
	 */
	@Test
	public void testgetEmpByNameQuery() {
		List<Employee> employeeList = new ArrayList<Employee>();
		employeeList.add(employee1);
		Mockito.when(
				empDao.getEmployeesWithLimit(Mockito.any(), Mockito.any(),
						Mockito.any())).thenReturn(employeeList);
		List<Employee> response = empHandler.getEmpByNameQuery("walmart", 0, 1);
		Assert.assertEquals(employeeList, response);
	}

	/*
	 * Test for getEmpByNameByBand method
	 */
	@Test
	public void testgetEmpByNameByBand() {
		List<EmployeeNames> employeeList = new ArrayList<EmployeeNames>();
		employeeList.add(employee);
		Mockito.when(empDao.getEmployeeByBand("A")).thenReturn(employeeList);
		List<EmployeeNames> response = empHandler.getEmpByNameByBand("A");
		Assert.assertEquals(employeeList, response);
	}
}
