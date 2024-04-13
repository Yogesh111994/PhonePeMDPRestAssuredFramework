package com.qa.phonepemdp.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.phonepemdp.base.BaseTest;
import com.qa.phonepemdp.client.RestClient;
import com.qa.phonepemdp.constant.APIHttpStatus;
import com.qa.phonepemdp.util.JsonPathValidator;

import io.restassured.response.Response;

public class CircuitAPITest extends BaseTest {


	@BeforeMethod
	public void getUserSetup() {
		restClient = new RestClient(prop, BaseURI);
	}

	@Test
	public void getCircuitTest() {
		restClient.get(CIRCUIT_ENDPOINT,false,true)
		.then()
		.assertThat()
		.statusCode(APIHttpStatus.Ok_200.getCode());

	}

	@Test
	public void circuitJsonPathTest() {
		JsonPathValidator jsonPath= new JsonPathValidator();
		Response response=restClient.get(CIRCUIT_ENDPOINT,false,true);
		
		response.then()
		.assertThat()
		.statusCode(APIHttpStatus.Ok_200.getCode())
		.and()
		.statusLine(APIHttpStatus.Ok_200.getMessage());
		
		//int statusCode=response.statusCode();
		//Assert.assertEquals(statusCode, APIHttpStatus.Ok_200.getCode());

		List<String> countryList=jsonPath.readJson(response, "$..CircuitTable.Circuits[*].Location.country");
		Assert.assertTrue(countryList.contains("China"));

	}

}
