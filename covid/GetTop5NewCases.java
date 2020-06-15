package covid;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetTop5NewCases {
	@Test
	public void getTop5NewCases(){
		RestAssured.baseURI = "https://covid-19.dataflowkit.com/v1";
		
		Response response = RestAssured
				.given()
				.log().all()
				.get();
		response.prettyPrint();

		if(response.getStatusCode()==200) {
		System.out.println("The Reponse code is verified and it is "+response.getStatusCode());
		}
		long time = response.getTime();
		System.out.println("The time is:" + time);
		Map<Integer,String> mapCases = new TreeMap<Integer,String>(Collections.reverseOrder());
	JsonPath jsonResponse = response.jsonPath();
	List<String> listOfCountry = jsonResponse.getList("Country_text");
	List<String> listOfNewCases = jsonResponse.getList("\"New Cases_text\"");
	for(int i=0;i<listOfCountry.size();i++) {
	if(listOfNewCases.get(i)!="" && listOfNewCases.get(i)!=null && !listOfCountry.get(i).equalsIgnoreCase("world")) {
			//System.out.println(listOfCountry.get(i));
			//System.out.println(listOfNewCases.get(i));
		String repCases = listOfNewCases.get(i).replaceAll("\\D", "");
		int intCases = Integer.parseInt(repCases);
	//	System.out.println(intCases);
		mapCases.put(intCases, listOfCountry.get(i) );
		}
		
	}
	int j=0;
	for(Entry<Integer,String> eachEntry : mapCases.entrySet() ) {
		if(j<5) {
System.out.println(eachEntry.getKey() + " -- "+ eachEntry.getValue());
		j++;
		}
	}
	

}
}