package uiBank;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ApplyForLoan extends BaseClass{
	
	@Test(dependsOnMethods = {"uiBank.LoginUser.loginUser"})
	public void applyLoan() {
		Response response = RestAssured
				.given()
				.log().all()
				.contentType(ContentType.JSON)
				.body("{\"email\": \"pavieastdale@gmail.com\", \"amount\": 500, \"term\": 3, \"income\": 10000, \"age\": 70289}\r\n" + 
						"")
				.post("quotes/newquote");
		
		response.prettyPrint();
		response.prettyPrint();
		JsonPath jsonResponse = response.jsonPath();
		loanId = jsonResponse.get("quoteid");
	
		System.out.println("The Authorization is: "+ loanId);

				
				
	}


}
