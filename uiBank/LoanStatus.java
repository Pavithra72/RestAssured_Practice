package uiBank;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class LoanStatus extends BaseClass{
	
	@Test(dependsOnMethods = {"uiBank.LoginUser.loginUser","uiBank.ApplyForLoan.applyLoan"})
	public void loanStatus() {
		Response response = RestAssured
				.given()
				.log().all()
				.contentType(ContentType.JSON)
				.get("quotes/"+loanId+"");
		
		response.prettyPrint();
		
				
				
	}


}
