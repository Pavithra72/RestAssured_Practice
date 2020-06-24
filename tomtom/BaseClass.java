package tomtom;

import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;

public class BaseClass {
	public static String apikey = "1SFWj4feUAQd4BWbuLX3Shc9dI6BHhg6";
	public static String projectId ;
	public static String regenAdminKey;
	public static String fenceId;
	
	@BeforeSuite
	public void setUp() {
		RestAssured.baseURI = "https://api.tomtom.com/geofencing/1";
		
	}

}
