package hotelBeds;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CheckRates extends GetSignature {
	/**
	 * 
	 */
	@Test(dependsOnMethods = {"hotelBeds.CheckRoomAvailability.checkAvailability"})
	public void checkRates() {
		//for(int i = 0; i< rateKeys.size();i++) {
			
		
		Response response = RestAssured
				.given()
				.log().all()
				.headers(header)
				.body("{\r\n" + 
						"  \"rooms\": [\r\n" + 
						"    {\r\n" + 
						"      \"rateKey\": \""+rateKeys.get(0)+"\"\r\n" + 
						"    }\r\n" + 
						"  ]\r\n" + 
						"}")
				.post("checkrates");
		response.prettyPrint();
		
		JsonPath jsonRes = response.jsonPath();
		 chkRateKey = jsonRes.get("hotel.rooms[0].rates[0].rateKey");
		 System.out.println("The rate Key is: "+ chkRateKey);

//	}
	}

}
