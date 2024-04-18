package com.qa.phonepemdp.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.phonepemdp.base.BaseTest;
import com.qa.phonepemdp.client.RestClient;

import io.restassured.RestAssured;

public class Test1 extends BaseTest {

	private String accessToken;
	@BeforeMethod
	public void mdpSetup() {
		restClient=new RestClient(prop,BaseURI);
		accessToken=restClient.getJwtToken("mdpuser02@innoventes.co","MDPUser021!",PHONEPE_MDP_LOGIN_ENDPOINT);
		System.out.println(accessToken);
	}
	
	@Test
	public void getTopFilter() {
		
		RestAssured.given().log().all()
		.cookie("JwtToken", accessToken)
		.when().log().all()
		.get("/ui/apis/getTopFilter")
		.then().log().all()
		.assertThat()
		.statusCode(200);
		
	}
}
