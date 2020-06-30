package lib.rest;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lib.utils.DataInputProvider;
import lib.utils.HTMLReporter;

public class PreAndTest_NewsLetter extends HTMLReporter{
	public static String accessToken;
	
	public String dataFileName, dataFileType;	
	
	
	@BeforeSuite
	public void beforeSuite() {
		startReport();
	}
	
	@BeforeTest
	public void beforeTest() {
		
	}
	
	
	@BeforeClass
	public void beforeClass() throws FileNotFoundException, IOException {
		startTestCase(testCaseName, testDescription);
		Properties prop = new Properties();
		prop.load(new FileInputStream(new File("./src/test/resources/config.properties")));

		
		RestAssured.baseURI = "https://"+prop.getProperty("server_newsletter")+"/";
		Response response = RestAssured
				.given()
				.header("Content-Type","application/json")
				.header("Authorization", "Basic eWFibXljbHpfRmJmdzlIX3RRUWw1YUlfbVZ0UFlWbmVwX1NHMmVReWJ5ejp5bTVzdnpvMw==")
				.contentType(ContentType.JSON)
				.body("{\r\n" + 
						"  \"username\": \"pavieastdale@gmail.com\",\r\n" + 
						"  \"password\": \"Ashwath@31\",\r\n" + 
						"  \"grant_type\": \"https://nl2go.com/jwt\"\r\n" + 
						"}\r\n" + 
						"")
				.post("oauth/v2/token");
		JsonPath jsonResponse = response.jsonPath();
		accessToken	 = jsonResponse.get("access_token");
		RestAssured.authentication = RestAssured.oauth2(accessToken);


	}
	
	
	@BeforeMethod
	public void beforeMethod() throws FileNotFoundException, IOException {
		//for reports		
		svcTest = startTestModule(nodes);
		svcTest.assignAuthor(authors);
		svcTest.assignCategory(category);
		
//		Properties prop = new Properties();
	//	prop.load(new FileInputStream(new File("./src/test/resources/config.properties")));
		
	//	RestAssured.baseURI = "https://"+prop.getProperty("server")+"/"+prop.getProperty("resources")+"/";
	//	RestAssured.authentication = RestAssured.basic(prop.getProperty("username"), prop.getProperty("password"));
		
		
	}

	@AfterMethod
	public void afterMethod() {
		
	}
	
	@AfterClass
	public void afterClass() {
		
	}
	
	@AfterTest
	public void afterTest() {
		
	}

	@AfterSuite
	public void afterSuite() {
		endResult();
	}

	@DataProvider(name="fetchData")
	public  Object[][] getData(){
		if(dataFileType.equalsIgnoreCase("Excel"))
			return DataInputProvider.getSheet(dataFileName);	
		else if(dataFileType.equalsIgnoreCase("JSON")){
			Object[][] data = new Object[1][1];
			data[0][0] = new File("./data/"+dataFileName+"."+dataFileType);
			System.out.println(data[0][0]);
			return data;
		}else {
			return null;
		}
			
	}

	@Override
	public long takeSnap() {
		return 0;
	}	

	
	
}
