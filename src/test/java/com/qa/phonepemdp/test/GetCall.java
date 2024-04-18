package com.qa.phonepemdp.test;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.phonepemdp.base.BaseTest;
import com.qa.phonepemdp.client.RestClient;
import com.qa.phonepemdp.constant.APIHttpStatus;

public class GetCall extends BaseTest {
//   private String jwtToken;
	@BeforeMethod
	public void getUserSetup() {
		restClient = new RestClient(prop, BaseURI);
	}
//	@BeforeMethod
//	public void mdpSetupTest() {
//		restClient= new RestClient(prop,BaseURI);
//		jwtToken=restClient.getJwtToken("mdpuser03@innoventes.co", "MDPUser031!",PHONEPE_MDP_LOGIN_ENDPOINT);
//		System.out.println(jwtToken);
//	}

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
