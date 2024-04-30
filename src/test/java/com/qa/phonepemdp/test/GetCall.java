package com.qa.phonepemdp.test;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.phonepemdp.base.BaseTest;
import com.qa.phonepemdp.client.RestClient;
import com.qa.phonepemdp.constant.APIHttpStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class GetCall extends BaseTest {
	@BeforeMethod
	public void mdpSetupTest() {
		restClient= new RestClient(prop,BaseURI);
		
	}

	@Test(priority = 1)
	public void getUser() {
		restClient.get(GOREST_ENDPOINT, true, true)
		.then().log().all()
		.assertThat()
		.statusCode(APIHttpStatus.Ok_200.getCode());

	}

	
	  @Test(priority=2, enabled=false) 
	  public void getSpecificUser() {
	  restClient.get(GOREST_ENDPOINT+"/"+6836094,true, true) 
	  .then().log().all()
	  .assertThat() 
	  .statusCode(APIHttpStatus.Ok_200.getCode());
	  }
	 
	  @Test 
	  public void getUserUsingQuery() { 
		  Map<String,Object> queryMap= new HashMap<String,Object>();
		  queryMap.put("name", "naveen");
	      queryMap.put("status", "active"); 
	      restClient.get(null, queryMap,GOREST_ENDPOINT, true, true)
	      .then()
	      .assertThat()
	      .statusCode(APIHttpStatus.Ok_200.getCode()); 
	      }
	  

	  
	  
	 
}
