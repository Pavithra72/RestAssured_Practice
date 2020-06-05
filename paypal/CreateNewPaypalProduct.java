package paypal;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class CreateNewPaypalProduct {
	public static List<String> idLists = new ArrayList<String> ();
   //Create 3 issues using DataProvider by passing the body as file
@DataProvider(name="paypalFiles")

public String[] getData() {
	String[] files = new String[3];
	files[0]="paypal.json";
	files[1]="paypal1.json";
	files[2]="paypal3.json";

	return files;
}

	
	@Test(dataProvider="paypalFiles")
	public void zcreateNewProduct(String fileName){
		File jsonFile = new File(fileName);
		RestAssured.baseURI = "https://api.sandbox.paypal.com/v1/catalogs/products";
		RestAssured.authentication = RestAssured.oauth2("A21AAHfsQhtkLoxw-JL2UhYzYNMub7C_rnocFWYnI7fKQtnfBhftACyHfZCPWI71_urHp43yE0_iMsLzxvK9LC46_k92mYc4w");
//Create Issue in jira by passing body as String
		Response response = RestAssured
				.given()
				.contentType(ContentType.JSON)
				.body(jsonFile)
				.post();
				
				
		System.out.println(response.getStatusCode());
		response.prettyPrint();
		long time = response.getTime();
		System.out.println("The time is:" + time);
		
		JsonPath jsonResponse = response.jsonPath();
		String id = jsonResponse.get("id");
		System.out.println("The id is "+ id);
		
			
			idLists.add(id);
			
			
	}
}
