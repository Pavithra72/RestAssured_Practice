package uiBank;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetAccountDetails extends BaseClass {
	
	@Test(dependsOnMethods = {"uiBank.LoginUser.loginUser"})
	public void getAcctDetails() {
		Response response = RestAssured
				.given()
				.log().all()
				.contentType(ContentType.JSON)
				.header("Authorization",userAuth)
				.queryParam("filter[where][userId]",user)
				.get("accounts");
		
		response.prettyPrint();
				
				
				
	}

}
