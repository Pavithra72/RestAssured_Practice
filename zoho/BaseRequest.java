package zoho;

import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;

public class BaseRequest {
	public static String cust_id;
	
	
	@BeforeSuite
	public void setUp() {
		RestAssured.baseURI = "https://subscriptions.zoho.com/api/v1/customers";
		RestAssured.authentication = RestAssured.oauth2("1000.1221eff1591bbb4415e1d250dd441d69.d3ff25ec3b35ac265e7ee25668bdfedc");
	}

}
