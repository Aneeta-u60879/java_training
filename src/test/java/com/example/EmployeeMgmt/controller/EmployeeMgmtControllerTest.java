package com.example.EmployeeMgmt.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.employeemgmt.bo.EmployeeBO;
import com.example.employeemgmt.controller.EmployeeMgmtController;
import com.example.employeemgmt.dto.DeleteEmployeeDTO;
import com.example.employeemgmt.dto.EmployeeBonusDTO;
import com.example.employeemgmt.dto.EmployeeDTO;
import com.example.employeemgmt.dto.EmployeeUpdateDTO;
import com.example.employeemgmt.handler.EmployeeMgmtHandlerImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EmployeeMgmtControllerTest {

	@InjectMocks
	EmployeeMgmtController controller;

	@Mock
	EmployeeMgmtHandlerImpl handler;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

	}

	/**
	 * Test for {@link EmployeeMgmtController#getEmployeeList()} with data.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetEmployeeList() throws Exception {
		List<EmployeeBO> employeeBO = new ArrayList<EmployeeBO>();
		employeeBO.add(new EmployeeBO("U9", "kichu", LocalDate.of(2019, 8, 15),
				"abc", 35000.0, 1.0, "B"));
		Mockito.when(handler.getEmpList()).thenReturn(employeeBO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getlist");
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/**
	 * Test for {@link EmployeeMgmtController#getEmployeeList()} with no data.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetEmployeeListIfResponseIsEmpty() throws Exception {
		List<EmployeeBO> employeeBO = new ArrayList<EmployeeBO>();
		Mockito.when(handler.getEmpList()).thenReturn(employeeBO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getlist");
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/**
	 * Test for {@link EmployeeMgmtController#searchById(String)} with data.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSearchById() throws Exception {
		EmployeeBO employeeBO = new EmployeeBO("U9", "kichu", LocalDate.of(
				2019, 8, 15), "abc", 35000.0, 1.0, "B");
		Mockito.when(handler.searchById(Mockito.any())).thenReturn(employeeBO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/searchbyid/U9");
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/**
	 * Test for {@link EmployeeMgmtController#searchById(String)} with no data.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSearchByIdFNoSuchEmployee() throws Exception {
		EmployeeBO employeeBO = null;
		Mockito.when(handler.searchById(Mockito.any())).thenReturn(employeeBO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/searchbyid/null");
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/**
	 * Test for {@link EmployeeMgmtController#searchByBand(String)} with data.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSearchByBand() throws Exception {
		List<EmployeeBO> employeeBO = new ArrayList<EmployeeBO>();
		employeeBO.add(new EmployeeBO("U9", "kichu", null, "abc", 35000.0, 1.0,
				"B"));
		Mockito.when(handler.searchByBand(Mockito.any()))
				.thenReturn(employeeBO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/searchbyband").param("band", "band");
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/**
	 * Test for {@link EmployeeMgmtController#searchByBand(String)} with no
	 * data.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSearchByBandFNoSuchEmployee() throws Exception {
		List<EmployeeBO> employeeBO = new ArrayList<EmployeeBO>();
		Mockito.when(handler.searchByBand(Mockito.any()))
				.thenReturn(employeeBO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/searchbyband").param("band", "band");
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/**
	 * Test for {@link EmployeeMgmtController#addEmployee(EmployeeDTO))} with
	 * data.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddEmployee() throws Exception {
		EmployeeDTO empDTO = new EmployeeDTO("U9", "kichu", null, "abc",
				35000.0, 1.0, "B");
		EmployeeBO employeeBO = new EmployeeBO("U9", "kichu", null, "abc",
				35000.0, 1.0, "B");
		Mockito.when(handler.addEmployee(Mockito.any())).thenReturn(employeeBO);
		ObjectMapper mapper = new ObjectMapper();
		String str = mapper.writeValueAsString(empDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/addemployee").content(str)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}

	/**
	 * Test for {@link EmployeeMgmtController#addEmployee(EmployeeDTO))} with no
	 * data.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddEmployeeWhenResponeIsNull() throws Exception {
		EmployeeDTO empDTO = new EmployeeDTO();
		EmployeeBO employeeBO = null;
		Mockito.when(handler.addEmployee(Mockito.any())).thenReturn(employeeBO);
		ObjectMapper mapper = new ObjectMapper();
		String str = mapper.writeValueAsString(empDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/addemployee").content(str)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
	}

	/**
	 * Test for {@link EmployeeMgmtController#deleteEmployee(String)} with data.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDeleteEmployee() throws Exception {
		EmployeeDTO empDTO = new EmployeeDTO("U9", "kichu", null, "abc",
				35000.0, 1.0, "B");
		EmployeeBO employeeBO = new EmployeeBO("U4", "arathy", null, "abc",
				35000.0, 1.0, "B");
		Mockito.when(handler.deleteEmployee(Mockito.any())).thenReturn(
				employeeBO);
		ObjectMapper mapper = new ObjectMapper();
		String str = mapper.writeValueAsString(empDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/delete").param("employeeId", "U9").content(str)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/**
	 * Test for {@link EmployeeMgmtController#deleteEmployee(String)} with no
	 * data.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDeleteEmployeeWhenResponeIsNull() throws Exception {
		EmployeeBO employeeBO = null;
		Mockito.when(handler.deleteEmployee(Mockito.any())).thenReturn(
				employeeBO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/delete").param("employeeId", "employeeId")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
	}

	/**
	 * Test for
	 * {@link EmployeeMgmtController#deleteEmployeeByBand(DeleteEmployeeDTO)}
	 * with data.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDeleteEmployeeByBand() throws Exception {
		List<EmployeeBO> employeeBO = new ArrayList<EmployeeBO>();
		DeleteEmployeeDTO empDTO = new DeleteEmployeeDTO("abc", "B");
		employeeBO.add(new EmployeeBO("U4", "arathy", null, "xyz", 35000.0,
				1.0, "A"));
		Mockito.when(handler.deleteEmployeeByBand(Mockito.any())).thenReturn(
				employeeBO);
		ObjectMapper mapper = new ObjectMapper();
		String str = mapper.writeValueAsString(empDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/deleteByBand").content(str)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/**
	 * Test for
	 * {@link EmployeeMgmtController#deleteEmployeeByBand(DeleteEmployeeDTO)}
	 * with no data.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDeleteEmployeeByBandWhenResponseIsEmpty() throws Exception {
		DeleteEmployeeDTO empDTO = new DeleteEmployeeDTO();
		List<EmployeeBO> employeeBO = new ArrayList<EmployeeBO>();
		Mockito.when(handler.deleteEmployeeByBand(Mockito.any())).thenReturn(
				employeeBO);
		ObjectMapper mapper = new ObjectMapper();
		String str = mapper.writeValueAsString(empDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/deleteByBand").content(str)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
	}

	/**
	 * Test for {@link EmployeeMgmtController#updateEmployee(EmployeeUpdateDTO)
	 * )} with data.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdateEmployee() throws Exception {
		EmployeeBO employeeBO = new EmployeeBO("U9", "arathy", null, "abc",
				35000.0, 1.0, "B");
		EmployeeUpdateDTO empDTO = new EmployeeUpdateDTO("U9", 1.0, "B");
		Mockito.when(handler.updateEmployee(Mockito.any())).thenReturn(
				employeeBO);
		ObjectMapper mapper = new ObjectMapper();
		String str = mapper.writeValueAsString(empDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/updateemp").content(str)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/**
	 * Test for {@link EmployeeMgmtController#updateEmployee(EmployeeUpdateDTO)
	 * )} with no data.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdateEmployeeWhenResponseIsNull() throws Exception {
		EmployeeBO employeeBO = null;
		EmployeeUpdateDTO empDTO = new EmployeeUpdateDTO("U9", 1.0, "B");

		Mockito.when(handler.updateEmployee(Mockito.any())).thenReturn(
				employeeBO);
		ObjectMapper mapper = new ObjectMapper();
		String str = mapper.writeValueAsString(empDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/updateemp").content(str)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
	}

	/**
	 * Test for {@link EmployeeMgmtController#addAllowance(Double))} with data.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdateallowance() throws Exception {
		List<EmployeeBO> employeeBO = new ArrayList<EmployeeBO>();
		employeeBO.add(new EmployeeBO("U9", "arathy", null, "abc", 38500.0,
				1.0, "B"));
		Mockito.when(handler.addAllowance(Mockito.any()))
				.thenReturn(employeeBO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/updateAllowance").param("allowance", "1000.0")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/**
	 * Test for {@link EmployeeMgmtController#addAllowance(Double))} with no
	 * data.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdateallowanceIfResponseIsEmpty() throws Exception {
		List<EmployeeBO> employeeBO = new ArrayList<EmployeeBO>();
		Mockito.when(handler.addAllowance(Mockito.any()))
				.thenReturn(employeeBO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/updateAllowance").param("allowance", "1000.0")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/**
	 * Test for {@link EmployeeMgmtController#addBonus(EmployeeBonusDTO)} with
	 * data.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdateBonus() throws Exception {
		EmployeeBO employeeBO = new EmployeeBO("U9", "arathy", null, "abc",
				35000.0, 1.0, "B");
		EmployeeBonusDTO empDTO = new EmployeeBonusDTO("U9", 10.0);
		Mockito.when(handler.addBonus(Mockito.any())).thenReturn(employeeBO);
		ObjectMapper mapper = new ObjectMapper();
		String str = mapper.writeValueAsString(empDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/updatebonus").content(str)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/**
	 * Test for {@link EmployeeMgmtController#addBonus(EmployeeBonusDTO)} with
	 * no data.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdateBonusWhenEmpNotExist() throws Exception {
		EmployeeBO employeeBO = null;
		EmployeeBonusDTO empDTO = new EmployeeBonusDTO();
		Mockito.when(handler.addBonus(Mockito.any())).thenReturn(employeeBO);
		ObjectMapper mapper = new ObjectMapper();
		String str = mapper.writeValueAsString(empDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/updatebonus").content(str)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
	}

	/**
	 * Test for {@link EmployeeMgmtController#searchByAcc(String)} with data.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSearchByAccount() throws Exception {
		List<EmployeeBO> employeeBO = new ArrayList<EmployeeBO>();
		employeeBO.add(new EmployeeBO("U9", "kichu", null, "abc", 35000.0, 1.0,
				"B"));
		Mockito.when(handler.searchByAcc(Mockito.any())).thenReturn(employeeBO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/searchbyacc").param("accountName", "accountName");
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/**
	 * Test for {@link EmployeeMgmtController#searchByAcc(String)} with no data.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSearchByAccountFNoSuchAccount() throws Exception {
		List<EmployeeBO> employeeBO = new ArrayList<EmployeeBO>();
		Mockito.when(handler.searchByAcc(Mockito.any())).thenReturn(employeeBO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/searchbyacc").param("accountName", "accountName");
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
	}

	/**
	 * Test for {@link EmployeeMgmtController#addEmployees(List)} with data.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddFreshers() throws Exception {
		List<EmployeeBO> employeeBO = new ArrayList<EmployeeBO>();
		List<EmployeeDTO> empDTO = new ArrayList<EmployeeDTO>();
		empDTO.add(new EmployeeDTO("U9", "kichu", null, "abc", 35000.0, 1.0,
				"B"));
		employeeBO.add(new EmployeeBO("U9", "kichu", null, "abc", 35000.0, 1.0,
				"B"));
		Mockito.when(handler.addFreshers(Mockito.any())).thenReturn(employeeBO);
		ObjectMapper mapper = new ObjectMapper();
		String str = mapper.writeValueAsString(empDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/addfreshers").content(str)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}

	/**
	 * Test for {@link EmployeeMgmtController#addEmployees(List))} with no data.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddFreshersWhenresponseIsEmpty() throws Exception {
		List<EmployeeBO> employeeBO = new ArrayList<EmployeeBO>();
		List<EmployeeDTO> empDTO = new ArrayList<EmployeeDTO>();
		Mockito.when(handler.addFreshers(Mockito.any())).thenReturn(employeeBO);
		ObjectMapper mapper = new ObjectMapper();
		String str = mapper.writeValueAsString(empDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/addfreshers").content(str)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
	}

	/**
	 * Test for
	 * {@link EmployeeMgmtController#getEmployeeByCustomName(String, Integer, Integer)}
	 * with data.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testgetEmployeeByCustomName() throws Exception {
		List<EmployeeBO> employeeBO = new ArrayList<EmployeeBO>();
		List<EmployeeDTO> empDTO = new ArrayList<EmployeeDTO>();
		empDTO.add(new EmployeeDTO("U9", "kichu", null, "abc", 35000.0, 1.0,
				"B"));
		employeeBO.add(new EmployeeBO("U9", "kichu", null, "abc", 35000.0, 1.0,
				"B"));
		Mockito.when(
				handler.getEmpByNameQuery((Mockito.any()), (Mockito.any()),
						(Mockito.any()))).thenReturn(employeeBO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/getempbyacc/U9/0/1");
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/**
	 * Test for
	 * {@link EmployeeMgmtController#getEmployeeByCustomName(String, Integer, Integer)}
	 * with data.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testgetEmployeeByCustomNameWhenResponseIsEmpty()
			throws Exception {
		List<EmployeeBO> employeeBO = new ArrayList<EmployeeBO>();
		List<EmployeeDTO> empDTO = new ArrayList<EmployeeDTO>();
		empDTO.add(new EmployeeDTO("U9", "kichu", null, "abc", 35000.0, 1.0,
				"B"));
		Mockito.when(
				handler.getEmpByNameQuery((Mockito.any()), (Mockito.any()),
						(Mockito.any()))).thenReturn(employeeBO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/getempbyacc/U9/0/1");
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/**
	 * Test for {@link EmployeeMgmtController#getEmployeeByCusId(String)} with
	 * data.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetEmpByCusId() throws Exception {
		EmployeeBO employeeBO = new EmployeeBO("U9", "kichu", LocalDate.of(
				2019, 8, 15), "abc", 35000.0, 1.0, "B");
		Mockito.when(handler.getEmployeesWithId(Mockito.any())).thenReturn(
				employeeBO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/getempbyid/U9");
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/**
	 * Test for {@link EmployeeMgmtController#getEmployeeByCusId(String)} with
	 * data.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetEmpByCusIdwhenResponseIsNull() throws Exception {
		EmployeeBO employeeBO = null;
		Mockito.when(handler.getEmployeesWithId(Mockito.any())).thenReturn(
				employeeBO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/getempbyid/U9");
		assertNotNull(requestBuilder);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result);
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
}
