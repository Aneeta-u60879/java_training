package com.employee.employeemgmt.handler.impl;

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

import com.employee.employeemgmt.BO.UpadateExperience;
import com.employee.employeemgmt.BO.UpdateBonus;
import com.employee.employeemgmt.DTO.EmployeeMgmtDTO;
import com.employee.employeemgmt.dao.EmployeeDao;
import com.employee.employeemgmt.exception.EmployeeMgmtException;
import com.employee.employeemgmt.model.ByAccountModel;
import com.employee.employeemgmt.model.EmployModel;
import com.employee.employeemgmt.repo.EmployeeMgmtRepo;

/**
 * Test for employ handler
 * @author 144895
 *
 */
public class EmployeeMgmtHandlerImplTests {
	
	@InjectMocks
	private EmployeeMgmtHandlerImpl employeeHandler;
	
	
	
	
	EmployModel newList = new EmployModel("emp1","Erin","2019-01-01","Experion",32000.0,(float) 1,"B");
	EmployeeMgmtDTO employ1 = new EmployeeMgmtDTO("emp2","Merin","2018-01-01","Walmart",32000.0,(float)2,"B");
	
	
	@Mock
	private EmployeeMgmtRepo  employRepo;
	@Mock
	private EmployeeDao  employDao;
	
	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	/**
	 * To get all employe
	 */
	@Test
	public void testGetAllEmploy(){
		Mockito.when(employRepo.findAll()).thenReturn(Flux.just(newList));
		List<EmployeeMgmtDTO> finalEmployList = employeeHandler.getEmployeeList();
		Assert.assertEquals("Erin",finalEmployList.get(0).getName());
	}
	
	/**
	 * To save employ
	 */
	@Test
	public void testSaveEmploy(){
		Mono<EmployModel> emp = null;
		Mockito.when(employRepo.findById(Mockito.any())).thenReturn(emp);
		Mockito.when(employRepo.save(Mockito.any())).thenReturn(Mono.just(newList));
		EmployModel response = employeeHandler.saveEmploy(newList);
		Assert.assertEquals("Erin",response.getName());
	}
	
	/**
	 * Save employ exception
	 */
	@Test (expected = EmployeeMgmtException.class)
	public void testSaveEmployExce(){
		Mockito.when(employRepo.findById(Mockito.any())).thenReturn(Mono.just(newList));
		Mockito.when(employRepo.save(Mockito.any())).thenReturn(Mono.just(newList));
		employeeHandler.saveEmploy(newList);
	}
	
	
	/**
	 * Delete employ
	 */
	@Test
	public void deleteEmploy(){
		Mockito.when(employRepo.findById(Mockito.any())).thenReturn(Mono.just(newList));
		Mockito.when(employRepo.delete(Mockito.any())).thenReturn(Mono.empty());
		EmployModel response = employeeHandler.deleteEmploye("emp1");
		Assert.assertEquals("Erin",response.getName());
	}
	
	/**
	 * Get employ by account
	 */
	@Test
	public void testGetByAccount(){
		Mockito.when(employRepo.findByAccountName(Mockito.any())).thenReturn(Flux.just(newList));
		EmployModel response = employeeHandler.getEmployeeByAccnt("Experion");
		Assert.assertEquals("Erin",response.getName());
	}
	
	/**
	 * Update employ experience
	 */
	@Test
	public void testUpdateExpr(){
		UpadateExperience update = new UpadateExperience();
		update.setEmployeeId("emp1");
		update.setExperience((float) 3);
		update.setBand("C");
		Mockito.when(employRepo.findById(Mockito.any())).thenReturn(Mono.just(newList));
		Mockito.when(employRepo.save(Mockito.any())).thenReturn(Mono.just(newList));
		EmployModel response = employeeHandler.updateEmploy(update);
		Assert.assertEquals("C",response.getBand());
	}
	
	/**
	 * update employ bonus
	 */
	@Test
	public void testUpdateBonus(){
		UpdateBonus update = new UpdateBonus();
		update.setEmployeeId("emp2");
		update.setBonus(32000.0);
		Mockito.when(employRepo.findById(Mockito.any())).thenReturn(Mono.just(newList));
		Mockito.when(employRepo.save(Mockito.any())).thenReturn(Mono.just(newList));
		EmployModel response = employeeHandler.updateBonus(update);
		Assert.assertEquals(new Double(64000.0),response.getSalary());
	}
	
	/**
	 * Add freshers
	 */
	@Test
	public void tesAddFreshers(){
		List<EmployModel> employList = new ArrayList<EmployModel>();
		employList.add(newList);
		Mockito.when(employRepo.saveAll(employList)).thenReturn(Flux.just(newList));
		EmployModel newEmployList = employeeHandler.addFreshers(employList);
		 Assert.assertEquals("Erin",newEmployList.getName() );
	}
	
	/**
	 * Get by account custom query
	 */
	@Test
	public void testGetByaccount(){
		ByAccountModel byAcc = new ByAccountModel();
		byAcc.setName("Bijoy");
		byAcc.setEmployeeId("emp1");
		List<ByAccountModel> byAccList = new ArrayList<ByAccountModel>();
		byAccList.add(byAcc);
		Mockito.when(employDao.getEmloyList(Mockito.any())).thenReturn(byAccList);
		 List<ByAccountModel> listByAcc = employeeHandler.getByAccnt("Walmart");
		 Assert.assertEquals("Bijoy", listByAcc.get(0).getName());
	}

	
}
