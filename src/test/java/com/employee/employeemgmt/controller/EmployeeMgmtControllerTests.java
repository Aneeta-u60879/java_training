package com.employee.employeemgmt.controller;

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
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

import com.employee.employeemgmt.DTO.EmployeeMgmtDTO;
import com.employee.employeemgmt.DTO.UpdateBonusDTO;
import com.employee.employeemgmt.DTO.UpdateExperienceDTO;
import com.employee.employeemgmt.handler.impl.EmployeeMgmtHandlerImpl;
import com.employee.employeemgmt.model.ByAccountModel;
import com.employee.employeemgmt.model.EmployModel;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Test cases of employee controller
 * @author 144895
 *
 */
public class EmployeeMgmtControllerTests {
	
	@InjectMocks
	
	private EmployeeMgmtController employCtrl;
	
	private MockMvc mockMvc;
	

	EmployModel newList = new EmployModel("emp1","Erin","2019-01-01","Experion",32000.0,(float) 1,"B");
	EmployeeMgmtDTO employ1 = new EmployeeMgmtDTO("emp2","Merin","2018-01-01","Walmart",32000.0,(float)2,"B");

    @Mock
    private HttpHeaders             httpHeaders;
	
	@Mock
	private EmployeeMgmtHandlerImpl emplHandler;
	
	 @Before
	    public void setUp() {
	        MockitoAnnotations.initMocks(this);
	        this.mockMvc = MockMvcBuilders.standaloneSetup(employCtrl).build();
	    }
	 
	 /**
	  * Test for get all the employ list
	 * @throws Exception
	 */
	@Test	 
	 public void testGetdata() throws Exception{
		 	List<EmployeeMgmtDTO> employeeMngmntList = new ArrayList<>();
		 	employeeMngmntList.add(employ1);
			Mockito.when(emplHandler.getEmployeeList()).thenReturn(employeeMngmntList);
			RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getemployes").accept("application/json")
	                .contentType("application/json");
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	        assertNotNull(result);
	        MockHttpServletResponse response = result.getResponse();
	        assertNotNull(response);
	        assertEquals(HttpStatus.OK.value(), response.getStatus());
	 }	 
	 
	 
	 /**
	  * Test to add employee
	 * @throws Exception
	 */
	@Test 
	 public void testAddEmployee() throws Exception{
		 	
			EmployeeMgmtDTO employ1 = new EmployeeMgmtDTO();
			Mockito.when(emplHandler.saveEmploy(Mockito.any())).thenReturn(newList);
			employ1.setEmployeeId("emp1");
			employ1.setName("Manu");
			employ1.setAccountName("Walmart");
			ObjectMapper mapper = new ObjectMapper();
			String str = mapper.writeValueAsString(employ1);
			RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/addEmploy").content(str).accept(MediaType.APPLICATION_JSON)
	                .contentType(MediaType.APPLICATION_JSON);
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	        assertNotNull(result);
	        MockHttpServletResponse response = result.getResponse();
	        assertNotNull(response);
	        assertEquals(HttpStatus.OK.value(), response.getStatus());
			
	 }
	 /**
	  * test add employee exception 
	 * @throws Exception
	 */
	@Test 
	 public void testAddEmployeeExce() throws Exception{
		 	
			EmployeeMgmtDTO employ1 = new EmployeeMgmtDTO();
			EmployModel newList = null;
			Mockito.when(emplHandler.saveEmploy(Mockito.any())).thenReturn(newList);
			employ1.setEmployeeId("emp1");
			employ1.setName("Manu");
			employ1.setAccountName("Walmart");
			ObjectMapper mapper = new ObjectMapper();
			String str = mapper.writeValueAsString(employ1);
			RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/addEmploy").content(str).accept(MediaType.APPLICATION_JSON)
	                .contentType(MediaType.APPLICATION_JSON);
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	        assertNotNull(result);
	        MockHttpServletResponse response = result.getResponse();
	        assertNotNull(response);			
	 }
	 
	 /**
	  * Test to get the list by account
	 * @throws Exception
	 */
	@Test	 
	 public void testGetByAccount() throws Exception{
			Mockito.when(emplHandler.getEmployeeByAccnt(Mockito.any())).thenReturn(newList);
			RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/byaccount/experion").accept("application/json")
	                .contentType("application/json");
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	        assertNotNull(result);
	        MockHttpServletResponse response = result.getResponse();
	        assertNotNull(response);
	        assertEquals(HttpStatus.OK.value(), response.getStatus());
	 }
	
	
	 /**
	  * Test to get by account exception case
	 * @throws Exception
	 */
	@Test	(expected = NestedServletException.class)  
	 public void testGetByAccountExce() throws Exception{
		 	EmployModel newList = null;
			Mockito.when(emplHandler.getEmployeeByAccnt(Mockito.any())).thenReturn(newList);
			RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/byaccount/experion").accept("application/json")
	                .contentType("application/json");
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	        assertNotNull(result);
	        MockHttpServletResponse response = result.getResponse();
	        assertNotNull(response);
	 }
	 
	 /**
	  * Test to add freshers
	 * @throws Exception
	 */
	@Test 
	 public void testAddFreshers() throws Exception{
			EmployeeMgmtDTO employ1 = new EmployeeMgmtDTO();
			List<EmployModel> employList = new ArrayList<EmployModel>();
			Mockito.when(emplHandler.addFreshers(Mockito.any())).thenReturn(newList);
			employ1.setEmployeeId("emp1");
			employ1.setName("Manu");
			employ1.setAccountName("Walmart");
			employList.add(newList);
			ObjectMapper mapper = new ObjectMapper();
			String str = mapper.writeValueAsString(employList);
			RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/addFreshers").content(str).accept(MediaType.APPLICATION_JSON)
	                .contentType(MediaType.APPLICATION_JSON);
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	        assertNotNull(result);
	        MockHttpServletResponse response = result.getResponse();
	        assertNotNull(response);
	        assertEquals(HttpStatus.OK.value(), response.getStatus());
			
	 }
//	 
	 /**
	   * Test for exception of add a freshers
	   * @throws Exception
	 */
	@Test 
	 public void testAddFreshersExec() throws Exception{
		 	EmployModel newList = null;
		 	EmployModel employ1 = new EmployModel();
			List<EmployModel> employList = new ArrayList<EmployModel>();
			Mockito.when(emplHandler.addFreshers(Mockito.any())).thenReturn(newList);
			employ1.setEmployeeId("emp1");
			employ1.setName("Manu");
			employ1.setAccountName("Walmart");
			employList.add(employ1);
			ObjectMapper mapper = new ObjectMapper();
			String str = mapper.writeValueAsString(employList);
			RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/addFreshers").content(str).accept(MediaType.APPLICATION_JSON)
	                .contentType(MediaType.APPLICATION_JSON);
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	        assertNotNull(result);
	        MockHttpServletResponse response = result.getResponse();
	        assertNotNull(response);
	        assertEquals(HttpStatus.CONFLICT.value(), response.getStatus());
			
	 }
	 
	 /**
	  * Test to delete employ
	  	 * @throws Exception
	 */
	@Test 
	 public void testDeleteEmployee() throws Exception{
			Mockito.when(emplHandler.deleteEmploye(Mockito.any())).thenReturn(newList);
			String str = "emp1";
			RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/delete/emp1").accept(MediaType.APPLICATION_JSON)
	                .contentType(MediaType.APPLICATION_JSON);
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	        assertNotNull(result);
	        MockHttpServletResponse response = result.getResponse();
	        assertNotNull(response);
	        assertEquals(HttpStatus.OK.value(), response.getStatus());
			
	 }
//	 
	 /**
	  * Test to delete employ exception case
	 * @throws Exception
	 */
	@Test 
	 public void testDeleteEmployeeExce() throws Exception{
		 EmployModel newList = null;
			Mockito.when(emplHandler.deleteEmploye(Mockito.any())).thenReturn(newList);
			String str = "emp1";
			RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/delete/emp1").accept(MediaType.APPLICATION_JSON)
	                .contentType(MediaType.APPLICATION_JSON);
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	        assertNotNull(result);
	        MockHttpServletResponse response = result.getResponse();
	        assertNotNull(response);
	        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
			
	 }
	
	 /**
	  * Test to update experience of employ 
	 * @throws Exception
	 */
	@Test 
	 public void testUpdateExpr() throws Exception{
			UpdateExperienceDTO update = new UpdateExperienceDTO();
			Mockito.when(emplHandler.updateEmploy(Mockito.any())).thenReturn(newList);
			update.setEmployeeId("emp1");
			update.setBand("B");
			update.setExperience((float) 3);
			ObjectMapper mapper = new ObjectMapper();
			String str = mapper.writeValueAsString(update);
			RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/updateexpr").content(str).accept(MediaType.APPLICATION_JSON)
	                .contentType(MediaType.APPLICATION_JSON);
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	        assertNotNull(result);
	        MockHttpServletResponse response = result.getResponse();
	        assertNotNull(response);
	        assertEquals(HttpStatus.OK.value(), response.getStatus());
			
	 }
	 
	 /**
	  * Test to update experience of employ exception case
	 * @throws Exception
	 */
	@Test 
	 public void testUpdateExprExce() throws Exception{
		 EmployModel newList = null;
			UpdateExperienceDTO update = new UpdateExperienceDTO();
			Mockito.when(emplHandler.updateEmploy(Mockito.any())).thenReturn(newList);
			update.setEmployeeId("emp1");
			update.setBand("B");
			update.setExperience((float) 3);
			ObjectMapper mapper = new ObjectMapper();
			String str = mapper.writeValueAsString(update);
			RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/updateexpr").content(str).accept(MediaType.APPLICATION_JSON)
	                .contentType(MediaType.APPLICATION_JSON);
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	        assertNotNull(result);
	        MockHttpServletResponse response = result.getResponse();
	        assertNotNull(response);
	        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
			
	 }
	 
	 /**
	  * Test to update bonus
	 * @throws Exception
	 */
	@Test 
	 public void testUpdateBonus() throws Exception{
			UpdateBonusDTO update = new UpdateBonusDTO();
			Mockito.when(emplHandler.updateBonus(Mockito.any())).thenReturn(newList);
			update.setEmployeeId("emp1");
			update.setBonus(10000.0);
			ObjectMapper mapper = new ObjectMapper();
			String str = mapper.writeValueAsString(update);
			RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/updatebonus").content(str).accept(MediaType.APPLICATION_JSON)
	                .contentType(MediaType.APPLICATION_JSON);
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	        assertNotNull(result);
	        MockHttpServletResponse response = result.getResponse();
	        assertNotNull(response);
	        assertEquals(HttpStatus.OK.value(), response.getStatus());
			
	 }
	 
	 /**
	  * Update bonus exception case
	 * @throws Exception
	 */
	@Test 
	 public void testUpdateBonusExce() throws Exception{
		 EmployModel newList = null;
			UpdateBonusDTO update = new UpdateBonusDTO();
			Mockito.when(emplHandler.updateBonus(Mockito.any())).thenReturn(newList);
			update.setEmployeeId("emp1");
			update.setBonus(10000.0);
			ObjectMapper mapper = new ObjectMapper();
			String str = mapper.writeValueAsString(update);
			RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/updatebonus").content(str).accept(MediaType.APPLICATION_JSON)
	                .contentType(MediaType.APPLICATION_JSON);
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	        assertNotNull(result);
	        MockHttpServletResponse response = result.getResponse();
	        assertNotNull(response);
	        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
			
	 }
	 
	 /**
	  * Get by account custom query
	 * @throws Exception
	 */
	@Test
	 public void testAccountByLimit() throws Exception{
		 ByAccountModel accModel = new ByAccountModel();
		 accModel.setEmployeeId("emp1");
		 accModel.setName("Bijoy");
		 List<ByAccountModel> byAccnt = new ArrayList<ByAccountModel>();
		 byAccnt.add(accModel);
		 Mockito.when(emplHandler.getByAccnt(Mockito.any())).thenReturn(byAccnt);
		 RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/accntByLimit/walmart").accept(MediaType.APPLICATION_JSON)
	                .contentType(MediaType.APPLICATION_JSON);
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	        assertNotNull(result);
	        MockHttpServletResponse response = result.getResponse();
	        assertNotNull(response);
	        assertEquals(HttpStatus.OK.value(), response.getStatus());;
	 }
	 /**
	  * Get by account custom query exception
	 * @throws Exception
	 */
	@Test
	 public void testAccountByLimitExce() throws Exception{
		 List<ByAccountModel> byAccnt = new ArrayList<ByAccountModel>();
		 Mockito.when(emplHandler.getByAccnt(Mockito.any())).thenReturn(byAccnt);
		 RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/accntByLimit/walmart").accept(MediaType.APPLICATION_JSON)
	                .contentType(MediaType.APPLICATION_JSON);
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	        assertNotNull(result);
	        MockHttpServletResponse response = result.getResponse();
	        assertNotNull(response);
	        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());;
	 }
	 

}
