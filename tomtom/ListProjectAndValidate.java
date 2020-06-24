package tomtom;

import static org.hamcrest.Matchers.containsString;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import static org.hamcrest.CoreMatchers.*;

public class ListProjectAndValidate extends BaseClass {
	
	@Test(dependsOnMethods = {"tomtom.Add_New_Project.add_Newproject"})
	public void listProjects_Validate() {
		
		ValidatableResponse response = RestAssured
				.given()
				.log().all()
				.queryParam("key", apikey)
				.get("projects")
				.then()
				.statusCode(200)
				.body(containsString(projectId));

				
		
		
		
	}

}
