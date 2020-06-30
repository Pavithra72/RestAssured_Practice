package tests.rest;

import java.util.LinkedHashMap;
import java.util.Map;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lib.rest.RESTAssuredBase;
import lib.rest.RESTAssuredBase_NewsLetter;

public class Scenario1_NewsLetter extends RESTAssuredBase_NewsLetter {
	public static String listId;
	public static String groupId;
	
	@BeforeTest
	public void setValues() {
		testCaseName = "Create List, Add Segment and Delete List";
		testDescription = "Create List, Add segment to list and update it and delete the list";
		nodes = "NewsLetter report";
		authors = "Pavithra";
		category = "API";
			}

	
	@Test(priority =1)
	public void createList(){
		System.out.println("*************Create List************");
		Map<String, String> headers = new LinkedHashMap<String,String>();
		headers.put("Content-Type","application/json");
		headers.put("Authorization", "Bearer "+accessToken+"");
		String createListBody = "{\r\n" + 
				"  \"name\": \"My new List\",\r\n" + 
				"  \"uses_econda\": false,\r\n" + 
				"  \"uses_googleanalytics\": true,\r\n" + 
				"  \"has_opentracking\": true,\r\n" + 
				"  \"has_clicktracking\": true,\r\n" + 
				"  \"has_conversiontracking\": false,\r\n" + 
				"  \"imprint\": \"http://example.org/imprint\",\r\n" + 
				"  \"header_from_email\": \"from@example.org\",\r\n" + 
				"  \"header_from_name\": \"From Name\",\r\n" + 
				"  \"header_reply_email\": \"reply@example.org\",\r\n" + 
				"  \"header_reply_name\": \"Reply Name\",\r\n" + 
				"  \"tracking_url\": null,\r\n" + 
				"  \"landingpage\": \"http://example.org/unsubscribe-landingpage\",\r\n" + 
				"  \"use_ecg_list\": false\r\n" + 
				"}";
		
		Response response = postWithJsonAsBody(createListBody, "lists");
//		Response response = postWithHeaderAndJsonBody(headers, createListBody, "lists");
					response.prettyPrint();

					// Verify the response status code
					verifyResponseCode(response, 201);	
					
					// Verify the response time
					verifyResponseTime(response, 3000);
					JsonPath jsonRes = response.jsonPath();
					listId = jsonRes.get("value[0].id");
				
		}
	
	@Test(dependsOnMethods= {"createList"},priority=2)
	public void createSegment() {
		System.out.println("************Create a new segment for the new List**************");
		String segmentBody = "{\r\n" + 
				"	\"list_id\": \""+listId+"\",\r\n" + 
				"	\"name\": \"New  Segment Value\",\r\n" + 
				"	\"is_dynamic\": false\r\n" + 
				"}";
		
		
		Response res2 = postWithJsonAsBody(segmentBody, "groups");
		res2.prettyPrint();

		// Verify the response status code
		verifyResponseCode(res2, 201);	
		
				JsonPath jsonRes = res2.jsonPath();
		groupId = jsonRes.get("value[0].id");

		
		
	}
	
	@Test(dependsOnMethods= {"createSegment"},priority=3)
	public void updateSegment() {
		System.out.println("*************Update the segment**************");
		String segmentBody = "{\r\n" + 
				"	\"name\": \"A new Segment Value Name\"\r\n" + 
				"}";
		
		
		Response res3 = patchWithJsonAsBody(segmentBody, "groups/"+groupId+"");
		res3.prettyPrint();

		// Verify the response status code
		verifyResponseCode(res3, 200);	
		
					}
	@Test(dependsOnMethods= {"createList"},priority=4)
	public void getList() {
		System.out.println("**********Get all the list and verify the existance of new list************");
		Response res4 = get("lists");
		res4.prettyPrint();

		// Verify the response status code
		verifyResponseCode(res4, 200);	
		JsonPath jsonRes = res4.jsonPath();
		String checkNewList = jsonRes.get("value[0].id");
		if(checkNewList.equalsIgnoreCase(listId)) {
			System.out.println("The list is available");
		}else {
			System.out.println("The list is not available");
		}


	
	}

	@Test(dependsOnMethods= {"createList"},priority=5)
	public void deleteList() {
		System.out.println("**********Delete the newly created list************");
		Response res5 = delete("lists/"+listId+"");

		// Verify the response status code
		verifyResponseCode(res5, 204);	
			
	}


}
