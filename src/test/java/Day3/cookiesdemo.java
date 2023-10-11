package Day3;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

public class cookiesdemo {
	
	
//	@Test(priority = 1)
	void testcookies() {
		
		
		given()
		
		.when()
			.get("https://www.google.com/")
		
		.then()
			.cookie("AEC", "Ackid1T2dQWoVd_eaZor9Iq08cFYaOyScjrWR02iROlYwvbg5z1gLLNU-w")
			.log().all();
	}
	
	
	@Test(priority = 2)
	void getCookieinfo() {
		
		
		Response res =given()
		
		.when()
			.get("https://www.google.com/");
		
		//get single cookie info
//		String cookie_value = res.cookie("AEC"); 
//		System.out.println("value of the cookie is ====>"+cookie_value);
		
		//get single all cookies info
		Map<String,String>cookies_values=res.getCookies();

//		System.out.println(cookies_values.keySet());
		
		for(String k:cookies_values.keySet()) {
			
			String cookie_value = res.cookie(k); 
			System.out.println(k+"	"+cookie_value);

		}
		
	}

}
