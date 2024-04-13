package com.qa.phonepemdp.test;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.phonepemdp.base.BaseTest;
import com.qa.phonepemdp.client.RestClient;
import com.qa.phonepemdp.constant.APIConstant;
import com.qa.phonepemdp.constant.APIHttpStatus;
import com.qa.phonepemdp.pojo.GoRestCreateUser;
import com.qa.phonepemdp.util.ExcelUtil;
import com.qa.phonepemdp.util.StringUtil;

public class PostCall extends BaseTest {
     private GoRestCreateUser user;
     
     
	@BeforeMethod
	public void getUserSetup() {
		restClient= new RestClient(prop, BaseURI);
	}
	
	@DataProvider
	public Object[][] userData() {
		return new Object [] []{
			{"rajesh","male","active"},
			{"Sita", "female","inactive"},
			{"anmol","male","active"}
		};
	}
	
	@DataProvider
	public Object[][] userExcelData() {
		ExcelUtil util= new ExcelUtil();
		return util.getExcelData(APIConstant.GOREST_USER_SHEET_NAME);
		}
	
	@Test(dataProvider="userData")
	public void createRestUserTest(String name,String gender,String status) {
		user= new GoRestCreateUser(name,StringUtil.getRandomEmail(),gender,status);
		Integer userId=restClient.post(user, "json",GOREST_ENDPOINT, true, true)
		.then()
		.assertThat()
		.statusCode(APIHttpStatus.CREATED_201.getCode())
		.extract().path("id");
		System.out.println(userId);
		RestClient restClient1= new RestClient(prop,BaseURI);
		restClient1.get(GOREST_ENDPOINT+"/"+userId, true, true)
		.then()
		.assertThat()
		.statusCode(APIHttpStatus.Ok_200.getCode())
		.and()
		.body("id", equalTo(userId))
		.and()
		.body("name",equalTo(user.getName()));
	}
}

