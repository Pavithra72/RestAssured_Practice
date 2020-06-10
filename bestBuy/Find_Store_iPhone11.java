package bestBuy;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Find_Store_iPhone11 {
	@Test
	public void findStoreiPhone(){
		RestAssured.baseURI = "https://api.bestbuy.com/v1/";
		
		Response response = RestAssured
				.given()
				.log().all()
				.param("format", "json")
				.param("show", "sku,name,stores.storeId,stores.name,stores.address")
				.param("apiKey", "qUh3qMK14GdwAs9bO59QRSCJ")
				.get("products(manufacturer=apple&search=mobile&inStorePickup=true)+stores(region=RI)");
		response.prettyPrint();

		if(response.getStatusCode()==200) {
		System.out.println("The Reponse code is verified and it is "+response.getStatusCode());
		}
			}
}
