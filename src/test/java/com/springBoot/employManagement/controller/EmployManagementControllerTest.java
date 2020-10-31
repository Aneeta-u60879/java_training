package com.springBoot.employManagement.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springBoot.employManagement.bo.EmployBO;
import com.springBoot.employManagement.dto.EmployDto;
import com.springBoot.employManagement.dto.UpdateAllowanceDto;
import com.springBoot.employManagement.dto.UpdateBonusDto;
import com.springBoot.employManagement.dto.UpdateExperienceDto;
import com.springBoot.employManagement.handler.impl.EmployManagementHandlerImpl;
import com.springBoot.employManagement.model.Employee;
import com.springBoot.employManagement.model.Model;

/**
 * Test case for controller
 * 
 * @author 144785
 *
 */
public class EmployManagementControllerTest {
	@InjectMocks
	private EmployManagementController controller;

	private MockMvc mockMvc;

	@Mock
	private EmployManagementHandlerImpl handler;

	EmployBO employ1 = new EmployBO(1001, "Sachin", "2019, 11, 1", "Walmart",
			20000.0, 0, "A", "U1");

	Employee employ3 = new Employee(1003, "Sachin", "2019, 11, 1", "Walmart",
			20000.0, 0, "A", "U1");

	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	/**
	 * test for getEmployeeList method
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetEmployeeList() throws Exception {
		Flux<Employee> newEmploy = Flux.just(employ3);
		Mockito.when(handler.getEmployeeList()).thenReturn(newEmploy);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/all")
				.accept("application/json").contentType("application/json");
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/**
	 * test for getEmployeeByAccnt method
	 * 
	 * @throws Exception
	 */
	@Test
	public void tesGetEmployeeByAccnt() throws Exception {
		Flux<Employee> newEmploy = Flux.just(employ3);
		Mockito.when(handler.getEmployeeByAccountName(Mockito.any()))
				.thenReturn(newEmploy);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/account/Walmart").param("acntName", "accountName")
				.accept("application/json").contentType("application/json");
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/**
	 * test for else case of getEmployeeByAccnt method
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetEmployeeByAccntElse() throws Exception {
		Flux<Employee> newEmploy = null;
		Mockito.when(handler.getEmployeeByAccountName(Mockito.any()))
				.thenReturn(newEmploy);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/account/Walmart").param("acntName", "accountName")
				.accept("application/json").contentType("application/json");
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.CONFLICT.value(), response.getStatus());
	}

	/**
	 * test for getEmployeeByBand method
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetEmployeeByBand() throws Exception {
		Flux<Employee> newEmploy = Flux.just(employ3);
		Mockito.when(handler.getEmployeeByBand(Mockito.any())).thenReturn(
				newEmploy);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/band/A")
				.param("bands", "band").accept("application/json")
				.contentType("application/json");
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/**
	 * tes for else case of getEmployeeByBand
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetEmployeeByBandElse() throws Exception {
		Flux<Employee> newEmploy = null;
		Mockito.when(handler.getEmployeeByBand(Mockito.any())).thenReturn(
				newEmploy);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/band/A")
				.param("bands", "band").accept("application/json")
				.contentType("application/json");
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.CONFLICT.value(), response.getStatus());
	}

	/**
	 * test for getEmployeeByEmpId mehod
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetEmployeeByEmpId() throws Exception {
		Flux<Employee> newEmploy = Flux.just(employ3);
		Mockito.when(handler.getEmployeeByEmployId(Mockito.any())).thenReturn(
				newEmploy);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/employId/1003").param("employId", "employId")
				.accept("application/json").contentType("application/json");
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/**
	 * test for else case of getEmployeeById
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetEmployeeByIdElse() throws Exception {
		Flux<Employee> newEmploy = null;
		Mockito.when(handler.getEmployeeByEmployId(Mockito.any())).thenReturn(
				newEmploy);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/employId/1003").param("employId", "employId")
				.accept("application/json").contentType("application/json");
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.CONFLICT.value(), response.getStatus());
	}

	/**
	 * test for deleteEmployee method
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDeleteEmployee() throws Exception {
		Mono<Employee> newEmploy = Mono.just(employ3);
		Mockito.when(handler.deleteEmployee(Mockito.any())).thenReturn(
				newEmploy);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/delete/1003").accept("application/json")
				.contentType("application/json");
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/**
	 * test for testAddFreshers method
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddFreshers() throws Exception {
		EmployDto employ = new EmployDto();
		List<EmployBO> employeeMngmntList = new ArrayList<>();
		List<EmployBO> employeeList = new ArrayList<>();
		employeeList.add(employ1);
		Mockito.when(handler.addFreshers(Mockito.any())).thenReturn(
				employeeList);
		employ.setEmployId(109);
		employ.setName("sanjay");
		employ.setAccountName("Walmart");
		employeeMngmntList.add(employ1);
		ObjectMapper mapper = new ObjectMapper();
		String str = mapper.writeValueAsString(employeeMngmntList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/addFreshers").content(str).accept("application/json")
				.contentType("application/json");
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
	}

	/**
	 * test for testAddFreshers conflict
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddFreshersConflicts() throws Exception {
		EmployDto employ5 = new EmployDto();
		List<EmployBO> employeeMngmntList = new ArrayList<>();
		List<EmployBO> employeeList = new ArrayList<>();
		employeeList.add(employ1);
		Mockito.when(handler.addFreshers(Mockito.any())).thenReturn(null);
		employ5.setEmployId(109);
		employ5.setName("sanjay");
		employ5.setAccountName("Walmart");
		employeeMngmntList.add(employ1);
		ObjectMapper mapper = new ObjectMapper();
		String str = mapper.writeValueAsString(employeeMngmntList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/addFreshers").content(str).accept("application/json")
				.contentType("application/json");
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);

	}

	/**
	 * test for saveEmployee method
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSaveEmployee() throws Exception {
		Mockito.when(handler.saveEmployee(Mockito.any())).thenReturn(employ1);
		ObjectMapper mapper = new ObjectMapper();
		EmployDto employ4 = new EmployDto();
		employ4.setAccountName("walmart");
		employ4.setBand("A");
		employ4.setExperience(5);

		String str = mapper.writeValueAsString(employ4);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/save")
				.content(str).accept("application/json")
				.contentType("application/json");
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/**
	 * test for updateExperience method
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdateExperience() throws Exception {
		Mockito.when(handler.updateExperience(Mockito.any())).thenReturn(
				employ1);
		ObjectMapper mapper = new ObjectMapper();
		UpdateExperienceDto employ4 = new UpdateExperienceDto();
		employ4.setId("U4");
		employ4.setBand("A");
		employ4.setExperience(1);

		String str = mapper.writeValueAsString(employ4);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/updateexpr").content(str).accept("application/json")
				.contentType("application/json");
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/**
	 * test for updateExperience method
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdateBonus() throws Exception {
		Mockito.when(handler.updateBonus(Mockito.any())).thenReturn(employ1);
		ObjectMapper mapper = new ObjectMapper();
		UpdateBonusDto employ4 = new UpdateBonusDto();
		employ4.setId("U4");
		employ4.setBonus(3.0);

		String str = mapper.writeValueAsString(employ4);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/updatebonus").content(str).accept("application/json")
				.contentType("application/json");
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/**
	 * test for updateAllowance method
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdateAllowance() throws Exception {
		Mockito.when(handler.updateExperience(Mockito.any())).thenReturn(
				employ1);
		ObjectMapper mapper = new ObjectMapper();
		UpdateAllowanceDto employ4 = new UpdateAllowanceDto();
		employ4.setId("U4");
		employ4.setAllowance(5);

		String str = mapper.writeValueAsString(employ4);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/updateallowance").content(str)
				.accept("application/json").contentType("application/json");
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/**
	 * test for getEmployeeByCustomName method
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetEmployeeByCustomName() throws Exception {
		List<Employee> newEmploy = new ArrayList<Employee>();
		newEmploy.add(employ3);
		Mockito.when(
				handler.getEmpByNameQuery(Mockito.any(), Mockito.any(),
						Mockito.any())).thenReturn(newEmploy);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/custom/Walmart/0/1").param("name", "name")
				.param("offset", "offset").param("limit", "limit")
				.accept("application/json").contentType("application/json");
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/**
	 * test for getEmployeeById method
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetEmployeeById() throws Exception {
		Model model = new Model();
		model.setEmployId(1001);
		model.setId("U1");
		List<Model> newEmploy = new ArrayList<Model>();
		newEmploy.add(model);
		Mockito.when(
				handler.getEmpByIdQuery(Mockito.any(), Mockito.any(),
						Mockito.any())).thenReturn(newEmploy);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/id/Walmart/0/1").param("name", "name")
				.param("offset", "offset").param("limit", "limit")
				.accept("application/json").contentType("application/json");
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

}
