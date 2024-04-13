package com.qa.phonepemdp.util;

import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;

import com.qa.phonepemdp.frameworkexception.APIException;

import io.restassured.response.Response;

public class JsonPathValidator {

	private String  getJsonResponseAsString(Response response) {
		return response.getBody().asString();
	}
	
	
	public  <T>T readJson(Response response, String jsonPath) {
		String jsonResponse=getJsonResponseAsString(response);
		try {
		return JsonPath.read(jsonResponse, jsonPath);
		}
		catch(PathNotFoundException e) {
			e.printStackTrace();
			throw new APIException(jsonPath+"is not found");
		}
	}
	
	public  <T>List<T> readJsonList(Response response, String jsonPath) {
		String jsonResponse=getJsonResponseAsString(response);
		try {
		return JsonPath.read(jsonResponse, jsonPath);
		}
		catch(PathNotFoundException e) {
			e.printStackTrace();
			throw new APIException(jsonPath+"is not found");
		}
	}
	
	public  <T>List<Map<String,T>> readJsonListOfMap(Response response, String jsonPath) {
		String jsonResponse=getJsonResponseAsString(response);
		try {
		return JsonPath.read(jsonResponse, jsonPath);
		}
		catch(PathNotFoundException e) {
			e.printStackTrace();
			throw new APIException(jsonPath+"is not found");
		}
	}
}
