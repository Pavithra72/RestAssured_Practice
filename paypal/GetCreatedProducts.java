package paypal;

import java.io.File;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class GetCreatedProducts{
  @Test(dependsOnMethods = {"paypal.CreateNewPaypalProduct.createNewProduct"})
	
	public void getCreatedProducts(){
		
		RestAssured.baseURI = "https://api.sandbox.paypal.com/v1/catalogs/products";
		RestAssured.authentication = RestAssured.oauth2("A21AAHfsQhtkLoxw-JL2UhYzYNMub7C_rnocFWYnI7fKQtnfBhftACyHfZCPWI71_urHp43yE0_iMsLzxvK9LC46_k92mYc4w");

		Response response = RestAssured
				.given()
				.param("page_size", "100")
				.get();
		response.prettyPrint();
			JsonPath jsonResponse= response.jsonPath();	
			List<String> listId = jsonResponse.getList("products.id");
			int size = listId.size();
			System.out.println(size);
			for(int i=0;i < CreateNewPaypalProduct.idLists.size();i++) {
				for(int j = 0; j <size; j ++) {
					if(CreateNewPaypalProduct.idLists.get(i).equalsIgnoreCase(listId.get(j))) {
						System.out.println("The created product "+CreateNewPaypalProduct.idLists.get(i)+" is available in the list");
					}
				}
			}
	}
}
