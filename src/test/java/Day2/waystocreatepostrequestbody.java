package Day2;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

/*
 Diffrent ways to create POST request body
 1)POST request body creation using HashMap
 2)POST request body creation using org.json
 3)POST request body creation using POJO class
 4)POST request body creation using external json file data 
 */

public class waystocreatepostrequestbody {
	
	int id;
	
//	1)POST request body creation using HashMap
	
//	@Test(priority = 1)
	void testpostusingHashMap() {
		
		HashMap<String, Object> data = new HashMap<>();
		data.put("name", "Guru");
		data.put("location", "Mandya");
		data.put("phone", "12345");
		
		String courseArr[]= {"C","C#"};
		data.put("courses", courseArr);
		
		given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.post("http://localhost:3000/students")
		
		.then()
			.statusCode(201)
			.body("name", equalTo("Guru"))
			.body("location", equalTo("Mandya"))
			.body("phone", equalTo("12345"))
			.body("courses[0]", equalTo("C"))
			.body("courses[1]", equalTo("C#"))
			.header("Content-Type", "application/json; charset=utf-8")
			.log().all();
	}
	
//	2)POST request body creation using org.json library
	
//	@Test(priority = 1)
	void testpostusingjsonlibrary() {
		
		JSONObject data = new JSONObject();
		data.put("name", "Guru");
		data.put("location", "Mandya");
		data.put("phone", "12345");
		
		String courseArr[]= {"C","C#"};
		data.put("courses", courseArr);
		
		given()
				.contentType("application/json")
				.body(data.toString())
				
		.when()
				.post("http://localhost:3000/students")
			
		.then()
				.statusCode(201)
				.body("name", equalTo("Guru"))
				.body("location", equalTo("Mandya"))
				.body("phone", equalTo("12345"))
				.body("courses[0]", equalTo("C"))
				.body("courses[1]", equalTo("C#"))
				.header("Content-Type", "application/json; charset=utf-8")
				.log().all();
				
		
	}
	
//	 3)POST request body creation using POJO class
	
//	@Test(priority = 1)
	void testpostusingPOJOclass() {
		
		//Object
		POJO_POSTRequest data = new POJO_POSTRequest();
		
		data.setName("Madhu");
		data.setLocation("Mysore");
		data.setPhone("123456543");
		
		String coursesArr[] = {"C","Ruby"};
		data.setCourses(coursesArr);
		
		given()
				.contentType("application/json")
				.body(data)
				
		.when()
				.post("http://localhost:3000/students")
			
		.then()
				.statusCode(201)
				.body("name", equalTo("Madhu"))
				.body("location", equalTo("Mysore"))
				.body("phone", equalTo("123456543"))
				.body("courses[0]", equalTo("C"))
				.body("courses[1]", equalTo("Ruby"))
				.header("Content-Type", "application/json; charset=utf-8")
				.log().all();
				
		
	}
	
//	4)POST request body creation using external json file data 
	
	@Test(priority = 1)
	void testpostusingExternalJsonFile() throws FileNotFoundException {
		
		File f =new File(".\\body.json");		//to open file
		
		FileReader fr = new FileReader(f);		//to read the file
		
		JSONTokener jt = new JSONTokener(fr);	//to split the file
		
		JSONObject data = new JSONObject(jt);	//object
		
		data.put("name", "Madhu");
		data.put("location", "Mysore");
		data.put("phone", "123456543");
		
		String courseArr[]= {"C","Ruby"};
		data.put("courses", courseArr);
		
		
		given() 
				.contentType("application/json")
				.body(data.toString())
				
		.when()
				.post("http://localhost:3000/students")
			
		.then()
				.statusCode(201)
				.body("name", equalTo("Madhu"))
				.body("location", equalTo("Mysore"))
				.body("phone", equalTo("123456543"))
				.body("courses[0]", equalTo("C"))
				.body("courses[1]", equalTo("Ruby"))
				.header("Content-Type", "application/json; charset=utf-8")
				.log().all();
				
		
	}
	
	@Test(priority = 2)
	void deleteTest() {
		
		when()
		.delete("http://localhost:3000/students/4")
		
	
		.then()
			.statusCode(200)
			.log().all();
		
	}

}
