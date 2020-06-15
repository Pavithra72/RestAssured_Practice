package zoho;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Create_Customer extends BaseRequest{

	@Test
	public void create_Cust() {
		
Response response = RestAssured
.given()
.log().all()
.contentType(ContentType.JSON)
.body("{\r\n" + 
		"    \"display_name\": \"Malathi Furniture\",\r\n" + 
		"    \"first_name\": \"Malathi\",\r\n" + 
		"    \"last_name\": \"Srini\",\r\n" + 
		"    \"email\": \"Malathi.srini@bowmanfurniture.com\"\r\n" + 
		"    \r\n" + 
		"}")
.post();
response.prettyPrint();
int statusCode = response.getStatusCode();
System.out.println("The status code is: " + statusCode);
JsonPath jsonResponse = response.jsonPath();
cust_id = jsonResponse.get("customer.customer_id");
System.out.println("The customer id is :" + cust_id);

	}
}
