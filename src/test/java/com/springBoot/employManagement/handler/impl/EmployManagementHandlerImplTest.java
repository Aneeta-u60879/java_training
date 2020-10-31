package com.springBoot.employManagement.handler.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.springBoot.employManagement.bo.EmployBO;
import com.springBoot.employManagement.bo.UpdateAllowance;
import com.springBoot.employManagement.bo.UpdateBonusBO;
import com.springBoot.employManagement.bo.UpdateExperienceBO;
import com.springBoot.employManagement.dao.EmployManagementDAO;
import com.springBoot.employManagement.exception.EmployManagementException;
import com.springBoot.employManagement.model.Employee;
import com.springBoot.employManagement.model.Model;
import com.springBoot.employManagement.repo.IEmployManagementRepo;

/**
 * Test case for Handler
 * 
 * @author 144785
 *
 */
public class EmployManagementHandlerImplTest {
	@InjectMocks
	private EmployManagementHandlerImpl handler;

	@Mock
	private IEmployManagementRepo repo;

	@Mock
	private EmployManagementDAO dao;

	EmployBO employ1 = new EmployBO(1001, "Sachin", "2019, 11, 1", "Walmart",
			20000.0, 0, "A", "U1");
	EmployBO employ2 = new EmployBO(1034, "Dravid", "2018, 1, 3", "Walmart",
			32000.0, 2, "B", "U3");
	Employee employ4 = new Employee(1003, "Sachin", "2019, 11, 1", "Walmart",
			20000.0, 0, "A", "U1");

	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * test for getEmployeeList method
	 */
	@Test
	public void testGetEmployeeList() {
		Flux<Employee> newEmploy = Flux.just(employ4);
		Mockito.when(repo.findAll()).thenReturn(newEmploy);
		Flux<Employee> response = handler.getEmployeeList();
		Assert.assertEquals(newEmploy, response);
	}

	/**
	 * test for getEmployeeByAccountName method
	 */
	@Test
	public void testGetEmployeeByAccountName() {
		Flux<Employee> employList = Flux.just(employ4);
		Mockito.when(repo.getEmpByAccountName(Mockito.any())).thenReturn(
				employList);
		Flux<Employee> response = handler.getEmployeeByAccountName("Walmart");
		Assert.assertEquals(employList, response);
	}

	/**
	 * test for getEmployeeByBand method
	 */
	@Test
	public void testGetEmployeeByBand() {
		Flux<Employee> employList = Flux.just(employ4);
		Mockito.when(repo.getEmpByBand(Mockito.any())).thenReturn(employList);
		Flux<Employee> response = handler.getEmployeeByBand("A");
		Assert.assertEquals(employList, response);
	}

	/**
	 * test for getEmployeeByEmployId method
	 */
	@Test
	public void testGetEmployeeByEmployId() {
		Flux<Employee> employList = Flux.just(employ4);
		Mockito.when(repo.getEmpByEmployId(1003)).thenReturn(employList);
		Flux<Employee> response = handler.getEmployeeByEmployId(1003);
		Assert.assertEquals(employList, response);
	}

	/**
	 * test for saveEmployee method
	 */
	@Test
	public void testSaveEmployee() {
		Mono<Employee> employList = Mono.just(employ4);
		Mockito.when(repo.save(Mockito.any())).thenReturn(employList);
		EmployBO response = handler.saveEmployee(employ2);
		Assert.assertEquals("Sachin", response.getName());
	}

	/**
	 * test for deleteEmployee method
	 * 
	 * @throws EmployManagementException
	 */
	@Test
	public void testDeleteEmployee() throws EmployManagementException {
		Mockito.when(repo.findById(Mockito.any())).thenReturn(
				Mono.just(employ4));
		Mockito.when(repo.delete(Mockito.any())).thenReturn(Mono.empty());
		Mono<Employee> response = handler.deleteEmployee("234");
		Assert.assertEquals("A", response.block().getBand());
	}

	/**
	 * test for addFreshers method
	 */
	@Test
	public void testAddFreshers() {
		Mockito.when(repo.saveAll(Mockito.<List<Employee>> any())).thenReturn(
				Flux.just(employ4));
		List<Employee> emplyList = new ArrayList<Employee>();
		emplyList.add(employ4);
		List<EmployBO> employeeList = new ArrayList<EmployBO>();
		employeeList.add(employ1);
		List<EmployBO> response = handler.addFreshers(employeeList);
		Assert.assertEquals(1, response.size());
	}

	/**
	 * test for updateExperience method
	 * 
	 * @throws EmployManagementException
	 */
	@Test(expected = EmployManagementException.class)
	public void testUpdateExperience() throws EmployManagementException {
		Mockito.when(repo.findById(Mockito.any())).thenReturn(null);
		UpdateExperienceBO employ5 = new UpdateExperienceBO();
		employ5.setExperience(2);
		employ5.setBand("B");
		employ5.setId("U4");
		handler.updateExperience(employ5);
	}

	/**
	 * test for updateExperience else case
	 * 
	 * @throws EmployManagementException
	 */
	@Test
	public void testUpdateExperienceElse() throws EmployManagementException {
		Mockito.when(repo.findById(Mockito.any())).thenReturn(
				Mono.just(employ4));
		Mockito.when(repo.save(Mockito.any())).thenReturn(Mono.just(employ4));

		UpdateExperienceBO employ5 = new UpdateExperienceBO();
		employ5.setExperience(2);
		employ5.setBand("B");
		employ5.setId("U4");
		EmployBO response = handler.updateExperience(employ5);
		Assert.assertEquals("B", response.getBand());
	}

	/**
	 * test for updateBonus method
	 * 
	 * @throws EmployManagementException
	 */
	@Test(expected = EmployManagementException.class)
	public void testUpdateBonus() throws EmployManagementException {
		Mockito.when(repo.findById(Mockito.any())).thenReturn(null);
		UpdateBonusBO employ5 = new UpdateBonusBO();
		handler.updateBonus(employ5);
	}

	/**
	 * test for updateBonusElse else case
	 * 
	 * @throws EmployManagementException
	 */
	@Test
	public void testUpdateBonusElse() throws EmployManagementException {
		Mockito.when(repo.findById(Mockito.any())).thenReturn(
				Mono.just(employ4));
		Mockito.when(repo.save(Mockito.any())).thenReturn(Mono.just(employ4));
		UpdateBonusBO employ5 = new UpdateBonusBO();
		employ5.setId("U1");
		employ5.setBonus(0.0);
		EmployBO response = handler.updateBonus(employ5);
		Assert.assertEquals("A", response.getBand());
	}

	/**
	 * test for updateAllowance method
	 * 
	 * @throws EmployManagementException
	 */
	@Test(expected = EmployManagementException.class)
	public void testUpdateAllowance() throws EmployManagementException {
		Mockito.when(repo.findById(Mockito.any())).thenReturn(null);
		UpdateAllowance employ5 = new UpdateAllowance();
		employ5.setId("U4");
		employ5.setAllowance(0);
		handler.updateAllowance(employ5);
	}

	/**
	 * test for updateAllowanceElse else case
	 * 
	 * @throws EmployManagementException
	 */
	@Test
	public void testUpdateAllowanceElse() throws EmployManagementException {
		Mockito.when(repo.findById(Mockito.any())).thenReturn(
				Mono.just(employ4));
		Mockito.when(repo.save(Mockito.any())).thenReturn(Mono.just(employ4));
		UpdateAllowance employ5 = new UpdateAllowance();
		employ5.setId("U4");
		employ5.setAllowance(0);
		EmployBO response = handler.updateAllowance(employ5);
		Assert.assertEquals("A", response.getBand());
	}

	/**
	 * test for getEmpByNameQuery method
	 */
	@Test
	public void testGetEmpByNameQuery() {
		Mockito.when(
				dao.getEmployeesWithLimit(Mockito.any(), Mockito.any(),
						Mockito.any())).thenReturn(Arrays.asList(employ4));
		List<Employee> response = handler.getEmpByNameQuery("Sachin", 0, 1);
		Assert.assertEquals(employ4, response.get(0));
	}

	/**
	 * test for getEmpByIdQuery method
	 */
	@Test
	public void testGetEmpByIdQuery() {
		Model model = new Model();
		model.setEmployId(145);
		model.setId("U4");
		Mockito.when(
				dao.getEmployWithLimit(Mockito.any(), Mockito.any(),
						Mockito.any())).thenReturn(Arrays.asList(model));
		List<Model> response = handler.getEmpByIdQuery("Sachin", 0, 1);
		Assert.assertEquals(model, response.get(0));
	}

}
