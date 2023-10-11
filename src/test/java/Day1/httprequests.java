package Day1;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

/*
 given()
 		content type, set coockies, add auth, add param, set headers info etc,..	
 		
 when()
 		all the request urls like get, post, put and delete...
 		
 then()
 		all the validations like status code, extract response, extract headers cookies and response body..
 
 */

public class httprequests {
	
	int id;
	
	@Test(priority = 1)
	void getUser()
	
	{
		given()
		
		.when()
			.get("https://reqres.in/api/users?page=2")
		
		.then()
			.statusCode(200)
			.body("page",equalTo(2))
			.log().all();
			
	}
	
	
	@Test(priority = 2)
	void createUser() {
		
		HashMap data = new HashMap();
		data.put("name", "Darshan");
		data.put("job", "Testing");
		
		
		id = given()
			.contentType("application/json")
			.body(data)

		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
			
//		.then()
//			.statusCode(201)
//			.log().all();
	}
	
	@Test(priority = 3,dependsOnMethods = {"createUser"})
	void updateUser() {
		
		HashMap data = new HashMap();
		data.put("name", "Robbert");
		data.put("job", "Devops");
		
		
		given()
			.contentType("application/json")
			.body(data)

		.when()
			.put("https://reqres.in/api/users/"+id)
		
		.then()
			.statusCode(200)
			.log().all();
			

	}
	
	@Test(priority = 4)
	void deleteUser() {
	
		when()
			.delete("https://reqres.in/api/users/\"+id")

		.then()
			.statusCode(204)
			.log().all();
	}
	
}
