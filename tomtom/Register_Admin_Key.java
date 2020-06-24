package tomtom;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Register_Admin_Key extends BaseClass{
	
	@Test
	public void register_AdminKey() {
		Response response = RestAssured
				.given()
				.log().all()
				.queryParam("key", apikey)
				.contentType(ContentType.JSON)
				.body("{\r\n" + 
						"  \"secret\": \"My very secret secret\"\r\n" + 
						"}")
				.post("register");
		
		response.prettyPrint();

	}

}
