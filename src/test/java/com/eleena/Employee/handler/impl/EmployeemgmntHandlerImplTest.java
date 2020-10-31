package com.eleena.Employee.handler.impl;



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

import com.eleena.Employee.bo.EmployeeMngmntBo;
import com.eleena.Employee.bo.UpdateBonus;
import com.eleena.Employee.bo.UpdateExperience;
import com.eleena.Employee.dao.EmployeeDAO;
import com.eleena.Employee.exception.EmployeeMgmntException;
import com.eleena.Employee.model.EmployeeMngmnt;
import com.eleena.Employee.model.EmployeeQueryModel;
import com.eleena.Employee.repo.IEmployeeMgmntRepo;

public class EmployeemgmntHandlerImplTest {
	@InjectMocks
	private EmployeeMgmntHandlerImpl handler;
	@Mock
	private IEmployeeMgmntRepo repo;
	@Mock
	private EmployeeDAO dao;

	EmployeeMngmnt empl1 = new EmployeeMngmnt("453","em15","Eliza","2011-01-2","Team1",24200.0,(float) 3.00,"E");
	EmployeeMngmntBo empl2 = new EmployeeMngmntBo("454","em16","Erin","2008-09-2","Walmart",420000.0,(float) 5.00,"H");
	EmployeeMngmntBo empl3 = new EmployeeMngmntBo("455","em17","Eleena","1999-09-2","Walmart",420000.0,(float) 6.00,"B");

	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
	}


	@Test
	public void testAddEmployee() {
		Mono <EmployeeMngmnt> emp = Mono.just(empl1 );
		Mockito.when(repo.save(Mockito.any(EmployeeMngmnt.class))).thenReturn(emp);
		EmployeeMngmntBo respose =  handler.addEmployee(empl2);
		Assert.assertEquals("Team1", respose.getAccountName());
	}
	@Test
	public void testSaveEmployee() {
		Mono <EmployeeMngmnt> emp = Mono.just(empl1 );
		Mockito.when(repo.save(Mockito.any(EmployeeMngmnt.class))).thenReturn(emp);
		EmployeeMngmnt respose =  handler.saveEmploy(empl1);
		Assert.assertEquals("Team1", respose.getAccountName());
	}
//	@Test
//	public void testAddEmployeeException() throws EmployeeMgmntException {
//		Mono <EmployeeMngmnt> emp = Mono.just(empl1);
//		Mockito.when(repo.save(Mockito.any(EmployeeMngmnt.class))).thenReturn();
//		EmployeeMngmntBo respose =  handler.addEmployee(empl2);
//	
//	}
	@Test
	public void getEmployeeById() {
		Mockito.when(repo.findById(Mockito.any(String.class))).thenReturn(Mono.just(empl1));
		String id ="234";
		Mono<EmployeeMngmnt> respose =  handler.findById(id);
		Assert.assertEquals("Team1",respose.block().getAccountName());
	}
	@Test
	public void getEmployeeByAccountName() {
		Mockito.when(repo.findByAccountName(Mockito.any(String.class))).thenReturn(Flux.just(empl1));
		String accountName ="walmart";
		Flux<EmployeeMngmnt> respose =  handler.findByAccount(accountName);
		Assert.assertEquals("Team1",respose.collectList().block().get(0).getAccountName());
	}
	@Test
	public void testgetEmpByNameQuery() {
		Mockito.when(dao.getEmployeesWithLimit(Mockito.any(String.class),Mockito.any(Integer.class),Mockito.any(Integer.class))).thenReturn(Arrays.asList(empl1));
		List<EmployeeMngmnt> respose =  handler.getEmpByNameQuery( "erin",0,1);
		Assert.assertEquals(empl1, respose.get(0));
	}
	@Test
	public void testgetEmpByNameQury() {
		EmployeeQueryModel emp= new EmployeeQueryModel( "453","Eliza");
		List<EmployeeQueryModel> empList = new ArrayList<EmployeeQueryModel>();
		empList.add(emp);
	    Mockito.when(dao.getEmployeesWithoutLimit(Mockito.any(String.class))).thenReturn(empList);
		List<?> respose =  handler.getEmpByNameQury("walmart");
		Assert.assertEquals(emp,respose.get(0));
	}
	@Test
	public void testDltEmploy() {
		Mockito.when(repo.findById(Mockito.any(String.class))).thenReturn(Mono.just(empl1));
		Mockito.when(repo.delete(Mockito.any(EmployeeMngmnt.class))).thenReturn(Mono.empty());
		String id ="234";
		Mono<EmployeeMngmnt> respose =  handler.dltEmploy(id);
		Assert.assertEquals("E",respose.block().getBand());
	}
	
	@Test
	public void testGetAllEmploy(){
		Flux <EmployeeMngmnt> emp = Flux.just(empl1 );
		Mockito.when(repo.findAll()).thenReturn(emp);
		Flux<EmployeeMngmnt> finalEmployList = handler.getEmployeeList();
		Assert.assertEquals("Team1",finalEmployList.collectList().block().get(0).getAccountName());
	}
	


	@Test
	public void testUpdateExperience() {
		UpdateExperience updateEmp = new UpdateExperience("455",(float) 6.00,"B");
		Mockito.when(repo.findById(Mockito.any(String.class))).thenReturn(Mono.just(empl1));
//		Mockito.when(repo.save(Mockito.any(EmployeeMngmnt.class))).thenReturn(Mono.just(new EmployeeMngmnt()));
		Mockito.when(repo.save(Mockito.any(EmployeeMngmnt.class))).thenReturn(Mono.just(empl1));
		EmployeeMngmntBo respose =  handler.updateExperience(updateEmp);
		Assert.assertEquals("B", respose.getBand());
	}
	@Test
	public void testAllowance() {
		Flux <EmployeeMngmnt> emp = Flux.just(empl1 );
		Mockito.when(repo.findAll()).thenReturn(emp);
//		Mockito.when(repo.saveAll(Arrays.asList(empl1))).thenReturn(emp);
		Mockito.when(repo.saveAll(Mockito.<List<EmployeeMngmnt>>any())).thenReturn(emp);
		int bonus = 20;
		List<EmployeeMngmntBo> fetchList = handler.emplyAllowance(bonus);
		Assert.assertEquals(empl1, fetchList);
	}
	@Test
	public void testUpdateBonus() {
		UpdateBonus updateBonusModel = new UpdateBonus(20.00,"458");
		Mockito.when(repo.save(Mockito.any(EmployeeMngmnt.class))).thenReturn(Mono.just(empl1));
		Mockito.when(repo.findById(Mockito.any(String.class))).thenReturn(Mono.just(empl1));
		EmployeeMngmntBo respose = handler.updateBonus(updateBonusModel);
		Assert.assertEquals("E", respose.getBand());
	}
	
	
}

//	@Test
//	public void testUpdateExperience() {
//		List<EmployeeMngmntBo> employeeMngmntList = new ArrayList<EmployeeMngmntBo>();
//		empl2.setEmplyExperience(9);
//		employeeMngmntList.add(empl1);
//		employeeMngmntList.add(empl2);
//		UpdateExperience update = new UpdateExperience();
//		update.setEmployeeId(144784);
//		update.setEmplyExperience(9);
//		update.setEmplyBand("B");
//		Mockito.when(repo.fetchEmployeeList()).thenReturn(employeeMngmntList);
//		List<EmployeeMngmntBo> respose = Handler.updateExperience(update);
//		Assert.assertEquals(employeeMngmntList, respose);
//	}
//
//	@Test
//	public void dltByAcount() throws EmployeeMgmntException {
//		List<EmployeeMngmntBo> employeeMngmntList = new ArrayList<EmployeeMngmntBo>();
//		employeeMngmntList.add(empl1);
//		employeeMngmntList.add(empl2);
//		employeeMngmntList.add(empl3);
//		List<EmployeeMngmntBo> responseEmployeeMngmntList = new ArrayList<>();
//		responseEmployeeMngmntList.add(empl2);
//		responseEmployeeMngmntList.add(empl3);
//		DltByAccnt dltModel = new DltByAccnt();
//		dltModel.setEmplyAccountName("Walmart");
//		dltModel.setEmplyBand("A");
//		Mockito.when(repo.fetchEmployeeList()).thenReturn(employeeMngmntList);
//		List<EmployeeMngmntBo> respose = Handler.deltAllEmply(dltModel);
//		Assert.assertEquals("Eliza", respose.get(0).getEmployeeName());
//	}
//
//	@Test(expected = EmployeeMgmntException.class)
//	public void dltByAcountException() throws EmployeeMgmntException  {
//		List<EmployeeMngmntBo> employeeMngmntList = new ArrayList<EmployeeMngmntBo>();
//		employeeMngmntList.add(empl1);
//		employeeMngmntList.add(empl2);
//		employeeMngmntList.add(empl3);
//		List<EmployeeMngmntBo> responseEmployeeMngmntList = new ArrayList<>();
//		responseEmployeeMngmntList.add(empl2);
//		responseEmployeeMngmntList.add(empl3);
//		DltByAccnt dltModel = new DltByAccnt();
//		dltModel.setEmplyAccountName("Team");
//		dltModel.setEmplyBand("D");
//		Mockito.when(repo.fetchEmployeeList()).thenReturn(employeeMngmntList);
//		Handler.deltAllEmply(dltModel);
//	}
//
//	@Test
//	public void testUpdateBonus() {
//		List<EmployeeMngmntBo> employeeMngmntList = new ArrayList<EmployeeMngmntBo>();
//		empl2.setEmplySalary(32200d);
//		employeeMngmntList.add(empl1);
//		employeeMngmntList.add(empl2);
//		UpdateBonus bonus = new UpdateBonus();
//		bonus.setBonuOfEmployee(200d);
//		bonus.setEmployeeId(144784);
//		Mockito.when(repo.fetchEmployeeList()).thenReturn(employeeMngmntList);
//		List<EmployeeMngmntBo> respose = Handler.updateBonus(bonus);
//		Assert.assertEquals(employeeMngmntList, respose);
//	}
//
//	@Test
//	public void testremoveEmployee() {
//		List<EmployeeMngmntBo> employeeMngmntList = new ArrayList<EmployeeMngmntBo>();
//		employeeMngmntList.add(empl1);
//		employeeMngmntList.add(empl2);
//		List<EmployeeMngmntBo> updatedList = new ArrayList<EmployeeMngmntBo>();
//		updatedList.add(empl2);
//		Mockito.when(repo.fetchEmployeeList()).thenReturn(employeeMngmntList);
//		List<EmployeeMngmntBo> fetchList = Handler.removeEmployee(144787);
//		Assert.assertEquals(updatedList, fetchList);
//	}
//
//	@Test
//	public void testFetchByBand() {
//		List<EmployeeMngmntBo> employeeMngmntList = new ArrayList<EmployeeMngmntBo>();
//		employeeMngmntList.add(empl1);
//		employeeMngmntList.add(empl2);
//		List<EmployeeMngmntBo> updatedList = new ArrayList<EmployeeMngmntBo>();
//		updatedList.add(empl1);
//		Mockito.when(repo.fetchEmployeeList()).thenReturn(employeeMngmntList);
//		List<EmployeeMngmntBo> fetchList = Handler.fetchEmplyByBand("A");
//		Assert.assertEquals(updatedList, fetchList);
//	}
//
//	@Test
//	public void testAllowance() {
//		List<EmployeeMngmntBo> employeeMngmntList = new ArrayList<EmployeeMngmntBo>();
//		empl1.setEmplySalary(40000d);
//		employeeMngmntList.add(empl1);
//		Mockito.when(repo.fetchEmployeeList()).thenReturn(employeeMngmntList);
//		int bonus = 200;
//		List<EmployeeMngmntBo> fetchList = Handler.emplyAllowance(bonus);
//		Assert.assertEquals(employeeMngmntList, fetchList);
//	}
//
//	@Test
//	public void testFetchById() {
//		EmployeeMngmntBo bo = new EmployeeMngmntBo(144787, "Eliza",
//				LocalDate.of(2019, 01, 01), "Walmart", 20000D, 0, "A");
//		List<EmployeeMngmntBo> employeeMngmntList = new ArrayList<EmployeeMngmntBo>();
//		employeeMngmntList.add(empl1);
//		employeeMngmntList.add(empl2);
//		Mockito.when(repo.fetchEmployeeList()).thenReturn(employeeMngmntList);
//		EmployeeMngmntBo fetchList = Handler.fetchEmplyById(144787);
//		Assert.assertEquals("Eliza", fetchList.getEmployeeName());
//	}
//
//	@Test
//	public void testFetchAllEmploy() {
//		List<EmployeeMngmntBo> employeeMngmntList = new ArrayList<EmployeeMngmntBo>();
//		employeeMngmntList.add(empl1);
//		employeeMngmntList.add(empl2);
//		Mockito.when(repo.fetchEmployeeList()).thenReturn(employeeMngmntList);
//		List<EmployeeMngmntBo> newEmployeeMngmntList = Handler.getAllEmployee();
//		Assert.assertEquals(employeeMngmntList, newEmployeeMngmntList);
//	}
//	@Test(expected = EmployeeMgmntException.class)
//	public void testAddEmployeeException() throws EmployeeMgmntException {
//		List<EmployeeMngmntBo> employeeMngmntList = new ArrayList<EmployeeMngmntBo>();
//		employeeMngmntList.add(empl2);
//		employeeMngmntList.add(empl3);
//		Mockito.when(repo.fetchEmployeeList()).thenReturn(employeeMngmntList);
//		Handler.addEmployee(new EmployeeMngmntBo(144787, "Eliza",
//				LocalDate.of(2019, 01, 01), "Walmart", 20000D, 0, "A"));
//	}	
//
//	@Test
//	public void testAddEmployee() throws EmployeeMgmntException {
//		List<EmployeeMngmntBo> employeeMngmntList = new ArrayList<EmployeeMngmntBo>();
//		employeeMngmntList.add(empl2);
//		employeeMngmntList.add(empl3);
//		Mockito.when(repo.fetchEmployeeList()).thenReturn(employeeMngmntList);
//		List<EmployeeMngmntBo> respose = Handler
//				.addEmployee(new EmployeeMngmntBo(3000, "Eleena", LocalDate
//						.of(1993, 01, 01), "TeamMobile", 5000D, 0, "A"));
//		Assert.assertEquals(employeeMngmntList, respose);
//	}
//	@Test
//	public void fetchEmplyByIdElse(){
//		List<EmployeeMngmntBo> employeeMngmntList = new ArrayList<EmployeeMngmntBo>();
//		Mockito.when(repo.fetchEmployeeList()).thenReturn(employeeMngmntList);
//		Handler.fetchEmplyById(200);
//	} 
//	@Test
//	public void fetchEmplyByBandElse(){
//		List<EmployeeMngmntBo> employeeMngmntList = new ArrayList<EmployeeMngmntBo>();
//		Mockito.when(repo.fetchEmployeeList()).thenReturn(employeeMngmntList);
//		Handler.fetchEmplyByBand("Q");
//	} 
//	@Test
//	public void removeEmployeElse(){
//		List<EmployeeMngmntBo> employeeMngmntList = new ArrayList<EmployeeMngmntBo>();
//		Mockito.when(repo.fetchEmployeeList()).thenReturn(employeeMngmntList);
//		Handler.removeEmployee(14478);
//	} 
//}
