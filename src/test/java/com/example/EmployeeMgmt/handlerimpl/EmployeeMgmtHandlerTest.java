package com.example.EmployeeMgmt.handlerimpl;

import java.time.LocalDate;
import java.util.ArrayList;
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

import com.example.employeemgmt.bo.DeleteEmployeeBO;
import com.example.employeemgmt.bo.EmployeeBO;
import com.example.employeemgmt.bo.EmployeeBonusBO;
import com.example.employeemgmt.bo.EmployeeUpdateBO;
import com.example.employeemgmt.dao.EmployeeMgmtDAO;
import com.example.employeemgmt.exception.EmployeeMgmtException;
import com.example.employeemgmt.handler.EmployeeMgmtHandlerImpl;
import com.example.employeemgmt.model.Employee;
import com.example.employeemgmt.repo.IEmployeeMgmtRepo;

/**
 * Test class for EmployeeMgmt Handler
 * 
 * @author 144900
 *
 */
public class EmployeeMgmtHandlerTest {
	@InjectMocks
	EmployeeMgmtHandlerImpl handler;

	@Mock
	IEmployeeMgmtRepo repo;

	@Mock
	EmployeeMgmtDAO dao;

	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Test for {@link EmployeeMgmtHandlerImpl#getEmpList()} with data.
	 */
	@Test
	public void testGetEmpList() {
		Mockito.when(repo.findAll()).thenReturn(getSampleEmployee());
		List<EmployeeBO> empList = handler.getEmpList();
		Assert.assertEquals(2, empList.size());
		Assert.assertEquals("Arathy", empList.get(0).getName());
	}

	/**
	 * Test for {@link EmployeeMgmtHandlerImpl#getEmpList()} with no data.
	 */
	@Test
	public void testGetEmpListWhenListIsEmpty() {
		Mockito.when(repo.findAll()).thenReturn(Flux.just());
		List<EmployeeBO> empList = handler.getEmpList();
		Assert.assertEquals(0, empList.size());
	}

	/**
	 * Test for {@link EmployeeMgmtHandlerImpl#searchById(String)} with data.
	 */
	@Test
	public void testSearchById() {
		Mockito.when(repo.findById(Mockito.any()))
				.thenReturn(getMonoEmployee());
		EmployeeBO employee = handler.searchById("U1");
		Assert.assertEquals("Arathy", employee.getName());
	}

	/**
	 * Test for {@link EmployeeMgmtHandlerImpl#searchById(String)} with no data.
	 */
	@Test(expected = EmployeeMgmtException.class)
	public void testSearchByIdThrowsException() throws EmployeeMgmtException {
		Mockito.when(repo.findById(Mockito.any())).thenReturn(Mono.empty());
		handler.searchById("U1");
	}

	/**
	 * Test for {@link EmployeeMgmtHandlerImpl#searchByBand(String))} with data.
	 */
	@Test
	public void testSearchByBand() {
		Mockito.when(repo.getEmpByBand(Mockito.any())).thenReturn(
				getSampleEmployee());
		List<EmployeeBO> employee = handler.searchByBand("B");
		Assert.assertEquals("Arathy", employee.get(0).getName());
	}

	/**
	 * Test for {@link EmployeeMgmtHandlerImpl#searchByBand(String))} with no
	 * data.
	 */
	@Test(expected = EmployeeMgmtException.class)
	public void testSearchByBandIfresponseEmpty() {
		Mockito.when(repo.getEmpByBand(Mockito.any())).thenReturn(Flux.just());
		handler.searchByBand("B");
	}

	/**
	 * Test for {@link EmployeeMgmtHandlerImpl#addEmployee(EmployeeBO)} with
	 * data.
	 */
	@Test
	public void testAddEmployee() {
		Mockito.when(repo.findById(Mockito.any())).thenReturn(Mono.empty());
		Mockito.when(repo.save(Mockito.any())).thenReturn(getMonoEmployee());
		EmployeeBO empBO = handler.addEmployee(new EmployeeBO());
		Assert.assertEquals("Arathy", empBO.getName());
	}

	/**
	 * Test for {@link EmployeeMgmtHandlerImpl#addEmployee(EmployeeBO)} with
	 * employee already exist.
	 */
	@Test(expected = EmployeeMgmtException.class)
	public void testAddEmployeeIfResponseIsNull() {
		Mockito.when(repo.findById(Mockito.any()))
				.thenReturn(getMonoEmployee());
		handler.addEmployee(new EmployeeBO());
	}

	/**
	 * Test for {@link EmployeeMgmtHandlerImpl#addFreshers(List)} with data.
	 */
	@Test
	public void testAddFresherEmployee() {
		Mockito.when(repo.saveAll(Mockito.<List<Employee>> any())).thenReturn(
				getSampleEmployee());
		List<EmployeeBO> empList = handler
				.addFreshers(getSampleEmployeeBOList());
		Assert.assertEquals(2, empList.size());
	}

	/**
	 * Test for {@link EmployeeMgmtHandlerImpl#deleteEmployee(String)} with
	 * data.
	 */
	@Test
	public void testDeleteEmployee() throws EmployeeMgmtException {
		Mockito.when(repo.findById(Mockito.any()))
				.thenReturn(getMonoEmployee());
		Mockito.when(repo.delete(Mockito.any())).thenReturn(Mono.empty());
		EmployeeBO empBO = handler.deleteEmployee("U3");
		Assert.assertEquals("Arathy", empBO.getName());
	}

	/**
	 * Test for {@link EmployeeMgmtHandlerImpl#deleteEmployee(String)} with no
	 * data.
	 */
	@Test(expected = EmployeeMgmtException.class)
	public void testDeleteEmployeeWheNoResponse() {
		Mockito.when(repo.findById(Mockito.any())).thenReturn(Mono.empty());
		handler.deleteEmployee("U3");
	}

	/**
	 * Test for
	 * {@link EmployeeMgmtHandlerImpl#deleteEmployeeByBand(DeleteEmployeeBO)}
	 * with data.
	 */
	@Test
	public void testDeleteEmployeeByBand() {
		Mockito.when(
				repo.getEmpByAccountNameAndBand(Mockito.any(), Mockito.any()))
				.thenReturn(getSampleEmployee());
		Mockito.when(repo.delete(Mockito.any())).thenReturn(Mono.empty());
		List<EmployeeBO> empList = handler
				.deleteEmployeeByBand(new DeleteEmployeeBO());
		Assert.assertEquals("Arathy", empList.get(0).getName());
	}

	/**
	 * Test for
	 * {@link EmployeeMgmtHandlerImpl#deleteEmployeeByBand(DeleteEmployeeBO)}
	 * with no data.
	 */
	@Test(expected = EmployeeMgmtException.class)
	public void testDeleteEmployeeByBandwhenNoResponse()
			throws EmployeeMgmtException {
		Mockito.when(
				repo.getEmpByAccountNameAndBand(Mockito.any(), Mockito.any()))
				.thenReturn(Flux.just());
		handler.deleteEmployeeByBand(new DeleteEmployeeBO());
	}

	/**
	 * Test for {@link EmployeeMgmtHandlerImpl#updateEmployee(EmployeeUpdateBO)}
	 * with data.
	 */
	@Test
	public void testUpdateEmployee() {
		Mockito.when(repo.findById(Mockito.any()))
				.thenReturn(getMonoEmployee());
		Mockito.when(repo.save(Mockito.any())).thenReturn(getMonoEmployee());
		EmployeeBO empBO = handler.updateEmployee(new EmployeeUpdateBO());
		Assert.assertEquals("Arathy", empBO.getName());
	}

	/**
	 * Test for {@link EmployeeMgmtHandlerImpl#updateEmployee(EmployeeUpdateBO)}
	 * with no data.
	 */
	@Test(expected = EmployeeMgmtException.class)
	public void testUpdateEmployeeWhenNoResponse() {
		Mockito.when(repo.findById(Mockito.any())).thenReturn(Mono.empty());
		handler.updateEmployee(new EmployeeUpdateBO());
	}

	/**
	 * Test for {@link EmployeeMgmtHandlerImpl#addAllowance(Double))} with data.
	 */
	@Test
	public void testAddAllowance() {
		Mockito.when(repo.findAll()).thenReturn(getSampleEmployee());
		Mockito.when(repo.saveAll(Mockito.<List<Employee>> any())).thenReturn(
				getSampleEmployee());
		List<EmployeeBO> empList = handler.addAllowance(10.0);
		Assert.assertEquals("Arathy", empList.get(0).getName());
	}

	/**
	 * Test for {@link EmployeeMgmtHandlerImpl#addAllowance(Double))} with no
	 * data.
	 */
	@Test
	public void testAddAllowanceIfNoResponse() {
		Mockito.when(repo.findAll()).thenReturn(Flux.just());
		List<EmployeeBO> empList = handler.addAllowance(10.0);
		Assert.assertEquals(0, empList.size());
	}

	/**
	 * Test for {@link EmployeeMgmtHandlerImpl#addAllowance(Double))} when
	 * salary is null.
	 */
	@Test
	public void testAddAllowanceWhenSalaryNull() {
		Mockito.when(repo.findAll()).thenReturn(getEmployees());
		Mockito.when(repo.saveAll(Mockito.<List<Employee>> any())).thenReturn(
				getSampleEmployee());
		List<EmployeeBO> empList = handler.addAllowance(10.0);
		Assert.assertEquals("Arathy", empList.get(0).getName());
	}

	/**
	 * Test for {@link EmployeeMgmtHandlerImpl#searchByAcc(String)} with data.
	 */
	@Test
	public void testSearchByAcc() {
		Mockito.when(repo.getEmpByAccountName(Mockito.any())).thenReturn(
				getSampleEmployee());
		List<EmployeeBO> employee = handler.searchByAcc("abc");
		Assert.assertEquals("Arathy", employee.get(0).getName());

	}

	/**
	 * Test for {@link EmployeeMgmtHandlerImpl#searchByAcc(String)} with no
	 * data.
	 */
	@Test(expected = EmployeeMgmtException.class)
	public void testSearchByAccIfNoResponse() {
		Mockito.when(repo.getEmpByAccountName(Mockito.any())).thenReturn(
				Flux.just());
		handler.searchByAcc("abc");
	}

	/**
	 * Test for {@link EmployeeMgmtHandlerImpl#addBonus(EmployeeBonusBO)} with
	 * data.
	 */
	@Test
	public void testAddBonus() throws EmployeeMgmtException {
		Mockito.when(repo.findById(Mockito.any()))
				.thenReturn(getMonoEmployee());
		EmployeeBO empBO = handler.addBonus(new EmployeeBonusBO("U1", 20.0));
		Assert.assertEquals(Double.valueOf(42000.0), empBO.getSalary());
	}

	/**
	 * Test for {@link EmployeeMgmtHandlerImpl#addBonus(EmployeeBonusBO)} with
	 * no data.
	 */
	@Test(expected = EmployeeMgmtException.class)
	public void testAddBonusWhenNoResponse() {
		Mockito.when(repo.findById(Mockito.any())).thenReturn(Mono.empty());
		handler.addBonus(new EmployeeBonusBO("U1", 20.0));
	}

	/**
	 * Test for
	 * {@link EmployeeMgmtHandlerImpl#getEmpByNameQuery(String, Integer, Integer)}
	 * with data.
	 */
	@Test
	public void testgetEmpByNameQuery() {
		Mockito.when(
				dao.getEmployeesWithLimit(Mockito.any(), Mockito.anyInt(),
						Mockito.anyInt())).thenReturn(getSampleEmployeeList());
		List<EmployeeBO> employee = handler.getEmpByNameQuery("U1", 1, 2);
		Assert.assertEquals("Arathy", employee.get(0).getName());
	}

	/**
	 * Test for
	 * {@link EmployeeMgmtHandlerImpl#getEmpByNameQuery(String, Integer, Integer)}
	 * with no data.
	 */
	@Test(expected = EmployeeMgmtException.class)
	public void testgetEmpByNameQueryIfNoResponse() {
		Mockito.when(
				dao.getEmployeesWithLimit(Mockito.any(), Mockito.anyInt(),
						Mockito.anyInt())).thenReturn(null);
		handler.getEmpByNameQuery("U1", 1, 2);
	}

	/**
	 * Test for {@link EmployeeMgmtHandlerImpl#getEmployeesWithId(String)} with
	 * data.
	 */
	@Test
	public void testgetEmployeesWithId() {
		Mockito.when(dao.getEmployeesWithId(Mockito.any())).thenReturn(
				new Employee("U1", "Arathy", "abc", null, 1.0, "B"));
		EmployeeBO employee = handler.getEmployeesWithId("U1");
		Assert.assertEquals("Arathy", employee.getName());
	}

	/**
	 * Test for {@link EmployeeMgmtHandlerImpl#getEmployeesWithId(String)} with
	 * no data.
	 */
	@Test(expected = EmployeeMgmtException.class)
	public void testgetEmployeesWithIdWhenNoResponse() {
		Mockito.when(dao.getEmployeesWithId(Mockito.any())).thenReturn(null);
		handler.getEmployeesWithId("U1");
	}

	/**
	 * function for get employeeBO sample List
	 * 
	 * @return List<EmployeeBO>
	 */
	private List<EmployeeBO> getSampleEmployeeBOList() {
		List<EmployeeBO> employeeList = new ArrayList<EmployeeBO>();
		employeeList.add(new EmployeeBO("1", "Arathy", LocalDate
				.of(2019, 8, 15), "abc", 35000.0, 1.0, "B"));
		employeeList.add(new EmployeeBO("U2", "Kallu", LocalDate
				.of(2015, 8, 15), "xyz", 55000.0, 5.0, "C"));
		return employeeList;
	}

	/**
	 * function for get employee sample List
	 * 
	 * @return List<Employee>
	 */
	private List<Employee> getSampleEmployeeList() {
		List<Employee> employeeList = new ArrayList<Employee>();
		employeeList.add(new Employee("U1", "Arathy", "abc", null, 1.0, "B"));
		employeeList.add(new Employee("U2", "Kallu", "xyz", 55000.0, 5.0, "C"));
		return employeeList;
	}

	/**
	 * function for get employee flux
	 * 
	 * @return Flux<Employee>
	 */
	private Flux<Employee> getSampleEmployee() {
		Employee employee1 = new Employee("U1", "Arathy", "abc", 35000.0, 1.0,
				"B");
		Employee employee2 = new Employee("U2", "Kallu", "abc", 35000.0, 1.0,
				"B");
		return Flux.just(employee1, employee2);
	}

	private Flux<Employee> getEmployees() {
		Employee employee1 = new Employee("U1", "Arathy", "abc", null, 1.0, "B");
		Employee employee2 = new Employee("U2", "Kallu", "abc", 35000.0, 1.0,
				"B");
		return Flux.just(employee1, employee2);
	}

	/**
	 * function for get employee Mono
	 * 
	 * @return Mono<Employee>
	 */
	private Mono<Employee> getMonoEmployee() {
		Employee employee1 = new Employee("U1", "Arathy", "abc", 35000.0, 1.0,
				"B");
		return Mono.just(employee1);
	}

}
