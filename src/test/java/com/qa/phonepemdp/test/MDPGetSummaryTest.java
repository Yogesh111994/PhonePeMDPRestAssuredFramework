package com.qa.phonepemdp.test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.phonepemdp.base.BaseTest;
import com.qa.phonepemdp.client.RestClient;

public class MDPGetSummaryTest  extends BaseTest{

	@DataProvider
	public Object[][] loginData() {
		return new Object[][] {
			{"mdpuser03@innoventes.co","MDPUser031!"},
			{"mdpuser02@innoventes.co","MDPUser021!"},
			{"mdpuser01@innoventes.co","MDPUser011!"}
		};
	}

	@Test(dataProvider="loginData")
	public void mdpSetupTest(String username, String Password) {
		restClient= new RestClient(prop,BaseURI);
		String jwtToken=restClient.getJwtToken(username, Password,PHONEPE_MDP_LOGIN_ENDPOINT);
		System.out.println(jwtToken);
	}


}
