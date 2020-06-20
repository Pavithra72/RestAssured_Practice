package uiBank;

import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;

public class BaseClass {
	
	public static String userAuth;
	public static String user;
	public static String loanId;
	
	@BeforeSuite
	public void setUp() {
		RestAssured.baseURI = "https://uibank-api.azurewebsites.net/api";
		
	}

}
