package com.neena.employee.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.neena.employee.bo.EmployeeMangmntBO;
import com.neena.employee.bo.UpdateBonusBo;
import com.neena.employee.bo.UpdateExperience;
import com.neena.employee.dao.EmployeeDao;
import com.neena.employee.exception.EmployeeManagementException;
import com.neena.employee.handler.impl.EmployeeMngmntHandler;
import com.neena.employee.model.Employee;
import com.neena.employee.model.Model;
import com.neena.employee.repo.IEmployeeMangmntRepo;

public class EmployeeMngmentHandlerTest {

	@InjectMocks
	private EmployeeMngmntHandler handler;
	@Mock
	private IEmployeeMangmntRepo repo;
	@Mock
	private EmployeeDao dao;

	EmployeeMangmntBO employee1 = new EmployeeMangmntBO("1", 100, "Eliza",
			"2011/10/10", "Walmart", 32000, 9, "C");
	EmployeeMangmntBO employee2 = new EmployeeMangmntBO("2", 102, "Erin",
			"2011/10/10", "Mobile", 32000, 6, "C");
	Employee employee3 = new Employee("3", 112, "Lena", "2011/10/10", "Mobile",
			32000, 6, "C");

	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 *  Test for fetching all employees
	 */
	@Test
	public void testGetEmployeeList() {
		Flux<Employee> newEmploy = Flux.just(employee3);
		Mockito.when(repo.findAll()).thenReturn(newEmploy);
		Flux<Employee> response = handler.getEmployeeList();
		Assert.assertEquals(newEmploy, response);
	}

	/**
	 * Test for saved employee
	 */
	@Test
	public void testSaveEmploy() {
		Mono<Employee> employList = Mono.just(employee3);
		Mockito.when(repo.save(Mockito.any())).thenReturn(employList);
		Employee employee = new Employee();
		Employee response = handler.saveEmploy(employee);
		Assert.assertEquals(employee3, response);
	}

	/**
	 * Test for list of employees in a particular account
	 */
	@Test
	public void testFindByAccountName() {
		Flux<Employee> employList = Flux.just(employee3);
		Mockito.when(repo.findByAccountName("Walmart")).thenReturn(employList);
		Flux<Employee> response = handler.findByAccountName("Walmart");
		Assert.assertEquals(employList, response);
	}

	/**
	 * Test to find an employee by id
	 */
	@Test
	public void testFindById() {
		Mono<Employee> employList = Mono.just(employee3);
		Mockito.when(repo.findById("3")).thenReturn(employList);
		Mono<Employee> response = handler.findById("3");
		Assert.assertEquals(employList, response);
	}

	/**
	 * Test to find an employee by id
	 */
	@Test
	public void testSearchById() {
		Mockito.when(repo.findById(Mockito.any()))
				.thenReturn(getMonoEmployee());  
		EmployeeMangmntBO employee = handler.searchById("3");
		Assert.assertEquals("Lena", employee.getEmployeeName());
	}

	/**
	 * Test for EmployeeManagementException
	 * @throws EmployeeManagementException
	 */
	@Test(expected = EmployeeManagementException.class)
	public void testSearchByIdThrowsException()
			throws EmployeeManagementException {
		Mockito.when(repo.findById(Mockito.any())).thenReturn(Mono.empty());
		handler.searchById("4");
	}

	/**
	 * Test for deleting an employee by Id
	 */
	@Test
	public void testDltEmploy() {
		Mockito.when(repo.findById(Mockito.any(String.class))).thenReturn(
				Mono.just(employee3));
		Mockito.when(repo.delete(Mockito.any(Employee.class))).thenReturn(
				Mono.empty());
		Mono<Employee> response = handler.deleteEmploy("3");
		Assert.assertEquals("C", response.block().getEmplyBand());
	}

	/**
	 * Test for Adding an employee to the table of employee
	 */
	@Test
	public void testAddEmployee() {
		Mockito.when(repo.findById(Mockito.any())).thenReturn(Mono.empty());
		Mockito.when(repo.save(Mockito.any())).thenReturn(getMonoEmployee());
		EmployeeMangmntBO response = handler.addEmployee(employee1);
		Assert.assertEquals("Mobile", response.getAccountName());

	}

	private Mono<Employee> getMonoEmployee() {
		Employee employee1 = new Employee("3", 112, "Lena", "2011/10/10",
				"Mobile", 32000, 6, "C");
		return Mono.just(employee1);
	}

	/**
	 * Test for exception
	 */
	@Test(expected = EmployeeManagementException.class)
	public void testAddEmployeeIfResponseIsNull() {
		Mockito.when(repo.findById(Mockito.any()))
				.thenReturn(getMonoEmployee());
		EmployeeMangmntBO empBO = new EmployeeMangmntBO();
		handler.addEmployee(empBO);
	}

	/**
	 * Test for adding freshers to the table of employee
	 */
	@Test
	public void testAddFresherEmployee() {
		List<EmployeeMangmntBO>employeeoList = new ArrayList <EmployeeMangmntBO>();
		employeeoList.add(employee1);
		Mockito.when(repo.saveAll(Mockito.<List<Employee>> any())).thenReturn(
				getSampleEmployee());
		List<EmployeeMangmntBO> empList = handler
				.addFreshers(new ArrayList<EmployeeMangmntBO>(employeeoList));
		Assert.assertEquals(3, empList.size());

	}

	private Flux<Employee> getSampleEmployee() {
		Employee employee6 = new Employee("3", 112, "Lena", "2011/10/10",
				"Mobile", 32000, 6, "C");
		Employee employee4 = new Employee("4", 114, "Teena", "2011/10/10",
				"Walmart", 32000, 6, "C");
		Employee employee5 = new Employee("4", 114, "Teena", "2011/10/10",
				"Walmart", 32000, 6, "C");
		return Flux.just(employee3, employee3, employee5);
	}

	/**
	 * Test for updating the experience and band of an employee
	 */
	@Test
	public void testUpdateEmployee() {
		Mockito.when(repo.findById(Mockito.any()))
				.thenReturn(getMonoEmployee());
		Mockito.when(repo.save(Mockito.any())).thenReturn(getMonoEmployee());
		EmployeeMangmntBO empBO = handler
				.updateEmployee(new UpdateExperience());
		Assert.assertEquals("Lena", empBO.getEmployeeName());
	}

	/**
	 * Test for exception
	 */
	@Test(expected = EmployeeManagementException.class)
	public void testUpdateEmployeeWhenNoResponse() {
		Mockito.when(repo.findById(Mockito.any())).thenReturn(Mono.empty());
		handler.updateEmployee(new UpdateExperience());
	}

	/**
	 * Test for updating the salary by adding bonus
	 * @throws EmployeeManagementException
	 */
	@Test
	public void testUpdateBonus() throws EmployeeManagementException {
		UpdateBonusBo updateBonusBo = new UpdateBonusBo();
		Mockito.when(repo.findById(Mockito.any())).thenReturn(
				Mono.just(employee3));
		Mockito.when(repo.save(Mockito.any())).thenReturn(Mono.just(employee3));
		updateBonusBo.setId("3");
		updateBonusBo.setBonus(1000);
		EmployeeMangmntBO empBO = handler.updateBonus(updateBonusBo);
		Assert.assertEquals("C", empBO.getEmplyBand());
	}

	/**
	 * Test for EmployeeManagementException
	 */
	@Test(expected = EmployeeManagementException.class)
	public void testUpdateBonusWhenNoResponse() {
		Mockito.when(repo.findById(Mockito.any())).thenReturn(Mono.empty());
		handler.updateBonus(new UpdateBonusBo());
	}

	/**
	 * Test for getting list of employees by custom query
	 */
	@Test
	public void testGetEmpByNameQuery() {
		Mockito.when(
				dao.getEmployeesWithLimit(Mockito.any(String.class),
						Mockito.any(Integer.class), Mockito.any(Integer.class)))
				.thenReturn(Arrays.asList(employee3));
		List<Employee> response = handler.getEmpByNameQuery("Lena", 0, 1);
		Assert.assertEquals(employee3, response.get(0));
	}

	/**
	 * Test for getting specific details of employees by custom query
	 */
	@Test
	public void testGetEmpIdByNameQuery() {
		Model model = new Model();
		model.setId("1");
		model.setEmployeeId(110);
		Mockito.when(
				dao.getEmployeesWithID(Mockito.any(), Mockito.any(),
						Mockito.any())).thenReturn(Arrays.asList(model));
		List<Model> response = handler.getEmpIdByNameQuery("Sachin", 0, 1);
		Assert.assertEquals(model, response.get(0));
	}

}
