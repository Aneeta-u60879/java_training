package com.eleena.Employee.controller.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.eleena.Employee.bo.EmployeeMngmntBo;
import com.eleena.Employee.bo.UpdateBonus;
import com.eleena.Employee.bo.UpdateExperience;
import com.eleena.Employee.controller.EmployeeMngmntController;
import com.eleena.Employee.dto.EmployeeMngmntDto;
import com.eleena.Employee.handler.impl.EmployeeMgmntHandlerImpl;
import com.eleena.Employee.model.EmployeeMngmnt;
import com.eleena.Employee.model.EmployeeQueryModel;
import com.eleena.Employee.repo.impl.EmployeeMgmntImpRepoImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.MediaType;

public class EmployeeMngmntControllerTest {

	@InjectMocks
	private EmployeeMngmntController controller;
	private MockMvc mockMvc;
	@Mock
	private EmployeeMgmntImpRepoImpl repo;
	@Mock
	private EmployeeMgmntHandlerImpl handler;
	@Mock
	private HttpHeaders headers;
	EmployeeMngmnt emp13 = new EmployeeMngmnt("234", "em15", "Eliza",
			"2011-01-2", "Team1", 24200.0, (float) 3.00, "E");
	EmployeeMngmntDto empl1 = new EmployeeMngmntDto("453", "em15", "Eliza",
			"2011-01-2", "Team1", 24200.0, (float) 3.00, "E");
	EmployeeMngmntBo empl2 = new EmployeeMngmntBo("454", "em16", "Erin",
			"2008-09-2", "Walmart", 420000.0, (float) 5.00, "H");

	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testGetdata() throws Exception {
		Flux<EmployeeMngmnt> emp = Flux.just(emp13);
		Mockito.when(handler.getEmployeeList()).thenReturn(emp);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/all")
				.accept("application/json").contentType("application/json");
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void testAddEmployee() throws Exception {
		Mockito.when(handler.addEmployee(Mockito.any(EmployeeMngmntBo.class)))
				.thenReturn(empl2);
		EmployeeMngmntDto employ1 = new EmployeeMngmntDto();
		employ1.setEmployeeId("emp1");
		employ1.setName("Eleena");
		employ1.setAccountName("kent");
		ObjectMapper mapper = new ObjectMapper();
		String str = mapper.writeValueAsString(employ1);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/addemployee").content(str).accept("application/json")
				.contentType("application/json");
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	@Test
	public void testDeleteEmployee() throws Exception {
		Mono<EmployeeMngmnt> emp = Mono.just(emp13);
		Mockito.when(handler.dltEmploy(Mockito.any())).thenReturn(emp);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/delete/234").accept("application/json")
				.contentType("application/json");
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	@Test
	public void testGetdById() throws Exception {
		Mono<EmployeeMngmnt> emp = Mono.just(emp13);
		Mockito.when(handler.findById(Mockito.any())).thenReturn(emp);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/id/234")
				.accept("application/json").contentType("application/json");
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testGetdByAccount() throws Exception {
		Flux<EmployeeMngmnt> emp = Flux.just(emp13);
		Mockito.when(handler.findByAccount(Mockito.any())).thenReturn(emp);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/ByAccount/walmart").accept("application/json")
				.contentType("application/json");
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void testSaveEmployee() throws Exception {
		Mockito.when(handler.saveEmploy(Mockito.any())).thenReturn(emp13);
		EmployeeMngmnt employ1 = new EmployeeMngmnt();
		employ1.setEmployeeId("emp1");
		employ1.setName("Eleena");
		employ1.setAccountName("kent");
		ObjectMapper mapper = new ObjectMapper();
		String str = mapper.writeValueAsString(employ1);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/save")
				.content(str).accept("application/json")
				.contentType("application/json");
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	@Test
	public void testUpdateExprExce() throws Exception {
		Mockito.when(handler.updateExperience(Mockito.any(UpdateExperience.class)))
				.thenReturn(empl2);
		EmployeeMngmntDto employ1 = new EmployeeMngmntDto();
		employ1.setEmployeeId("emp1");
		employ1.setName("Eleena");
		employ1.setAccountName("kent");
		ObjectMapper mapper = new ObjectMapper();
		String str = mapper.writeValueAsString(employ1);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/update").content(str)
				.accept("application/json")
				.contentType("application/json");
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}
	 @Test 
	 public void testUpdateBonus() throws Exception{
		 Mockito.when(handler.updateBonus(Mockito.any(UpdateBonus.class)))
			.thenReturn(empl2);
	    EmployeeMngmntDto employ1 = new EmployeeMngmntDto();
	    employ1.setEmployeeId("emp1");
	    employ1.setName("Eleena");
	    employ1.setAccountName("kent");
	    ObjectMapper mapper = new ObjectMapper();
	    String str = mapper.writeValueAsString(employ1);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/updatebonus").content(str).accept("application/json")
				.contentType("application/json");
	    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	    assertNotNull(result);
	    MockHttpServletResponse response = result.getResponse();
	    assertNotNull(response);
	    assertEquals(HttpStatus.OK.value(), response.getStatus());
			
	 }
	 @Test
	 public void testAccountByLimit() throws Exception{
		 EmployeeMngmnt accModel = new EmployeeMngmnt();
		 accModel.setEmployeeId("123");
		 accModel.setName("Erin");
		 List<EmployeeMngmnt> byAccnt = new ArrayList<EmployeeMngmnt>();
		 byAccnt.add(accModel);
		 Mockito.when(handler.getEmpByNameQuery(Mockito.any(String.class),Mockito.any(Integer.class),Mockito.any(Integer.class))).thenReturn(byAccnt);
		 RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/custom/walmart/0/1").accept("application/json")
					.contentType("application/json");
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	        assertNotNull(result);
	        MockHttpServletResponse response = result.getResponse();
	        assertNotNull(response);
	        assertEquals(HttpStatus.OK.value(), response.getStatus());;
	 }
	 @Test
	 public void testAccountWithoutLimit() throws Exception{
		 EmployeeQueryModel accModel = new EmployeeQueryModel();
		accModel.setName("Erin");
		accModel.setId("1");
		 List<EmployeeQueryModel> byAccnt = new ArrayList<EmployeeQueryModel>();
		 byAccnt.add(accModel);
		 Mockito.when(handler.getEmpByNameQury(Mockito.any(String.class))).thenReturn( byAccnt);
		 RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/custom/walmart").accept("application/json")
					.contentType("application/json");
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	        assertNotNull(result);
	        MockHttpServletResponse response = result.getResponse();
	        assertNotNull(response);
	        assertEquals(HttpStatus.OK.value(), response.getStatus());;
	 }
	 @Test 
	 public void testUpdateAllowance() throws Exception{
		 List<EmployeeMngmntBo> byAccnt = new ArrayList<EmployeeMngmntBo>();
		 byAccnt.add(empl2);
		 Mockito.when(handler.emplyAllowance(Mockito.any(Integer.class))).thenReturn(byAccnt);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/allowanceEmply/10").accept("application/json")
				.contentType("application/json");
	    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	    assertNotNull(result);
	    MockHttpServletResponse response = result.getResponse();
	    assertNotNull(response);
	    assertEquals(HttpStatus.OK.value(), response.getStatus());
			
	 }

}
//
// @Test
// public void addEmployee() throws Exception {
// List<EmployeeMngmntBo> newEmploy = new ArrayList<EmployeeMngmntBo>();
// newEmploy.add(empl1);
// Mockito.when(handler.addEmployee(Mockito.any())).thenReturn(newEmploy);
// ObjectMapper mapper = new ObjectMapper();
// EmployeeMngmntDto employDto = new EmployeeMngmntDto();
// employDto.setEmployeeName("Erin");
// employDto.setEmplyAccountName("Walmart");
// employDto.setEmplyExperience(7);
// String str = mapper.writeValueAsString(employDto);
// RequestBuilder requestBuilder = MockMvcRequestBuilders
// .post("/addEmploy").content(str).accept("application/json")
// .contentType("application/json");
// assertNotNull(requestBuilder);
// MvcResult result = mockMvc.perform(requestBuilder).andReturn();
// assertNotNull(result);
// MockHttpServletResponse response = result.getResponse();
// assertNotNull(response);
// assertEquals(HttpStatus.OK.value(), response.getStatus());
// }
//
// @Test
// public void addEmployeeConflicts() throws Exception {
// List<EmployeeMngmntBo> newEmploy = new ArrayList<EmployeeMngmntBo>();
// // newEmploy.add(empl1);
// Mockito.when(handler.addEmployee(Mockito.any())).thenThrow(
// new EmployeeMgmntException());
// // Mockito.doThrow(new
// // EmployeeMgmntException()).when(handler.addEmployee(Mockito.any()));
// ObjectMapper mapper = new ObjectMapper();
// EmployeeMngmntDto employDto = new EmployeeMngmntDto();
// employDto.setEmployeeName("Erin");
// employDto.setEmplyAccountName("Walmart");
// employDto.setEmplyExperience(7);
// String str = mapper.writeValueAsString(employDto);
// RequestBuilder requestBuilder = MockMvcRequestBuilders
// .post("/addEmploy").content(str).accept("application/json")
// .contentType("application/json");
// assertNotNull(requestBuilder);
// MvcResult result = mockMvc.perform(requestBuilder).andReturn();
// assertNotNull(result);
// MockHttpServletResponse response = result.getResponse();
// assertNotNull(response);
// assertEquals(HttpStatus.CONFLICT.value(), response.getStatus());
// }
//
// @Test
// public void testfetchData() throws Exception {
// List<EmployeeMngmntBo> employeeMngmntList = new ArrayList<>();
// employeeMngmntList.add(empl1);
// Mockito.when(handler.getAllEmployee()).thenReturn(employeeMngmntList);
// RequestBuilder requestBuilder = MockMvcRequestBuilders
// .get("/getemployes").accept("application/json")
// .contentType("application/json");
// MvcResult result = mockMvc.perform(requestBuilder).andReturn();
// assertNotNull(result);
// MockHttpServletResponse response = result.getResponse();
// assertNotNull(response);
// assertEquals(HttpStatus.OK.value(), response.getStatus());
// }
//
// @Test
// public void testDltEmply() throws Exception {
// List<EmployeeMngmntBo> employeeMngmntList = new ArrayList<>();
// employeeMngmntList.add(empl1);
// Mockito.when(handler.deltAllEmply(Mockito.any())).thenReturn(
// employeeMngmntList);
// ObjectMapper mapper = new ObjectMapper();
// EmployeeMngmntDto employDto = new EmployeeMngmntDto();
// employDto.setEmplyBand("A");
// employDto.setEmplyAccountName("Walmart");
// employDto.setEmplyExperience(9);
// String str = mapper.writeValueAsString(employDto);
// RequestBuilder requestBuilder = MockMvcRequestBuilders
// .patch("/dltEmply").content(str).accept("application/json")
// .contentType("application/json");
// assertNotNull(requestBuilder);
// MvcResult result = mockMvc.perform(requestBuilder).andReturn();
// assertNotNull(result);
// MockHttpServletResponse response = result.getResponse();
// assertNotNull(response);
// assertEquals(HttpStatus.OK.value(), response.getStatus());
// }
//
// @Test
// public void testDltEmplyConflicts() throws Exception {
// List<EmployeeMngmntBo> employeeMngmntList = new ArrayList<>();
// // employeeMngmntList.add(empl1);
// Mockito.when(handler.deltAllEmply(Mockito.any())).thenThrow(
// new EmployeeMgmntException());
// ObjectMapper mapper = new ObjectMapper();
// EmployeeMngmntDto employDto = new EmployeeMngmntDto();
// employDto.setEmplyBand("A");
// employDto.setEmplyAccountName("Walmart");
// employDto.setEmplyExperience(9);
// String str = mapper.writeValueAsString(employDto);
// RequestBuilder requestBuilder = MockMvcRequestBuilders
// .patch("/dltEmply").content(str).accept("application/json")
// .contentType("application/json");
// assertNotNull(requestBuilder);
// MvcResult result = mockMvc.perform(requestBuilder).andReturn();
// assertNotNull(result);
// MockHttpServletResponse response = result.getResponse();
// assertNotNull(response);
// assertEquals(HttpStatus.CONFLICT.value(), response.getStatus());
// }
//
// @Test
// public void testupdateEmply() throws Exception {
// List<EmployeeMngmntBo> employeeMngmntList = new ArrayList<>();
// employeeMngmntList.add(empl1);
// Mockito.when(handler.updateExperience(Mockito.any())).thenReturn(
// employeeMngmntList);
// ObjectMapper mapper = new ObjectMapper();
// EmployeeMngmntDto employDto = new EmployeeMngmntDto();
// employDto.setEmplyBand("A");
// employDto.setEmplyAccountName("Walmart");
// employDto.setEmplyExperience(9);
// String str = mapper.writeValueAsString(employDto);
// RequestBuilder requestBuilder = MockMvcRequestBuilders
// .put("/updateEmply").content(str).accept("application/json")
// .contentType("application/json");
// assertNotNull(requestBuilder);
// MvcResult result = mockMvc.perform(requestBuilder).andReturn();
// assertNotNull(result);
// MockHttpServletResponse response = result.getResponse();
// assertNotNull(response);
// assertEquals(HttpStatus.OK.value(), response.getStatus());
// }
//
// @Test
// public void testupdateEmplyConflicts() throws Exception {
// List<EmployeeMngmntBo> employeeMngmntList = new ArrayList<>();
// // employeeMngmntList.add(empl1);
// Mockito.when(handler.updateExperience(Mockito.any())).thenReturn(
// employeeMngmntList);
// ObjectMapper mapper = new ObjectMapper();
// EmployeeMngmntDto employDto = new EmployeeMngmntDto();
// employDto.setEmplyBand("A");
// employDto.setEmplyAccountName("Walmart");
// employDto.setEmplyExperience(9);
// String str = mapper.writeValueAsString(employDto);
// RequestBuilder requestBuilder = MockMvcRequestBuilders
// .put("/updateEmply").content(str).accept("application/json")
// .contentType("application/json");
// assertNotNull(requestBuilder);
// MvcResult result = mockMvc.perform(requestBuilder).andReturn();
// assertNotNull(result);
// MockHttpServletResponse response = result.getResponse();
// assertNotNull(response);
// assertEquals(HttpStatus.CONFLICT.value(), response.getStatus());
// }
//
// @Test
// public void testupdateBonus() throws Exception {
// List<EmployeeMngmntBo> employeeMngmntList = new ArrayList<>();
// employeeMngmntList.add(empl1);
// Mockito.when(handler.updateBonus(Mockito.any())).thenReturn(
// employeeMngmntList);
// ObjectMapper mapper = new ObjectMapper();
// EmployeeMngmntDto employDto = new EmployeeMngmntDto();
// employDto.setEmployeeId(144784);
// employDto.setEmplySalary(20000d);
// employDto.setEmplyExperience(9);
// String str = mapper.writeValueAsString(employDto);
// RequestBuilder requestBuilder = MockMvcRequestBuilders
// .patch("/updatebonus").content(str).accept("application/json")
// .contentType("application/json");
// assertNotNull(requestBuilder);
// MvcResult result = mockMvc.perform(requestBuilder).andReturn();
// assertNotNull(result);
// MockHttpServletResponse response = result.getResponse();
// assertNotNull(response);
// assertEquals(HttpStatus.OK.value(), response.getStatus());
// }
//
// @Test
// public void testupdateBonusConflicts() throws Exception {
// List<EmployeeMngmntBo> employeeMngmntList = new ArrayList<>();
// // employeeMngmntList.add(empl1);
// Mockito.when(handler.updateBonus(Mockito.any())).thenReturn(
// employeeMngmntList);
// ObjectMapper mapper = new ObjectMapper();
// EmployeeMngmntDto employDto = new EmployeeMngmntDto();
// employDto.setEmployeeId(144784);
// employDto.setEmplySalary(20000d);
// employDto.setEmplyExperience(9);
// String str = mapper.writeValueAsString(employDto);
// RequestBuilder requestBuilder = MockMvcRequestBuilders
// .patch("/updatebonus").content(str).accept("application/json")
// .contentType("application/json");
// assertNotNull(requestBuilder);
// MvcResult result = mockMvc.perform(requestBuilder).andReturn();
// assertNotNull(result);
// MockHttpServletResponse response = result.getResponse();
// assertNotNull(response);
// assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
// }
//
// @Test
// public void fetchById() throws Exception {
// Mockito.when(handler.fetchEmplyById(Mockito.any())).thenReturn(empl1);
// RequestBuilder requestBuilder = MockMvcRequestBuilders
// .get("/getById/144784").accept("application/json")
// .contentType("application/json");
// MvcResult result = mockMvc.perform(requestBuilder).andReturn();
// assertNotNull(result);
// MockHttpServletResponse response = result.getResponse();
// assertNotNull(response);
// assertEquals(HttpStatus.OK.value(), response.getStatus());
// }
//
// @Test
// public void fetchByIdConflicts() throws Exception {
// Mockito.when(handler.fetchEmplyById(Mockito.any())).thenReturn(null);
// RequestBuilder requestBuilder = MockMvcRequestBuilders
// .get("/getById/2000").accept("application/json")
// .contentType("application/json");
// MvcResult result = mockMvc.perform(requestBuilder).andReturn();
// assertNotNull(result);
// MockHttpServletResponse response = result.getResponse();
// assertNotNull(response);
// assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
// }
//
// @Test
// public void fetchByband() throws Exception {
// List<EmployeeMngmntBo> employeeMngmntList = new ArrayList<>();
// employeeMngmntList.add(empl1);
// Mockito.when(handler.fetchEmplyByBand(Mockito.any())).thenReturn(
// employeeMngmntList);
// RequestBuilder requestBuilder = MockMvcRequestBuilders
// .get("/getbyBand").param("emplyBand", "emplyBnd")
// .accept("application/json").contentType("application/json");
// MvcResult result = mockMvc.perform(requestBuilder).andReturn();
// assertNotNull(result);
// MockHttpServletResponse response = result.getResponse();
// assertNotNull(response);
// assertEquals(HttpStatus.OK.value(), response.getStatus());
// }
//
// @Test
// public void fetchBybandConflicts() throws Exception {
// List<EmployeeMngmntBo> employeeMngmntList = new ArrayList<>();
// // employeeMngmntList.add(empl1);
// Mockito.when(handler.fetchEmplyByBand(Mockito.any())).thenReturn(
// employeeMngmntList);
// RequestBuilder requestBuilder = MockMvcRequestBuilders
// .get("/getbyBand").param("emplyBand", "emplyBnd")
// .accept("application/json").contentType("application/json");
// MvcResult result = mockMvc.perform(requestBuilder).andReturn();
// assertNotNull(result);
// MockHttpServletResponse response = result.getResponse();
// assertNotNull(response);
// assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
// }
//
// @Test
// public void testAddFreshers() throws Exception {
// List<EmployeeMngmntBo> employeeMngmntList = new ArrayList<>();
// employeeMngmntList.add(empl1);
// EmployeeMngmntDto employ1 = new EmployeeMngmntDto();
// List<EmployeeMngmntDto> employList = new ArrayList<EmployeeMngmntDto>();
// Mockito.when(handler.addFreshers(Mockito.any())).thenReturn(
// employeeMngmntList);
// empl1.setEmplyBand("A");
// empl1.setEmplyAccountName("Walmart");
// empl1.setEmplyExperience(9);
// employList.add(employ1);
// ObjectMapper mapper = new ObjectMapper();
// String str = mapper.writeValueAsString(employList);
// RequestBuilder requestBuilder = MockMvcRequestBuilders
// .post("/addFreshers").content(str).accept("application/json")
// .contentType("application/json");
// MvcResult result = mockMvc.perform(requestBuilder).andReturn();
// assertNotNull(result);
// MockHttpServletResponse response = result.getResponse();
// assertNotNull(response);
// assertEquals(HttpStatus.OK.value(), response.getStatus());
//
// }
//
// @Test
// public void testAddFreshersConflicts() throws Exception {
// List<EmployeeMngmntBo> employeeMngmntList = new ArrayList<>();
// // employeeMngmntList.add(empl1);
// EmployeeMngmntDto employ1 = new EmployeeMngmntDto();
// List<EmployeeMngmntDto> employList = new ArrayList<EmployeeMngmntDto>();
// Mockito.when(handler.addFreshers(Mockito.any())).thenReturn(
// employeeMngmntList);
// empl1.setEmplyBand("A");
// empl1.setEmplyAccountName("Walmart");
// empl1.setEmplyExperience(9);
// employList.add(employ1);
// ObjectMapper mapper = new ObjectMapper();
// String str = mapper.writeValueAsString(employList);
// RequestBuilder requestBuilder = MockMvcRequestBuilders
// .post("/addFreshers").content(str).accept("application/json")
// .contentType("application/json");
// MvcResult result = mockMvc.perform(requestBuilder).andReturn();
// assertNotNull(result);
// MockHttpServletResponse response = result.getResponse();
// assertNotNull(response);
// assertEquals(HttpStatus.CONFLICT.value(), response.getStatus());
//
// }
//
// @Test
// public void updateAllowance() throws Exception {
// List<EmployeeMngmntBo> employeeMngmntList = new ArrayList<>();
// employeeMngmntList.add(empl1);
// Mockito.when(handler.emplyAllowance(Mockito.anyInt())).thenReturn(
// employeeMngmntList);
// List<EmployeeMngmntDto> employList = new ArrayList<EmployeeMngmntDto>();
// EmployeeMngmntDto employ1 = new EmployeeMngmntDto();
// employList.add(employ1);
// ObjectMapper mapper = new ObjectMapper();
// String str = mapper.writeValueAsString(employList);
// RequestBuilder requestBuilder = MockMvcRequestBuilders
// .put("/allowanceEmply").param("emplySalary", "200")
// .content(str).accept("application/json")
// .contentType("application/json");
// assertNotNull(requestBuilder);
// MvcResult result = mockMvc.perform(requestBuilder).andReturn();
// assertNotNull(result);
// MockHttpServletResponse response = result.getResponse();
// assertNotNull(response);
// assertEquals(HttpStatus.OK.value(), response.getStatus());
// }
// @Test
// public void updateAllowanceConflicts() throws Exception {
// List<EmployeeMngmntBo> employeeMngmntList = new ArrayList<>();
// // employeeMngmntList.add(empl1);
// Mockito.when(handler.emplyAllowance(Mockito.anyInt())).thenReturn(
// employeeMngmntList);
// //data transfer object(str) passed to the http request
// List<EmployeeMngmntDto> employList = new ArrayList<EmployeeMngmntDto>();
// EmployeeMngmntDto employ1 = new EmployeeMngmntDto();
// employList.add(employ1);
// ObjectMapper mapper = new ObjectMapper();
// String str = mapper.writeValueAsString(employList);
// RequestBuilder requestBuilder = MockMvcRequestBuilders
// .put("/allowanceEmply").param("emplySalary", "200")
// .content(str).accept("application/json")
// .contentType("application/json");
// assertNotNull(requestBuilder);
// MvcResult result = mockMvc.perform(requestBuilder).andReturn();
// assertNotNull(result);
// MockHttpServletResponse response = result.getResponse();
// assertNotNull(response);
// assertEquals(HttpStatus.CONFLICT.value(), response.getStatus());
// }
// @Test
// public void remobeById() throws Exception {
// List<EmployeeMngmntBo> employeeMngmntList = new ArrayList<>();
// employeeMngmntList.add(empl1);
// Mockito.when(handler.removeEmployee(Mockito.any())).thenReturn(
// employeeMngmntList);
// RequestBuilder requestBuilder = MockMvcRequestBuilders
// .get("/removeById").param("employeeId", "144784")
// .accept("application/json").contentType("application/json");
// MvcResult result = mockMvc.perform(requestBuilder).andReturn();
// assertNotNull(result);
// MockHttpServletResponse response = result.getResponse();
// assertNotNull(response);
// assertEquals(HttpStatus.OK.value(), response.getStatus());
// }
// @Test
// public void remobeByIdConflicts() throws Exception {
// List<EmployeeMngmntBo> employeeMngmntList = new ArrayList<>();
// // employeeMngmntList.add(empl1);
// Mockito.when(handler.removeEmployee(Mockito.any())).thenReturn(
// employeeMngmntList);
// RequestBuilder requestBuilder = MockMvcRequestBuilders
// .get("/removeById").param("employeeId", "144784")
// .accept("application/json").contentType("application/json");
// MvcResult result = mockMvc.perform(requestBuilder).andReturn();
// assertNotNull(result);
// MockHttpServletResponse response = result.getResponse();
// assertNotNull(response);
// assertEquals(HttpStatus.CONFLICT.value(), response.getStatus());
// }
//
// }
