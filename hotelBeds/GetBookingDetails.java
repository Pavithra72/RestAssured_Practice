package hotelBeds;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetBookingDetails extends GetSignature {
	@Test(dependsOnMethods = {"hotelBeds.CheckRoomAvailability.checkAvailability","hotelBeds.CheckRates.checkRates","hotelBeds.BookFirstRoom.bookRoom"})
	public void getBookDetails() {
			
		
		Response response = RestAssured
				.given()
				.log().all()
				.headers(header)
				.get("bookings/"+bookingRef+"");
		response.prettyPrint();

	}

}
