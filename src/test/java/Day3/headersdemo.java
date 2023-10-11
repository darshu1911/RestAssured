package Day3;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class headersdemo {
	
	
//	@Test(priority = 1)
	void testheaders() {
		
		
		given()
		
		.when()
			.get("https://www.google.com/")
		
		.then()
			.header("Content-Type", "text/html; charset=ISO-8859-1")
			.and()
			.header("Content-Encoding", "gzip")
			.and()
			.header("Server", "gws")
			.log().headers();
			
	}

	
	@Test(priority = 2)
	void getheaders() {
		
		
		Response res = given()
		
		.when()
			.get("https://www.google.com/");
		
		//get single header info
//		String header_value = res.getHeader("Content-Type");
//			System.out.println("The value of Content-Type is:"+header_value);
		
		//get all the headers info
		Headers myheaders=res.getHeaders();
		
		for(Header hd:myheaders) {
			System.out.println(hd.getName()+"	"+hd.getValue());
		}
		
	}

	
}
