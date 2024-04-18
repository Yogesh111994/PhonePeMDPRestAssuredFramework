package com.qa.phonepemdp.base;

import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;


import com.qa.phonepemdp.client.RestClient;
import com.qa.phonepemdp.configuration.ConfigurationManager;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

public class BaseTest {

	// Service URL
	public static  String GOREST_ENDPOINT="/public/v2/users";
	public static String CIRCUIT_ENDPOINT="/api/f1/2017/circuits.json";
	public static String AMADUS_TOKEN_ENDPOINT="/v1/security/oauth2/token";
	public static String  AMADUS_FLIGHT_ENDPOINT="/v1/shopping/flight-destinations";
	public static String PHONEPE_MDP_LOGIN_ENDPOINT="/api/v1/auth/login";
	
	protected Properties prop;
	protected ConfigurationManager config;
	protected RestClient restClient;
	protected String BaseURI;
	
	@Parameters({"BaseURI"})
	@BeforeTest
	public void setUp(String BaseURI) {
		RestAssured.filters(new AllureRestAssured());
		config= new ConfigurationManager();
		prop =config.init();
		this.BaseURI=BaseURI;
	//	String BaseURI=prop.getProperty("BaseURI");
		
		restClient = new RestClient( prop,BaseURI);
	}
}
