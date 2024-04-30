package com.qa.phonepemdp.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.phonepemdp.base.BaseTest;
import com.qa.phonepemdp.client.RestClient;
import com.qa.phonepemdp.pojo.StructureDeepDivePojo;
import com.qa.phonepemdp.pojo.StructureDeepDivePojo.Reportee;
import com.qa.phonepemdp.pojo.StructureDeepDivePojo.Span;
import com.qa.phonepemdp.pojo.StructureDeepDivePojo.Timeline;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class StructureDeepDive extends BaseTest{

	private String access_token;
	@BeforeMethod
	public void mdpSetupTest() {
		restClient= new RestClient(prop,BaseURI);
		access_token=restClient.getJwtToken("mdpuser03@innoventes.co", "MDPUser031!",PHONEPE_MDP_LOGIN_ENDPOINT);
		System.out.println(access_token);	
	}
	
	@DataProvider
	public Object[][] reporteeData() {
		return new Object[][] {
			{"Ambr3244","Ambr3244@phonepe.com","Product Director - Merchant Payments","Q1 2024: Jan - Feb - Mar","2024","Direct","2024"},
			{"Harj2232","Harj2232@phonepe.com","Associate Director - Product Management","Q4 2023: Oct - Nov - Dec","2023","Direct","2023"}
		};
	}

	
	@Test(dataProvider="reporteeData")
	public void getStructureDeepDiveTest(String name,String email,String role,String quarter,String year,String spanText) {
		
		Reportee  reportee= new Reportee(name,email,role,true);
		Timeline   timeline= new Timeline(quarter,year,true);
		Span    span= new Span(spanText,true);		
		
		
		StructureDeepDivePojo data= new StructureDeepDivePojo(reportee,timeline,span);
		
		RestAssured.given().log().all()
		.contentType(ContentType.JSON)
		.body(data)
		.cookie("JwtToken",access_token )
		.when()
		.post("/ui/apis/getManagerReporteeList")
		.then()
		.assertThat()
		.statusCode(200);
		
		
			}
	
}
