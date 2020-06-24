package tomtom;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RegenarateAdminKey extends BaseClass{
	
	@Test
	public void regenarate_AdminKey() {
		Response response = RestAssured
				.given()
				.log().all()
				.queryParam("key", apikey)
				.contentType(ContentType.JSON)
				.body("{\r\n" + 
						"  \"secret\": \"My very secret secret\"\r\n" + 
						"}")
				.post("regenerateKey");
		
		response.prettyPrint();
		
		JsonPath jsonRes = response.jsonPath();
		regenAdminKey = jsonRes.get("adminKey");
		System.out.println("The Regenerated admin key is: " + regenAdminKey);

	}

}
