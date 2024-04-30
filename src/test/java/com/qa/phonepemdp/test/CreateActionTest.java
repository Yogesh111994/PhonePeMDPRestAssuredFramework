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
import com.qa.phonepemdp.pojo.CreateAction;
import com.qa.phonepemdp.pojo.CreateAction.ActionData;
import com.qa.phonepemdp.pojo.CreateAction.Reportee;
import com.qa.phonepemdp.pojo.CreateAction.Timeline;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class CreateActionTest extends BaseTest {

	private String access_token;
	int action_id;
	@BeforeMethod
	public void mdpSetupTest() {
		restClient= new RestClient(prop,BaseURI);
		access_token=restClient.getJwtToken("mdpuser03@innoventes.co", "MDPUser031!",PHONEPE_MDP_LOGIN_ENDPOINT);
		System.out.println(access_token);	
	}
	

	public void createOwnAction() {
		 
		ActionData assignFor= new ActionData("Radh294@phonepe.com","2024-05-05","Complete the task today");
		Reportee createBy= new Reportee("Suha3322","Suha3322@phonepe.com","Director, Product Management",true);
		Timeline timeSpan= new Timeline("Q2 2024: Apr - May - Jun","2024",true);
		CreateAction action= new CreateAction(assignFor,createBy,timeSpan,"open");
		 Response response = RestAssured.given().log().all()
		.cookie("JwtToken",access_token )
		.contentType(ContentType.JSON)
		.body(action)
		.when().log().all()
		.post("/ui/apis/createAction");
		 
		 int statusCode=response.statusCode();
		 System.out.println(response.prettyPrint());
		Assert.assertEquals(statusCode, APIHttpStatus.Ok_200.getCode());	
		
		String jsonResponse=response.body().asString();
	    List<String> actionIdList=JsonPath.read(jsonResponse,"$..data..Col1.\"text\"");
	    
	    for(String id : actionIdList) {	
	          System.out.println(id);
	    } 
	    
	}
	
	
}
