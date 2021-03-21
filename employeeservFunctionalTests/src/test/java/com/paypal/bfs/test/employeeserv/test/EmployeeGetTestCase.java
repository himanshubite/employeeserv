package com.paypal.bfs.test.employeeserv.test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class EmployeeGetTestCase {
	
	private Response response;

	@Before
	public void setup() {
		RestAssured.baseURI = "http://localhost:8083";
	}

	@Test
	public void goodGetVerification() throws IOException {
		String expectedJson = "{\"id\":1,\"first_name\":\"Himanshu\",\"last_name\":\"Singh\",\"date_of_birth\":\"1988-03-20\",\"address\":{\"line1\":\"Adarsh Palm Retreat\",\"line2\":\"Bellandur\",\"city\":\"Bangalore\",\"state\":\"KA\",\"zipp_code\":560103}}";
		response =  given().headers("Content-Type","application/json").get("v1/bfs/employees/1");
		ObjectMapper mapper = new ObjectMapper();
		
		assertEquals(200, response.getStatusCode());
		assertEquals(mapper.readTree(response.getBody().asInputStream()),mapper.readTree(expectedJson));
             
	}

	@Test
	public void invalidIdTypeVerification() {
		response =  given().headers("Content-Type","application/json").get("v1/bfs/employees/R");
		assertEquals(422, response.getStatusCode());
	         
	}
}
