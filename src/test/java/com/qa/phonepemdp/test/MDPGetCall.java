package com.qa.phonepemdp.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.phonepemdp.base.BaseTest;
import com.qa.phonepemdp.client.RestClient;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class MDPGetCall extends BaseTest{


	private String access_token;
	@BeforeMethod
	public void mdpSetupTest() {
		restClient= new RestClient(prop,BaseURI);
		access_token=restClient.getJwtToken("mdpuser03@innoventes.co", "MDPUser031!",PHONEPE_MDP_LOGIN_ENDPOINT);
		System.out.println(access_token);	
	}

	@Test
	public void getMetricsSummaryDataTest() {
		RestAssured.given().log().all()
		.cookie("JwtToken",access_token )
		.when().log().all()
		.get("/ui/apis/getMetricsSummary")
		.then()
		.assertThat()
		.statusCode(200);
	}

	@Test
	public void getTopFilter() {
		RestAssured.given()
		.cookie("JwtToken",access_token)
		.contentType(ContentType.JSON)
		.when().log().all()
		.get("https://mdpserverqa.innoventestech.in/ui/apis/getTopFilter")
		.then().log().all()
		.assertThat()
		.statusCode(200);
	}
}
