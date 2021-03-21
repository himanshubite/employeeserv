package com.paypal.bfs.test.employeeserv.test;


import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.jayway.restassured.path.json.JsonPath;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class EmployeePostTestCase {
	
	private Response response;

	@Before
	public void setup() {
		RestAssured.baseURI = "http://localhost:8083";
	}

	@Test
	public void verifyGoodPostRequest() throws IOException {
		String apiBody = "{\"first_name\":\"Himanshu\",\"last_name\":\"Singh\",\"date_of_birth\":\"1988-03-20\",\"address\":{\"line1\":\"Adarsh Palm Retreat\",\"line2\":\"Bellandur\",\"city\":\"Bangalore\",\"state\":\"KA\",\"zipp_code\":560103}}";
		response =  given().headers("Content-Type","application/json").body(apiBody).post("v1/bfs/employee");
		
		JsonPath jp = new JsonPath(response.getBody().asInputStream());
		
		assertEquals(201, response.getStatusCode());
		assertEquals("Himanshu",jp.get("first_name"));
		assertEquals("Singh",jp.get("last_name"));          
		assertEquals("Adarsh Palm Retreat",jp.get("address.line1"));   
		assertNotNull(jp.get("id"));
	}
	
	@Test
	public void verifyFailureResponseIfFieldSizeIsTooBig() throws IOException {
		String apiBody = "{\"first_name\":\"Himanshu\",\"last_name\":\"Singhxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
				+ "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
				+ "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\","
				+ "\"date_of_birth\":\"1988-03-20\",\"address\":{\"line1\":\"Adarsh Palm Retreat\",\"line2\":\"Bellandur\",\"city\":\"Bangalore\",\"state\":\"KA\",\"zipp_code\":560103}}";
		response = given().headers("Content-Type", "application/json").body(apiBody).post("v1/bfs/employee");

		assertEquals(400, response.getStatusCode());
		
	}

	@Test
	public void verifyErrorResponseInCaseOfDBInsertFailure() throws IOException {
		String apiBody = "{\"first_name\":\"Himanshu\",\"last_name\":\"Singh\",\"date_of_birth\":\"1988-23-32\",\"address\":{\"line1\":\"Adarsh Palm Retreat\",\"line2\":\"Bellandur\",\"city\":\"Bangalore\",\"state\":\"KA\",\"zipp_code\":560103}}";
		response = given().headers("Content-Type", "application/json").body(apiBody).post("v1/bfs/employee");

		assertEquals(409, response.getStatusCode());
		
	}

	
}
