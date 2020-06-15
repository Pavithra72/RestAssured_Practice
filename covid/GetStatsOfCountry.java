package covid;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetStatsOfCountry {
	@Test
	public void getTop5NewCases(){
		RestAssured.baseURI = "https://covid-19.dataflowkit.com/v1/";
		
		Response response = RestAssured
				.given()
				.log().all()
				.get("india");
		response.prettyPrint();

		if(response.getStatusCode()==200) {
		System.out.println("The Reponse code is verified and it is "+response.getStatusCode());
		}
		long time = response.getTime();
		System.out.println("The time is:" + time);
		System.out.println("The content type is " + response.contentType());
}
}