package Day5;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class parsingXMLResponseData {
	
	@Test(priority = 1)
	void testXMLresopnse() {
		
		given()
		
		.when()
		
		.then()
			.statusCode(200);
		
	}

}
