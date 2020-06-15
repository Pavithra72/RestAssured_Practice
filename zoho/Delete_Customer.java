package zoho;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Delete_Customer extends BaseRequest{

	@Test(dependsOnMethods = {"zoho.Create_Customer.create_Cust","zoho.Get_Customer.get_Cust","zoho.Update_customer.update_Cust"})
	public void delete_Cust() {
		
Response response = RestAssured
.given()
.log().all()
.delete(cust_id);
int statusCode = response.getStatusCode();
System.out.println("The status code is: " + statusCode);

	}
}
