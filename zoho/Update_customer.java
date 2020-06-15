package zoho;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Update_customer extends BaseRequest{

	@Test(dependsOnMethods = {"zoho.Create_Customer.create_Cust","zoho.Get_Customer.get_Cust"})
	public void update_Cust() {
		
Response response = RestAssured
.given()
.log().all()
.contentType(ContentType.JSON)
.body("{\r\n" + 
		"    \"phone\": 23467278,\r\n" + 
		"    \"mobile\": 938237475,\r\n" + 
		"    \"shipping_address\": {\r\n" + 
		"        \"attention\": \"Pavithra Srinivasan\",\r\n" + 
		"        \"street\": \"T.Nagar Street\",\r\n" + 
		"        \"city\": \"Chennai Lake City\",\r\n" + 
		"        \"state\": \"IN\",\r\n" + 
		"        \"zip\": 600061,\r\n" + 
		"        \"country\": \"INDIA\",\r\n" + 
		"        \"fax\": 4527389\r\n" + 
		"    }\r\n" + 
		"\r\n" + 
		"}")
.put(cust_id);
response.prettyPrint();
int statusCode = response.getStatusCode();
System.out.println("The status code is: " + statusCode);
JsonPath jsonResponse = response.jsonPath();
cust_id = jsonResponse.get("customer.customer_id");
System.out.println("The customer id is :" + cust_id);

	}
}
