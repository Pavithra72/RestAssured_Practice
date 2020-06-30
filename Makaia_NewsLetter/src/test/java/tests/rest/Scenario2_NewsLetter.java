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

public class Scenario2_NewsLetter extends RESTAssuredBase_NewsLetter {
	public static String userId;
	
	@BeforeTest
	public void setValues() {
		testCaseName = "Get all users and your user detail and update";
		testDescription = "Get all users and your user detail and update";
		nodes = "NewsLetter report";
		authors = "Pavithra";
		category = "API";
			}

	
	@Test(priority=1)
	public void getUsers(){
		System.out.println("*************Get all the users************");
				Response response = get("users");
					response.prettyPrint();

					// Verify the response status code
					verifyResponseCode(response, 200);	
					
					JsonPath jsonRes = response.jsonPath();
					userId = jsonRes.get("value[0].id");
				
		}
	
	@Test(dependsOnMethods= {"getUsers"},priority=2)
	public void getYourDetails() {
		System.out.println("************Get user detail (yours)**************");
				
		Response res2 = get("users/"+userId+"");
		res2.prettyPrint();

		// Verify the response status code
		verifyResponseCode(res2, 200);	
	
	}
	
	@Test(dependsOnMethods= {"getUsers"},priority=3)
	public void updateSegment() {
		System.out.println("*************update the user details (yours) and verify the updated changes**************");
		String segmentBody = "{\r\n" + 
				"	\"last_name\":\"Arvind\",\r\n" + 
				"	\"language\": \"en_IN\"\r\n" + 
				"}";
		
		
		Response res3 = patchWithJsonAsBody(segmentBody, "users/"+userId+"");
		res3.prettyPrint();

		// Verify the response status code
		verifyResponseCode(res3, 200);	
		
		Response res4 = get("users/"+userId+"");
		JsonPath jsonRes = res4.jsonPath();
		String	 uplastName = jsonRes.get("value[0].last_name");
		if(uplastName.equalsIgnoreCase("Arvind")) {
			System.out.println("The User has been updated");
		}else {
			System.out.println("The user has not been upated");
		}

		
	}
	
}
