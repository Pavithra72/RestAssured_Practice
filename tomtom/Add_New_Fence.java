package tomtom;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Add_New_Fence extends BaseClass{

	@Test(dependsOnMethods = {"tomtom.RegenarateAdminKey.regenarate_AdminKey"})
	public void add_Newfence() {
		Response response = RestAssured
				.given()
				.log().all()
				.queryParam("key", apikey)
				.queryParam("adminKey", regenAdminKey)
				.contentType(ContentType.JSON)
				.body("{\r\n" + 
						"  \"name\": \"No Entry zone 26\",\r\n" + 
						"  \"type\": \"Feature\",\r\n" + 
						"  \"geometry\": {\r\n" + 
						"    \"radius\": 75,\r\n" + 
						"    \"type\": \"Point\",\r\n" + 
						"    \"shapeType\": \"Circle\",\r\n" + 
						"    \"coordinates\": [-67.137343, 45.137451]\r\n" + 
						"  },\r\n" + 
						"  \"properties\": {\r\n" + 
						"    \"maxSpeedKmh\": 70\r\n" + 
						"  }\r\n" + 
						"}")
				.post("projects/dcfbcd8d-0cb3-474d-b569-d67dadd1f71f/fence");
		
		response.prettyPrint();
		JsonPath jsonRes = response.jsonPath();
		fenceId=	jsonRes.get("id");
		System.out.println("The Fence id is: " + fenceId);

	}

}
