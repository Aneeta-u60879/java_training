package com.neena.employee.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
import com.neena.employee.bo.EmployeeMangmntBO;
import com.neena.employee.dto.EmployeeManagementDto;
import com.neena.employee.dto.UpdateBonusDto;
import com.neena.employee.dto.UpdateExperienceDto;
import com.neena.employee.handler.impl.EmployeeMngmntHandler;
import com.neena.employee.model.Employee;
import com.neena.employee.model.Model;

public class EmployeeMnagementControllerTest {

	@InjectMocks
	private EmployeeMangmntController Controller;
	private MockMvc mockMVc;

	@Mock
	private EmployeeMngmntHandler handler;

	EmployeeMangmntBO employeemngmnt = new EmployeeMangmntBO("1", 100, "Eliza",
			"2011/01/01", "Walmart", 32000, 9, "C");
	Employee employee = new Employee("1", 100, "Eliza", "2011/01/01",
			"Walmart", 32000, 9, "C");

	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.mockMVc = MockMvcBuilders.standaloneSetup(Controller).build();
	}

	/**
	 *  Test for get all employees
	 * @throws Exception
	 */
	@Test
	public void testGetEmployeeList() throws Exception {
		Flux<Employee> newEmploy = Flux.just(employee);
		Mockito.when(handler.getEmployeeList()).thenReturn(newEmploy);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/all")
				.accept("application/json").contentType("application/json");
		assertNotNull(requestBuilder);
		MvcResult result = mockMVc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
  
	/**
	 * Test for getting employee by account
	 * @throws Exception
	 */
	@Test
	public void testGetEmployeeByAccnt() throws Exception {
		Flux<Employee> newEmploy = Flux.just(employee);
		Mockito.when(handler.findByAccountName(Mockito.any())).thenReturn(
				newEmploy);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/ByAccount/Walmart").accept("application/json")
				.contentType("application/json");
		assertNotNull(requestBuilder);
		MvcResult result = mockMVc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/**
	 * Test for the Conflict Status
	 * @throws Exception
	 */
	@Test
	public void testGetEmployeeByAccntConflicts() throws Exception {
		Flux<Employee> newEmploy = null;
		Mockito.when(handler.findByAccountName(Mockito.any())).thenReturn(
				newEmploy);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/ByAccount/Walmart").accept("application/json")
				.contentType("application/json");
		assertNotNull(requestBuilder);
		MvcResult result = mockMVc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.CONFLICT.value(), response.getStatus());
	}

	/**
	 * Test for getting employee by Id
	 * @throws Exception
	 */
	@Test
	public void testGetById() throws Exception {
		Mono<Employee> newEmploy = Mono.just(employee);
		Mockito.when(handler.findById(Mockito.any())).thenReturn(newEmploy);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/id/1")
				.accept("application/json").contentType("application/json");
		assertNotNull(requestBuilder);
		MvcResult result = mockMVc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/**
	 * Test for BadRequest if no employee found by a given Id
	 * @throws Exception
	 */
	@Test
	public void testGetByIdtNosuchEmployee() throws Exception {
		Mono<Employee> newEmploy = null;
		Mockito.when(handler.findById(Mockito.any())).thenReturn(newEmploy);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/id/1")
				.accept("application/json").contentType("application/json");
		assertNotNull(requestBuilder);
		MvcResult result = mockMVc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
	}

	/**
	 * Test for getting employee by Id
	 * @throws Exception
	 */
	@Test
	public void testSearchById() throws Exception {
		Mockito.when(handler.searchById(Mockito.any())).thenReturn(
				employeemngmnt);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/searchbyid/1").accept("application/json")
				.contentType("application/json");
		assertNotNull(requestBuilder);
		MvcResult result = mockMVc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/**
	 * Test for saving an employee
	 * @throws Exception
	 */
	@Test
	public void testSaveEmployee() throws Exception {
		Mockito.when(handler.saveEmploy(Mockito.any())).thenReturn(employee);
		ObjectMapper mapper = new ObjectMapper();
		Employee employ4 = new Employee();
		employ4.setAccountName("Walmart");
		employ4.setEmplyBand("A");
		employ4.setExperience(5);
		String str = mapper.writeValueAsString(employ4);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/save")
				.content(str).accept("application/json")
				.contentType("application/json");
		assertNotNull(requestBuilder);
		MvcResult result = mockMVc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/**
	 * Test for Conflicts in saving an employee
	 * @throws Exception
	 */
	@Test
	public void testSaveEmployeeconflicts() throws Exception {
		Mockito.when(handler.saveEmploy(Mockito.any())).thenReturn(null);
		ObjectMapper mapper = new ObjectMapper();
		Employee employ4 = new Employee();
		employ4.setAccountName("Walmart");
		employ4.setEmplyBand("A");
		employ4.setExperience(5);
		String str = mapper.writeValueAsString(employ4);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/save")
				.content(str).accept("application/json")
				.contentType("application/json");
		assertNotNull(requestBuilder);
		MvcResult result = mockMVc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.CONFLICT.value(), response.getStatus());
	}

	/**
	 * Test for adding an employee
	 * @throws Exception
	 */
	@Test
	public void testAddEmployee() throws Exception {

		Mockito.when(handler.addEmployee(Mockito.any())).thenReturn(
				employeemngmnt);
		ObjectMapper mapper = new ObjectMapper();
		EmployeeManagementDto employ4 = new EmployeeManagementDto();
		employ4.setAccountName("Walmart");
		employ4.setEmplyBand("A");
		employ4.setExperience(5);

		String str = mapper.writeValueAsString(employ4);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/addemployee").content(str).accept("application/json")
				.contentType("application/json");
		assertNotNull(requestBuilder);
		MvcResult result = mockMVc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/**
	 * Test for updating experience and band of an employee
	 * @throws Exception
	 */
	@Test
	public void testupdateEmployee() throws Exception {
		UpdateExperienceDto update = new UpdateExperienceDto();
		EmployeeMangmntBO employee = new EmployeeMangmntBO("1", 100, "Eliza",
				"2011/01/01", "Walmart", 32000, 9, "C");
		Mockito.when(handler.updateEmployee(Mockito.any()))
				.thenReturn(employee);
		update.setId("1");

		update.setBand("A");
		update.setExperience(3);
		ObjectMapper mapper = new ObjectMapper();
		String str = mapper.writeValueAsString(update);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/updateexp").content(str).accept("application/json")
				.contentType("application/json");
		MvcResult result = mockMVc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	/**
	 * Test for updating salary of an employee
	 * @throws Exception
	 */
	@Test
	public void testUpdateBonus() throws Exception {
		UpdateBonusDto update = new UpdateBonusDto();
		Mockito.when(handler.updateBonus(Mockito.any())).thenReturn(
				employeemngmnt);
		update.setId("1");
		update.setBonus(10000);
		ObjectMapper mapper = new ObjectMapper();
		String str = mapper.writeValueAsString(update);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/updatebonus").content(str).accept("application/json")
				.contentType("application/json");
		MvcResult result = mockMVc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	/**
	 * Test for getting employee list by custom query
	 * @throws Exception
	 */
	@Test
	public void getEmployeeByCustomName() throws Exception {
		List<Employee> newEmploy = new ArrayList<Employee>();
		newEmploy.add(employee);
		Mockito.when(
				handler.getEmpByNameQuery(Mockito.any(), Mockito.any(),
						Mockito.any())).thenReturn(newEmploy);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/custom/Walmart/0/1").param("name", "name")
				.param("offset", "offset").param("limit", "limit")
				.accept("application/json").contentType("application/json");
		assertNotNull(requestBuilder);
		MvcResult result = mockMVc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/**
	 * Test for getting custom details using custom query
	 * @throws Exception
	 */
	@Test
	public void TestGetEmployeeByID() throws Exception {
		Model model = new Model();
		model.setId("1");
		model.setEmployeeId(110);
		List<Model> newEmployee = new ArrayList<>();
		newEmployee.add(model);
		Mockito.when(
				handler.getEmpIdByNameQuery(Mockito.any(), Mockito.any(),
						Mockito.any())).thenReturn(newEmployee);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/id/Walmart/0/1").param("name", "name")
				.param("offset", "offset").param("limit", "limit")
				.accept("application/json").contentType("application/json");
		assertNotNull(requestBuilder);
		MvcResult result = mockMVc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/**
	 * Test for addinf freshers to the table
	 * @throws Exception
	 */
	@Test
	public void testAddFreshers() throws Exception {
		EmployeeManagementDto employ1 = new EmployeeManagementDto();
		List<EmployeeMangmntBO> employeeMngmntList = new ArrayList<>();
		List<EmployeeMangmntBO> employeeList = new ArrayList<>();
		employeeList.add(employeemngmnt);
		Mockito.when(handler.addFreshers(Mockito.any())).thenReturn(
				employeeList);
		employ1.setEmployeeId(109);
		employ1.setEmployeeName("eleena");
		employ1.setAccountName("Walmart");
		employeeMngmntList.add(employeemngmnt);
		ObjectMapper mapper = new ObjectMapper();
		String str = mapper.writeValueAsString(employeeMngmntList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/addFreshers").content(str).accept("application/json")
				.contentType("application/json");
		assertNotNull(requestBuilder);
		MvcResult result = mockMVc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	/**
	 * Test for conflicts in adding freshers
	 * @throws Exception
	 */
	@Test
	public void testAddFreshersConflicts() throws Exception {
		EmployeeManagementDto employ1 = new EmployeeManagementDto();
		List<EmployeeMangmntBO> employeeMngmntList = new ArrayList<>();
		List<EmployeeMangmntBO> employeeList = new ArrayList<>();
		employeeList.add(employeemngmnt);
		Mockito.when(handler.addFreshers(Mockito.any())).thenReturn(null);
		employ1.setEmployeeId(109);
		employ1.setEmployeeName("eleena");
		employ1.setAccountName("Walmart");
		employeeMngmntList.add(employeemngmnt);
		ObjectMapper mapper = new ObjectMapper();
		String str = mapper.writeValueAsString(employeeMngmntList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/addFreshers").content(str).accept("application/json")
				.contentType("application/json");
		assertNotNull(requestBuilder);
		MvcResult result = mockMVc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.CONFLICT.value(), response.getStatus());
	}

	/**
	 * Test for delaeting an employee
	 * @throws Exception
	 */
	@Test
	public void testDeleteEmployee() throws Exception {
		Mockito.when(handler.deleteEmploy(Mockito.any())).thenReturn(
				getMonoEmployee());
		;
		String str = "employee1";
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete(("/delete/1")).content(str).accept("application/json")
				.contentType("application/json");
		MvcResult result = mockMVc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	/**
	 * Test for BAD REQUEST if not an employee found by the given id
	 * @throws Exception
	 */
	@Test
	public void testDeleteEmployeeNoSuchEmployee() throws Exception {
		Mockito.when(handler.deleteEmploy(Mockito.any())).thenReturn(null);
		;
		String str = "employee1";
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete(("/delete/1")).content(str).accept("application/json")
				.contentType("application/json");
		MvcResult result = mockMVc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());

	}

	private Mono<Employee> getMonoEmployee() {
		Employee employee1 = new Employee("3", 112, "Lena", "2011/10/10",
				"Mobile", 32000, 6, "C");
		return Mono.just(employee1);
	}

}
