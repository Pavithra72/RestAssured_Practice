package tomtom;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.containsString;


public class GetFenceTransition_Validate extends BaseClass {
	@Test(dependsOnMethods = {"tomtom.RegenarateAdminKey.regenarate_AdminKey","tomtom.Add_New_Fence.add_Newfence"})
	public void getFence_Transition() {
		ValidatableResponse response = RestAssured
				.given()
				.log().all()
				.queryParam("key", apikey)
				.queryParam("from", "2020-06-24T01:00:00")
				.queryParam("to", "2020-06-24T23:00:00")
				.contentType(ContentType.JSON)
				.get("transitions/fences/"+fenceId+"")
		.then()
		.statusCode(200)
		.body(containsString(fenceId));
		
	}

}
