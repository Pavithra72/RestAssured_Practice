package tomtom;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Add_New_Project extends BaseClass{

	@Test
	public void add_Newproject() {
		Response response = RestAssured
				.given()
				.log().all()
				.queryParam("key", apikey)
				.queryParam("adminKey", "rzD7YbpBmOgAMi86Y6bgheiJgnDtDKUlWSTHmlbDN4Z0QA0V")
				.contentType(ContentType.JSON)
				.body("{\r\n" + 
						"  \"name\": \"Factories in London\"\r\n" + 
						"}\r\n" + 
						"")
				.post("projects/project");
		
		response.prettyPrint();
		JsonPath jsonRes = response.jsonPath();
		projectId=	jsonRes.get("id");
		System.out.println("The Project id is: " + projectId);

	}

}
