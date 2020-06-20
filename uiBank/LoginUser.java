package uiBank;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class LoginUser extends BaseClass {
	
	@Test
	public void loginUser() {
		Response response = RestAssured
				.given()
				.log().all()
				.contentType(ContentType.JSON)
				.body("{\r\n" + 
						"    \"username\": \"ashwath123\", \r\n" + 
						"    \"password\": \"Ashwath@31\"\r\n" + 
						"    }\r\n" + 
						"")
				.post("users/login");
		
		response.prettyPrint();
		JsonPath jsonResponse = response.jsonPath();
		userAuth = jsonResponse.get("id");
		user = jsonResponse.get("userId");

		System.out.println("The Authorization is: "+ userAuth);
		System.out.println("The User ID is: "+user);
	}

}
