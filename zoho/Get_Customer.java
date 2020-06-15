package zoho;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Get_Customer extends BaseRequest{

	@Test(dependsOnMethods = {"zoho.Create_Customer.create_Cust"})
	public void get_Cust() {
		
Response response = RestAssured
.given()
.log().all()
.get(cust_id);

response.prettyPrint();
int statusCode = response.getStatusCode();
System.out.println("The status code is: " + statusCode);
JsonPath jsonResponse = response.jsonPath();
cust_id = jsonResponse.get("customer.customer_id");
System.out.println("The customer id is :" + cust_id);

	}
}
