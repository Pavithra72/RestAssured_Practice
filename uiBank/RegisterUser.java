package uiBank;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RegisterUser extends BaseClass{
	@Test
	public void registerUser() {
		Response response = RestAssured
				.given()
				.log().all()
				.contentType(ContentType.JSON)
				.body("{\"firstName\":\"Arvind\",\"title\":\"mr\",\"lastName\":\"Srini\",\"gender\":\"male\",\"employmentStatus\":\"Full-time\",\"age\":\"01/01/1990\",\"maritalStatus\":\"Single\",\"username\":\"arvi123\",\"email\":\"arvi@yahoo.com\",\"password\":\"Arvi.7289\",\"type\":\"customer\"}")
				.post("users");
		
		response.prettyPrint();

		
		
	}

}
