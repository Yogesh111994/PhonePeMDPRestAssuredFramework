package com.qa.phonepemdp.client;

import static io.restassured.RestAssured.given;

import java.util.Map;
import java.util.Properties;

import com.qa.phonepemdp.constant.APIHttpStatus;
import com.qa.phonepemdp.frameworkexception.APIException;
import com.qa.phonepemdp.pojo.LoginCredential;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {

	private static RequestSpecBuilder specBuilder;
	//	private static final String BASE_URI = "https://gorest.co.in";
	//	private static final String TOKEN = "fe06a0006eb28608aea0dd91b3d4087397505e0b8d70d03abf0891b799d79674";

	private Properties prop;
	private String BaseURI;
	private boolean isAuthoriztionAdded;

	//	static {
	//		specBuilder = new RequestSpecBuilder();
	//	}

	public RestClient(Properties prop,String BaseURI) {
		specBuilder = new RequestSpecBuilder();
		this.prop=prop;
		this.BaseURI=BaseURI;
	}

	private void setContentType(String contentType) {
		switch (contentType.toLowerCase().trim()) {
		case "json":
			specBuilder.setContentType(ContentType.JSON);
			break;
		case "xml":
			specBuilder.setContentType(ContentType.XML);
			break;
		case "text":
			specBuilder.setContentType(ContentType.TEXT);
			break;
		case "html":
			specBuilder.setContentType(ContentType.HTML);
			break;

		default:
			System.out.println("Please Pass The Right Content Type");
			throw new APIException("Enter Wrong Content Type");
		}
	}

	private void addAuthorization() {
		if(! isAuthoriztionAdded) {
			specBuilder.addHeader("Authorization", "Bearer " + prop.getProperty("Token"));
		}
		isAuthoriztionAdded=true;
	}

	private RequestSpecification createReqSpec(boolean includeAuth) {
		specBuilder.setBaseUri(BaseURI);
		if(includeAuth){
			addAuthorization();
		}
		return specBuilder.build();
	}

	private RequestSpecification createReqSpec(Map<String, String> headersMap,boolean includeAuth) {
		specBuilder.setBaseUri(BaseURI);
		if(includeAuth){
			addAuthorization();
		}
		if (headersMap != null) {
			specBuilder.addHeaders(headersMap);
		}
		return specBuilder.build();
	}

	private RequestSpecification createReqSpec(Map<String, String> headersMap, Map<String, Object> queryMap,boolean includeAuth) {
		specBuilder.setBaseUri(BaseURI);
		if(includeAuth){
			addAuthorization();
		}
		if (headersMap != null) {
			specBuilder.addHeaders(headersMap);
		}
		if (queryMap != null) {
			specBuilder.addQueryParams(queryMap);
		}
		return specBuilder.build();
	}

	private RequestSpecification createReqSpec(Object requestBody, String contentType,boolean includeAuth) {
		specBuilder.setBaseUri(BaseURI);
		if(includeAuth){
			addAuthorization();
		}

		if (contentType != null) {
			setContentType(contentType);
		}
		if (requestBody != null) {
			specBuilder.setBody(requestBody);
		}
		return specBuilder.build();
	}

	private RequestSpecification createReqSpec(Object requestBody, String contentType, Map<String, String> headersMap,boolean includeAuth) {
		specBuilder.setBaseUri(BaseURI);
		if(includeAuth){
			addAuthorization();
		}

		if (contentType != null) {
			setContentType(contentType);
		}

		if (requestBody != null) {
			specBuilder.setBody(requestBody);
		}
		if (headersMap != null) {
			specBuilder.addHeaders(headersMap);
		}
		return specBuilder.build();
	}

	// =============== GET CALL =======================

	public Response get(String serviceURL,boolean includeAuth, boolean log) {
		if (log) {
			return RestAssured.given(createReqSpec(includeAuth)).log().all().when().get(serviceURL);
		}
		return RestAssured.given(createReqSpec(includeAuth)).when().get(serviceURL);
	}

	public Response get(Map<String, String> headerMap, String serviceURL, boolean includeAuth,boolean log) {
		if (log) {
			return RestAssured.given(createReqSpec(headerMap,includeAuth)).log().all().when().log().all().get(serviceURL);
		}
		return RestAssured.given(createReqSpec(headerMap,includeAuth)).when().get(serviceURL);
	}

	public Response get(Map<String, String> headerMap, Map<String, Object> querymap, String serviceURL,boolean includeAuth, boolean log) {
		if (log) {
			return RestAssured.given(createReqSpec(headerMap, querymap,includeAuth)).log().all().when().log().all().get(serviceURL);
		}
		return RestAssured.given(createReqSpec(headerMap, querymap,includeAuth)).when().get(serviceURL);
	}

	// ======================= POST CALL ================================

	public Response post(Object requestBody, String contentType, String serviceURL, boolean includeAuth,boolean log) {

		if (log) {
			return RestAssured.given(createReqSpec(requestBody, contentType,includeAuth)).log().all().when().log().all()
					.post(serviceURL);
		}
		return RestAssured.given(createReqSpec(requestBody, contentType,includeAuth)).when().post(serviceURL);
	}

	public Response post(Object requestBody, String contentType, Map<String, String> headerMap, String serviceURL,boolean includeAuth,
			boolean log) {

		if (log) {
			return RestAssured.given(createReqSpec(requestBody, contentType, headerMap,includeAuth)).log().all().when().log().all()
					.post(serviceURL);
		}
		return RestAssured.given(createReqSpec(requestBody, contentType, headerMap,includeAuth)).when().post(serviceURL);
	}

	//========================= PUT CALL =================================

	public Response put(Object requestBody, String contentType, String serviceURL, boolean includeAuth,boolean log) {

		if (log) {
			return RestAssured.given(createReqSpec(requestBody, contentType,includeAuth)).log().all().when().log().all()
					.put(serviceURL);
		}
		return RestAssured.given(createReqSpec(requestBody, contentType,includeAuth)).when().put(serviceURL);
	}

	public Response put(Object requestBody, String contentType, Map<String, String> headerMap, String serviceURL,
			boolean includeAuth,boolean log) {

		if (log) {
			return RestAssured.given(createReqSpec(requestBody, contentType, headerMap,includeAuth)).log().all().when().log().all()
					.put(serviceURL);
		}
		return RestAssured.given(createReqSpec(requestBody, contentType, headerMap,includeAuth)).when().put(serviceURL);
	}

	//=========================== PATCH CALL ================================

	public Response patch(Object requestBody, String contentType, String serviceURL, boolean includeAuth,boolean log) {

		if (log) {
			return RestAssured.given(createReqSpec(requestBody, contentType,includeAuth)).log().all().when().log().all()
					.post(serviceURL);
		}
		return RestAssured.given(createReqSpec(requestBody, contentType,includeAuth)).when().post(serviceURL);
	}

	public Response patch(Object requestBody, String contentType, Map<String, String> headerMap, String serviceURL,
			boolean includeAuth,boolean log) {

		if (log) {
			return RestAssured.given(createReqSpec(requestBody, contentType, headerMap,includeAuth)).log().all().when().log().all()
					.patch(serviceURL);
		}
		return RestAssured.given(createReqSpec(requestBody, contentType, headerMap,includeAuth)).when().patch(serviceURL);
	}


	//=========================== DELETE CALL ===========================

	public Response delete(String serviceURL,boolean includeAuth,boolean log) {
		if(log) {
			return RestAssured.given(createReqSpec(includeAuth)).log().all().when().log().all().delete(serviceURL);
		}
		return RestAssured.given(createReqSpec(includeAuth)).when().delete(serviceURL);
	}

	//========================= Access Token Auth 2.0 =======================

	public  String getAccessToken(String serviceUrl, String grantTpye,String clientId,String clientSecret) {

		RestAssured.baseURI="https://test.api.amadeus.com";
		String accessTokenId = given().log().all()
				.headers("Content-Type","application/x-www-form-urlencoded")
				.formParam("grant_type", grantTpye)
				.formParam("client_id", clientId)
				.formParam("client_secret", clientSecret)
				.when()
				.post(serviceUrl)
				.then().log().all()
				.assertThat()
				.statusCode(APIHttpStatus.Ok_200.getCode())
				.extract().path("access_token");
		System.out.println("Token Id : "+accessTokenId);
		return accessTokenId;
	}

	public String getJwtToken(String username, String password,String serviceURL) {

		LoginCredential cred= new LoginCredential(username,password);
		RestAssured.baseURI="https://mdpserverqa.innoventestech.in";
		String jwtToken=RestAssured.given().log().all()
				.contentType(ContentType.JSON)
				.body(cred)
				.when().log().all()
				.post(serviceURL)
				.then().log().all()
				.assertThat()
				.statusCode(APIHttpStatus.Ok_200.getCode())
				.extract().path("access_token");
		return jwtToken;

	}

}
