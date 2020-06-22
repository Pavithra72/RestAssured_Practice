package hotelBeds;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteBooking extends GetSignature {
	@Test(dependsOnMethods = {"hotelBeds.CheckRoomAvailability.checkAvailability","hotelBeds.CheckRates.checkRates","hotelBeds.BookFirstRoom.bookRoom","hotelBeds.GetBookingDetails.getBookDetails"})
	public void deleteBooking() {
			
		
		Response response = RestAssured
				.given()
				.log().all()
				.headers(header)
				.queryParam("cancellationFlag", "CANCELLATION")
				.delete("bookings/"+bookingRef+"");
		response.prettyPrint();

	}

}
