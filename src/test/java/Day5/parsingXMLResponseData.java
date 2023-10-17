package Day5;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class parsingXMLResponseData {
	
//	@Test(priority = 1)
	void testXMLresopnse() {
		
		/* given()
		
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?Page=1")	
		.then()
			.statusCode(200)
			.header("Content-Type", "application/xml; charset=utf-8")
			.body("TravelerinformationResponse.page", equalTo("1"))
			.body("TravelerinformationResponse.travelers.Travelerinformation[0].id", equalTo("11133"))
			;
		*/
		
		//Approach 2 by returning the response into a variable.
	
	Response res =	given()
		
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?Page=1");
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.header("Content-Type"), "application/xml; charset=utf-8"); 
		
		String pageNo = res.xmlPath().get("TravelerinformationResponse.page").toString();
		Assert.assertEquals(pageNo, "1");
		
		String ID = res.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].id").toString();
		Assert.assertEquals(ID, "11133");
	}
	
	
	
	@Test(priority = 1)
	void testXMLResopnseBody() {
	
	Response res =	given()
		
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?Page=1");
		
		XmlPath xmlobj = new XmlPath(res.asString());
	
		//verify total number of travellers 
	List<String> travelers = xmlobj.getList("TravelerinformationResponse.travelers.Travelerinformation");
	Assert.assertEquals(travelers.size(), 10);
	
	// verfiy traveler name is present in response
	List<String> travelers_ids = xmlobj.getList("TravelerinformationResponse.travelers.Travelerinformation.id");
	
	boolean status = false;
	for(String travelersid:travelers_ids)
	{
//		System.out.println(travelersid);
		if(travelersid.equals("11133")) 
		{
			status = true;
			break;
		}
		Assert.assertEquals(status, true);
	}
	
	
		
		
		
	}

}
