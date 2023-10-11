package Day3;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class loggingdemo {
	
	@Test
	void testLogs() {
		
		given()
		
		.when()
			.get("https://www.google.com/")
			
		.then()		
//			.log().body()		//print only body
//			.log().cookies()		//print only cookies
//			.log().headers()		//print only headers
			.log().all() 		//print all the response	
			;		
	}

}
