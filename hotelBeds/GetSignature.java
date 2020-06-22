package hotelBeds;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;

public class GetSignature {
	public static String  apiKey = "e32adbe05f06e2f61611f4ffc54a603e";
	public static String Secret = "20a0260fd2";
	public static String signature;
	public static Object  rateType,rateKey;
	public static Object chkRateKey;
	public static Object bookingRef;
	public static List<Object> rateKeys = new ArrayList<Object>();
 public static Map<String,String> header = new LinkedHashMap<String, String>();
 
 @BeforeSuite
	public void getSignature() {
				 signature = org.apache.commons.codec.digest.DigestUtils
							.sha256Hex(apiKey + Secret + System.currentTimeMillis() / 1000);

		System.out.println("Signature: " + signature);
		RestAssured.baseURI ="https://api.test.hotelbeds.com/hotel-api/1.0/";
		header.put("Api-key", apiKey);
		header.put("X-Signature", signature);
		header.put("Accept", "application/json");
		header.put("Accept-Encoding", "gzip");
		header.put("Content-Type", "application/json");

		
		
	}

}
