package hotelBeds;

import java.io.File;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CheckRoomAvailability extends GetSignature {
	@Test
	public void checkAvailability() {
		File jsonFile = new File("roomAvailability.json");

		
		Response response = RestAssured
				.given()
				.log().all()
				.headers(header)
				.body(jsonFile)
				.post("hotels");
		
		response.prettyPrint();
		JsonPath jsonResponse = response.jsonPath();
		List<Object> hotelsList = jsonResponse.getList("hotels.hotels");
		System.out.println("The Hotels Availability  is "+ hotelsList.size());
		
	for(int i=1;i<hotelsList.size();i++)
		{
		 rateType = jsonResponse.get("hotels.hotels["+i+"].rooms[0].rates[0].rateType");
	//	System.out.println("rate type: " +rateType);
	if(rateType.toString().equalsIgnoreCase("BOOKABLE")){
		   rateKey = jsonResponse.get("hotels.hotels["+i+"].rooms[0].rates[0].rateKey");
		   rateKeys.add(rateKey);
	  // System.out.println(rateKey);
		   }
	    }	
	}

}
