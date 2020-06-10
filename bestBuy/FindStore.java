package bestBuy;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class FindStore {
	@Test
	public void findStore(){
		RestAssured.baseURI = "https://api.bestbuy.com/v1/stores(area(02864,10))";
		
		Response response = RestAssured
				.given()
				.log().all()
				.param("format", "json")
				.param("show", "storeId,storeType,name,address,distance")
				.param("pageSize", "1")
				.param("apiKey", "qUh3qMK14GdwAs9bO59QRSCJ")
				.get();
		response.prettyPrint();

		if(response.getStatusCode()==200) {
		System.out.println("The Reponse code is verified and it is "+response.getStatusCode());
		}
			}
}
