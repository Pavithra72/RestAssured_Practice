package openweatherman;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetWeatherForCity {
	@Test
	public void getWeather(){
		RestAssured.baseURI = "https://api.openweathermap.org/data/2.5/weather";
		
		Response response = RestAssured
				.given()
				.log().all()
				.param("q", "Chennai")
				.param("appid", "ea73207eef272669200945f5ae1e39da")
				.get();
		response.prettyPrint();

		if(response.getStatusCode()==200) {
		System.out.println("The Reponse code is verified and it is "+response.getStatusCode());
		}
		long time = response.getTime();
		System.out.println("The time is:" + time);
	JsonPath jsonResponse = response.jsonPath();
	float maxTemp = jsonResponse.get("main.temp_max");
	System.out.println("The maximum temperature is " + maxTemp);
	int sunsetTime = jsonResponse.get("sys.sunset");
System.out.println("The sunset time is " + sunsetTime);
float windSpeed = jsonResponse.get("wind.speed");
System.out.println("The wind speed is " + windSpeed);

//		var maxTemp = response.main.temp_max;
//		console.log("The maximum temperature is " + maxTemp);
//		var sunsetTime = response.sys.sunset;
//		console.log("The sunset time is " + sunsetTime);
//		var windspeed = response.wind.speed;
//		console.log("The wind speed is " + windspeed);
		
	}
}
