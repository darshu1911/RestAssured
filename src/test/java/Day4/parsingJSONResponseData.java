package Day4;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

import io.restassured.http.ContentType;
import io.restassured.internal.http.Status;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;


public class parsingJSONResponseData {
	
//	@Test(priority = 1)
	void testJSONResponse() 
	
	{
			//Approach1
		
		/*given()
			.contentType(ContentType.JSON)
		
		.when()
			.get("http://localhost:3000/store")
			
		.then()
			.statusCode(200)
			.header("Content-Type", "application/json; charset=utf-8")
			.body("book[3].title", equalTo("The Lord of the Rings"));*/
		
		//Approach2
		
		Response res = given()
				.contentType(ContentType.JSON)
				
					.when()
						.get("http://localhost:3000/store");
			
					Assert.assertEquals(res.getStatusCode(),200);		//validation1
					Assert.assertEquals(res.header("Content-Type"),"application/json; charset=utf-8");
					
					//to capture specific field
				String bookname =res.jsonPath().get("book[3].title").toString();	
				Assert.assertEquals(bookname, "The Lord of the Rings");
			
	}
	
	@Test(priority = 1)
	void testJSONResponseBodyData() 
	
	{
		//Approach2 with response
		Response res = given()
					.contentType(ContentType.JSON)
				
					.when()
						.get("http://localhost:3000/store");
			
					/*Assert.assertEquals(res.getStatusCode(),200);		//validation1
					Assert.assertEquals(res.header("Content-Type"),"application/json; charset=utf-8");
					
					//to capture specific field
					String bookname =res.jsonPath().get("book[3].title").toString();	
					Assert.assertEquals(bookname, "The Lord of the Rings");*/
		
			//use JSON object class to traverse or parse the json response
			JSONObject jo = new JSONObject(res.asString());		//to convert to JSON object type
			
			//to read the data from json array
			/*for(int i=0;i<jo.getJSONArray("book").length();i++) 
			{
				String booktitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
				System.out.println(booktitle);
			
			}*/
			
			//search for title of the Book in JSON  validation 1
			boolean Status =false;
			for(int i=0;i<jo.getJSONArray("book").length();i++) 
			{
				String booktitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
				if(booktitle.equals("The Lord of the Rings")) {
					Status = true;
					break;
				}
			}
			Assert.assertEquals(Status, true);
			
			//validate total price of books
			double totalprice =0;
			for(int i=0;i<jo.getJSONArray("book").length();i++) 
			{
				String price = jo.getJSONArray("book").getJSONObject(i).get("price").toString();
				
				totalprice = totalprice+Double.parseDouble(price);
			
			}
		System.out.println("Total price of the books is "+totalprice);
		Assert.assertEquals(totalprice, 572.0);
	}

}
