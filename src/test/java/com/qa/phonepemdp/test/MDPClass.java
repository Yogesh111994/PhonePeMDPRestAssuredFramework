package com.qa.phonepemdp.test;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.qa.phonepemdp.base.BaseTest;
import com.qa.phonepemdp.client.RestClient;
import com.qa.phonepemdp.constant.APIHttpStatus;
import com.qa.phonepemdp.pojo.GetReporteeList;
import com.qa.phonepemdp.pojo.LoginCredential;
import com.qa.phonepemdp.pojo.GetReporteeList.Span;
import com.qa.phonepemdp.pojo.GetReporteeList.Timeline;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class MDPClass extends BaseTest {

	private String access_token;
	@BeforeMethod
	public void mdpSetupTest() {
		restClient= new RestClient(prop,BaseURI);
		access_token=restClient.getJwtToken("mdpuser03@innoventes.co", "MDPUser031!",PHONEPE_MDP_LOGIN_ENDPOINT);
		System.out.println(access_token);	
	}


	@Test
	public void getTopFilter() {

		Response response = RestAssured.given().log().all()
				.cookie("JwtToken",access_token)
				.when().log().all()
				.get("/ui/apis/getTopFilter");

		int statusCode=response.statusCode();
		Assert.assertEquals(statusCode, APIHttpStatus.Ok_200.getCode());
		//System.out.println(response.prettyPrint());

		String jsonResponse=response.body().asString();
		List<Map<String,Object>> timeLineList=JsonPath.read(jsonResponse,"$..timelineList[*].[\"timelineText\",\"year\"]");

		for(Map<String,Object> timeLineData : timeLineList) {
			String timelineText= (String)timeLineData.get("timelineText");
			Object year =(Object)timeLineData.get("year");

			System.out.println("TimeLineText : "+timelineText);
			System.out.println("TimeLineYear: "+ year);
			System.out.println("-------------");		
		}

	}
	
	@Test
	public void getReporteeList() {
		Response response = RestAssured.given().log().all()
				.cookie("JwtToken",access_token)
				.when().log().all()
				.get("/ui/apis/getTopFilter");

		int statusCode=response.statusCode();
		Assert.assertEquals(statusCode, APIHttpStatus.Ok_200.getCode());
	//	System.out.println(response.prettyPrint());

		String jsonResponse=response.body().asString();
		List<Map<String,Object>> timeLineList=JsonPath.read(jsonResponse,"$..timelineList[*].[\"timelineText\",\"year\"]");
		 
		
		
		for(Map<String,Object> timeLineData : timeLineList) {
			String timelineText= (String)timeLineData.get("timelineText");
			String year =(String)timeLineData.get("year");

			Timeline timeText= new Timeline(timelineText,year,true);
			Span spantext= new Span("Direct",true);

			GetReporteeList list= new GetReporteeList(timeText,spantext);

			RestAssured.given().log().all()
			.cookie("JwtToken",access_token)
			.contentType(ContentType.JSON)
			.body(list)
			.when().log().all()
			.post("/ui/apis/getReporteeList")
			.then().log().all()
			.assertThat()
			.statusCode(200);
		

System.out.println("=======================================================================================");

//
//			System.out.println("TimeLineText : "+timelineText);
//			System.out.println("TimeLineYear: "+ year);
//			System.out.println("-------------");		
		}






	}




}
