package hotelBeds;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class BookFirstRoom extends GetSignature {
	@Test(dependsOnMethods = {"hotelBeds.CheckRoomAvailability.checkAvailability","hotelBeds.CheckRates.checkRates"})
	public void bookRoom() {
			
		
		Response response = RestAssured
				.given()
				.log().all()
				.headers(header)
				.body("{\r\n" + 
						"    \"holder\": {\r\n" + 
						"        \"name\": \"Arvind\",\r\n" + 
						"        \"surname\": \"Kannan\"\r\n" + 
						"    },\r\n" + 
						"    \"rooms\": [\r\n" + 
						"        {\r\n" + 
						"            \"rateKey\": \""+chkRateKey+"\",\r\n" + 
						"            \"paxes\": [\r\n" + 
						"                {\r\n" + 
						"                    \"roomId\": 1,\r\n" + 
						"                    \"type\": \"AD\",\r\n" + 
						"                    \"name\": \"Arvind\",\r\n" + 
						"                    \"surname\": \"Kannan\"\r\n" + 
						"                },\r\n" + 
						"                {\r\n" + 
						"                    \"roomId\": 1,\r\n" + 
						"                    \"type\": \"AD\",\r\n" + 
						"                    \"name\": \"Pavi\",\r\n" + 
						"                    \"surname\": \"Srini\"\r\n" + 
						"                }\r\n" + 
						"            ]\r\n" + 
						"        }\r\n" + 
						"    ],\r\n" + 
						"    \"clientReference\": \"IntegrationAgency\",\r\n" + 
						"    \"remark\": \"Test booking by pavithra.\",\r\n" + 
						"    \"tolerance\": 2\r\n" + 
						"}")
				.post("bookings");
		response.prettyPrint();
JsonPath jsonResponse = response.jsonPath();
bookingRef=jsonResponse.get("booking.reference");

	}

}
