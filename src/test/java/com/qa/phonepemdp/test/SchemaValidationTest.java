package com.qa.phonepemdp.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.phonepemdp.base.BaseTest;
import com.qa.phonepemdp.client.RestClient;
import com.qa.phonepemdp.constant.APIHttpStatus;
import com.qa.phonepemdp.pojo.GoRestCreateUser;
import com.qa.phonepemdp.util.StringUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class SchemaValidationTest extends BaseTest {

	private GoRestCreateUser user;
	@BeforeMethod
	public void getUserSetup() {
		restClient= new RestClient(prop, BaseURI);
	}

	@Test
	public void createRestUserSchemaTest() {
		user= new GoRestCreateUser("yogi",StringUtil.getRandomEmail(),"male","active");
		restClient.post(user, "json",GOREST_ENDPOINT, true, true)
				.then().log().all()
				.assertThat()
				.statusCode(APIHttpStatus.CREATED_201.getCode())
				.and()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("adduserschemavalidator.json"));
			
	}

}
