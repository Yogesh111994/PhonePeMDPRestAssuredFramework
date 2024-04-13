package com.qa.phonepemdp.test;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.phonepemdp.base.BaseTest;
import com.qa.phonepemdp.client.RestClient;
import com.qa.phonepemdp.constant.APIHttpStatus;

public class AmadusAuth2Test extends BaseTest{
	
	private String accessToken;

	@Parameters({"BaseURI","grantTpye","clientId","clientSecret"})
	@BeforeMethod
	public void amedusSetup(String BaseURI,String grantType,String clientId,String clientSecret) {
		restClient = new RestClient(prop,BaseURI);
		accessToken=restClient.getAccessToken(AMADUS_TOKEN_ENDPOINT, grantType, clientId, clientSecret);
	}
	
	@Test
	public void getAmedusFlightDetail() {
		
		RestClient flightClient= new RestClient(prop,BaseURI);
		Map<String,String> headerMap= new HashMap<String,String>();
		headerMap.put("Authorization", "Bearer "+accessToken);
		Map<String,Object> queryMap= new HashMap<String,Object>();
		queryMap.put("origin", "PAR");
		queryMap.put("maxPrice", 200);
		flightClient.get(headerMap, queryMap, AMADUS_FLIGHT_ENDPOINT, false, true)
		.then().log().all()
		.assertThat()
		.statusCode(APIHttpStatus.Ok_200.getCode());
		
	}
}
