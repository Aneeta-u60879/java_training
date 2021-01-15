/*Class Name  : EmployeeControllerTest
 *Description : class for EmployeeController testing
 *Date of Creation: 28/11/2020
 */
package com.training.ust.employee;

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


import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.ust.employee.contoller.EmployeeController;
import com.training.ust.employee.model.Employee;
import com.training.ust.employee.service.EmployeeService;

import reactor.core.publisher.Mono;

/**
 * class for controller test
 * @author 87094
 *
 */
public class EmployeeControllerTest {
	
	
	
@InjectMocks
	
	private EmployeeController employCtrl;
	
	private MockMvc mockMvc;
	

	 @Mock
	    private HttpHeaders             httpHeaders;
		
		@Mock
		private EmployeeService handler;
		
		 @Before
		    public void setUp() {
		        MockitoAnnotations.initMocks(this);
		        this.mockMvc = MockMvcBuilders.standaloneSetup(employCtrl).build();
		    }
        /*
         * Method to get all employees
         */
		 @Test	 
		 public void testGetdata() throws Exception{
			 	//List<Employee> employeeMngmntList = new ArrayList<>();
			 	
				Mockito.when(handler.getEmployeeList()).thenReturn(getSampleEmployeeBOList());
				RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employees").accept("application/json")
		                .contentType("application/json");
				MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		        assertNotNull(result);
		        MockHttpServletResponse response = result.getResponse();
		        assertNotNull(response);
		        assertEquals(HttpStatus.OK.value(), response.getStatus());
		 }	 

		  /*
	       * Method to delete employee
	       */
		 @Test 
		 public void testDeleteEmployee() throws Exception{
				Mockito.when(handler.deleteEmployee(Mockito.any())).thenReturn(getMonoEmployee());
				//String str = "emp1";
				RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/delete/101").accept(MediaType.APPLICATION_JSON)
		                .contentType(MediaType.APPLICATION_JSON);
				MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		        assertNotNull(result);
		        MockHttpServletResponse response = result.getResponse();
		        assertNotNull(response);
		        assertEquals(HttpStatus.OK.value(), response.getStatus());
				
		 }
		 
		 /*
	      * Method to delete employee without data
	      */ 
		 @Test 
		 public void testDeleteEmployeeExce() throws Exception{
				Mockito.when(handler.deleteEmployee(Mockito.any())).thenReturn(getMonoEmployee());
				//String str = "emp1";
				RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/delete/101").accept(MediaType.APPLICATION_JSON)
		                .contentType(MediaType.APPLICATION_JSON);
				MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		        assertNotNull(result);
		        MockHttpServletResponse response = result.getResponse();
		        assertNotNull(response);
		     //   assertEquals(HttpStatus.NOT_FOUND, response.getStatus());
				
		 }
		 
		 /*
	      * Method to get employee by account name
	      */
		 
		 @Test	 
		 public void testGetByAccount() throws Exception{
				Mockito.when(handler.getEmpByAccount(Mockito.any())).thenReturn(getSampleEmployeeBOList());
				RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getEmp/Hiscox").accept("application/json")
		                .contentType("application/json");
				MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		        assertNotNull(result);
		        MockHttpServletResponse response = result.getResponse();
		        assertNotNull(response);
		        assertEquals(HttpStatus.OK.value(), response.getStatus());
		 }
		 
		 
		  /*
	       * Method to add employee
	       */
		 @Test 
		 public void testAddEmployee() throws Exception{
			 	
			
				Employee employeeBO = new Employee("101","rahul","Hiscox","2020-11-02",2000.0,5.0,"B",20);
				Mockito.when(handler.saveEmployee(Mockito.any())).thenReturn(employeeBO);
				ObjectMapper mapper = new ObjectMapper();
				String str = mapper.writeValueAsString(newList);
				RequestBuilder requestBuilder = MockMvcRequestBuilders
						.post("/addEmployee").content(str)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON);
				assertNotNull(requestBuilder);
				MvcResult result = mockMvc.perform(requestBuilder).andReturn();
				
				System.out.println("hello"+result);
				assertNotNull(result);
				MockHttpServletResponse response = result.getResponse();
				assertNotNull(response);
				assertEquals(HttpStatus.CREATED.value(), response.getStatus());
				
		 }
		 
		  /*
	       * Method to add batch of employees
	       */
		 
		 @Test 
		 public void testAddFreshers() throws Exception{
				Employee  employ1 = new Employee();
				List<Employee> employList = new ArrayList<Employee>();
				Mockito.when(handler.saveBatchEmployee(Mockito.any())).thenReturn(getSampleEmployeeBOList());
			    employ1.setEmployeeId("emp1");
				employ1.setEmpName("Manu");
				employ1.setAccountName("Walmart");
				employList.add(newList);
				ObjectMapper mapper = new ObjectMapper();
				String str = mapper.writeValueAsString(employList);
				RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/saveAll").content(str).accept(MediaType.APPLICATION_JSON)
		                .contentType(MediaType.APPLICATION_JSON);
				MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		        assertNotNull(result);
		        MockHttpServletResponse response = result.getResponse();
		        assertNotNull(response);
		        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
				
		 }
		 
		 /*
	       * Method to search employee by account
	       */
		 @Test
			public void testSearchByAccount() throws Exception {
			// Employee newList = new Employee("101","rahul","Hiscox","2020-11-02",2000.0,5.0,"B",20);
			 //Mockito.when(handler.getEmpByAccount(Mockito.any())).thenReturn(newList);
				RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/byaccount/experion").accept("application/json")
		                .contentType("application/json");
				MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		        assertNotNull(result);
		        MockHttpServletResponse response = result.getResponse();
		        assertNotNull(response);
		        assertEquals(HttpStatus.OK.value(), response.getStatus());
			}

	 Employee newList = new Employee("101","rahul","Hiscox","2020-11-02",2000.0,5.0,"B",20);
	 /*
	  * method for getMonoEmployee
	  */
		 private Mono<Employee> getMonoEmployee() {
				Employee employee1 = new Employee("101","rahul","Hiscox","2020-11-02",2000.0,5.0,"B",20);
				return Mono.just(employee1);
			}
		 
		 /*
		  * method for getSampleEmployeeBOList
		  */

			private List<Employee> getSampleEmployeeBOList() {
				List<Employee> employeeList = new ArrayList<Employee>();
				employeeList.add(new Employee("101","rahul","Hiscox","2020-11-02",2000.0,5.0,"B",20));
				employeeList.add( new Employee("103","shibu","Hiscox","2020-11-02",2000.0,5.0,"B",20));
				return employeeList;
			}
		 
}
