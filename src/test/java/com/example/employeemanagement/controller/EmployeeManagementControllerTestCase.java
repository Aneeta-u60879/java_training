package com.example.employeemanagement.controller;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.model.EmployeeNames;
import com.example.employeemanagement.bo.EmployeeBO;
import com.example.employeemanagement.bo.UpdateEmployeeBO;
import com.example.employeemanagement.dto.EmployeeDTO;
import com.example.employeemanagement.dto.UpdateEmployeeDTO;
import com.example.employeemanagement.handler.impl.EmployeeManagementHandlerImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Test Class for Employee Controller
 * 
 * @author 144892
 *
 */
@RunWith(SpringRunner.class)
public class EmployeeManagementControllerTestCase {

	/*
	 * Inject Mock of EmployeeManagementController
	 */
	@InjectMocks
	private EmployeeManagementController empcontroller;

	private MockMvc mockMvc;

	/*
	 * Mocking EmployeeManagementHandlerImpl
	 */
	@Mock
	private EmployeeManagementHandlerImpl empHandler;

	/*
	 * Creating Mock EmployeeBO, EmployeeDTO, Employee, UpdateEmployeeBO and
	 * EmployeeNames objects
	 */
	EmployeeBO emp1 = new EmployeeBO(100, "Thomas", "2016-2-15", "walmart",
			50000.0, 4.0, "A");
	EmployeeBO emp2 = new EmployeeBO(101, "Neena", "2019-12-20", "anthem",
			20000.0, 1.0, "B");
	EmployeeDTO emp3 = new EmployeeDTO(100, "Thomas", "2016-2-15", "walmart",
			50000.0, 4.0, "A");
	EmployeeDTO emp4 = new EmployeeDTO(101, "Neena", "2019-12-20", "anthem",
			20000.0, 1.0, "B");
	Employee emp5 = new Employee(100, "Thomas", "2016-2-15", "walmart",
			50000.0, 4.0, "A");
	Employee emp6 = new Employee(101, "Neena", "2019-12-20", "anthem", 20000.0,
			1.0, "B");
	UpdateEmployeeBO updateEmployee = new UpdateEmployeeBO(100, 500.0);
	EmployeeNames employee = new EmployeeNames(100, "Thomas", "2016-2-15");

	/*
	 * Setting up Mocks
	 */
	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(empcontroller).build();
	}

	/*
	 * Test for getEmployeeDetails method
	 */
	@Test
	public void testgetEmployeeDetails() throws Exception {
		List<EmployeeBO> employeeDetailsList = new ArrayList<EmployeeBO>();
		employeeDetailsList.add(emp1);
		Mockito.when(empHandler.getEmployeeList()).thenReturn(
				employeeDetailsList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/all")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/*
	 * Test for addEmployee method
	 */
	@Test
	public void testaddEmployee() throws Exception {
		Mockito.when(empHandler.saveEmployee(Mockito.any())).thenReturn(emp1);
		ObjectMapper mapper = new ObjectMapper();
		EmployeeDTO emp3 = new EmployeeDTO();
		emp3.setEmpId(101);
		emp3.setEmpName("Arathy");
		emp3.setAccName("W");
		String str = mapper.writeValueAsString(emp3);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/save")
				.content(str).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/*
	 * Test for deleteEmployee method
	 */
	@Test
	public void testdeleteEmployee() throws Exception {
		Mockito.when(empHandler.deleteEmployee("100")).thenReturn(emp1);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/deleteemp/100").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/*
	 * Test for updateDetails method
	 */
	@Test
	public void testupdateDetails() throws Exception {
		Mockito.when(empHandler.updateEmployee(Mockito.any())).thenReturn(emp1);
		ObjectMapper mapper = new ObjectMapper();
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setEmpId(100);
		employeeDTO.setSalary(35000.0);
		employeeDTO.setBand("B");
		String str = mapper.writeValueAsString(employeeDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/update")
				.content(str).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/*
	 * Test for getgetBandEmployeeList method
	 */
	@Test
	public void testgetBandEmployeeList() throws Exception {
		List<EmployeeBO> employeeDetailsList = new ArrayList<EmployeeBO>();
		employeeDetailsList.add(emp1);
		Mockito.when(empHandler.getEmployeesFromBand(Mockito.any()))
				.thenReturn(employeeDetailsList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/getbandemp").param("band", "band")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/*
	 * Test for getEmployeeByAcc method
	 */
	@Test
	public void testgetEmployeeByAcc() throws Exception {
		List<EmployeeBO> employeeDetailsList = new ArrayList<EmployeeBO>();
		employeeDetailsList.add(emp1);
		Mockito.when(empHandler.getByAcc(Mockito.any())).thenReturn(
				employeeDetailsList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/getaccemp").param("accName", "accName")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/*
	 * Test for deleteEmployeeFromAcc method
	 */
	@Test
	public void testdeleteEmployeeFromAcc() throws Exception {
		List<EmployeeBO> employeeDetailsList = new ArrayList<EmployeeBO>();
		employeeDetailsList.add(emp1);
		Mockito.when(empHandler.deleteByAcc(Mockito.any(), Mockito.any()))
				.thenReturn(employeeDetailsList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/deleteaccemp/walmart/A")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/*
	 * Test for addBonus method
	 */
	@Test
	public void testaddBonus() throws Exception {
		Mockito.when(empHandler.addBonus(Mockito.any())).thenReturn(emp1);
		ObjectMapper mapper = new ObjectMapper();
		UpdateEmployeeDTO employeeDTO = new UpdateEmployeeDTO();
		employeeDTO.setEmpId(100);
		employeeDTO.setBonus(500.0);
		String str = mapper.writeValueAsString(employeeDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/addbonus")
				.content(str).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/*
	 * Test for addAllowance method
	 */
	@Test
	public void testaddAllowance() throws Exception {
		List<EmployeeBO> employeeDetailsList = new ArrayList<EmployeeBO>();
		employeeDetailsList.add(emp1);
		Mockito.when(empHandler.addAllowance(Mockito.any())).thenReturn(
				employeeDetailsList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/addallowance/500.0")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/*
	 * Test for addEmployeeList method
	 */
	@Test
	public void testaddEmployeeList() throws Exception {
		List<EmployeeBO> employeeDetailsList = new ArrayList<EmployeeBO>();
		employeeDetailsList.add(emp1);
		employeeDetailsList.add(emp2);
		Mockito.when(empHandler.addNewEmployees(Mockito.any())).thenReturn(
				employeeDetailsList);
		ObjectMapper mapper = new ObjectMapper();
		List<EmployeeDTO> employeeDetailsListDTO = new ArrayList<EmployeeDTO>();
		employeeDetailsListDTO.add(emp3);
		employeeDetailsListDTO.add(emp4);
		String str = mapper.writeValueAsString(employeeDetailsListDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/addemployeelist").content(str)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/*
	 * Test for getEmployeeByAccName method
	 */
	@Test
	public void testgetEmployeeByAccName() throws Exception {
		List<Employee> employeeDetailsList = new ArrayList<Employee>();
		employeeDetailsList.add(emp5);
		employeeDetailsList.add(emp6);
		Mockito.when(
				empHandler.getEmpByNameQuery(Mockito.any(), Mockito.any(),
						Mockito.any())).thenReturn(employeeDetailsList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/customacc/walmart/0/2")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/*
	 * Test for getEmployeeByBand method
	 */
	@Test
	public void testgetEmployeeByBand() throws Exception {
		List<EmployeeNames> employeeDetailsList = new ArrayList<EmployeeNames>();
		employeeDetailsList.add(employee);
		Mockito.when(empHandler.getEmpByNameByBand(Mockito.any())).thenReturn(
				employeeDetailsList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/customband/A").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
}
