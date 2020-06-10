package bestBuy;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Find_All_Canon_Products {
	@Test
	public void getAllCanonProd(){
		RestAssured.baseURI = "https://api.bestbuy.com/v1/";
		
		Response response = RestAssured
				.given()
				.log().all()
				.param("format", "json")
				.param("show", "sku,name,salePrice")
				.param("apiKey", "qUh3qMK14GdwAs9bO59QRSCJ")
				.get("products(manufacturer=canon&salePrice<1500&salePrice>1000)");
		response.prettyPrint();

		if(response.getStatusCode()==200) {
		System.out.println("The Reponse code is verified and it is "+response.getStatusCode());
		}
			}
}
