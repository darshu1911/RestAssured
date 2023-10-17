package Day5;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

public class FileUploadandDownload {
	
	@Test(priority = 1)
	void singleFileUpload() 
	{
		File myfile = new File("C:\\Users\\aaa\\Postman Files\\Files\\DWSample1-TXT.txt");
		
		given()
			.multiPart("file",myfile)
			.contentType("multipart/form-data")
			
		.when()
			.post("http://localhost:8080/uploadFile")

		.then()
			.statusCode(200)
			.body("fileName", equalTo("DWSample1-TXT.txt"))
			.body("size", equalTo(607))
			.log().all();
	}
	
//	@Test
	void multipleFileUpload() 
	{
		File myfile1 = new File("C:\\Users\\aaa\\Postman Files\\Files\\DWSample1-TXT.txt");
		File myfile2 = new File("C:\\Users\\aaa\\Postman Files\\Files\\DWSample2-TXT.txt");
		
		given()
			.multiPart("files",myfile1)
			.multiPart("files",myfile2)
			.contentType("multipart/form-data")
			
		.when()
			.post("http://localhost:8080/uploadMultipleFiles")

		.then()
			.statusCode(200)
			.body("[1].fileName", equalTo("DWSample2-TXT.txt"))
			.body("[1].size", equalTo(2859))
			.log().all();
	}

	
//	@Test
	void multipleFileUpload2() 
	{
		File myfile1 = new File("C:\\Users\\aaa\\Postman Files\\Files\\DWSample1-TXT.txt");
		File myfile2 = new File("C:\\Users\\aaa\\Postman Files\\Files\\DWSample2-TXT.txt");
		File myfile3 = new File("C:\\Users\\aaa\\Postman Files\\Files\\DWSample2-TXT.txt");
		File myfile4 = new File("C:\\Users\\aaa\\Postman Files\\Files\\DWSample2-TXT.txt");
		
		File filearr[] = {myfile1,myfile2,myfile3,myfile4};
		
		given()
			.multiPart("files",filearr)
			.contentType("multipart/form-data")
			
		.when()
			.post("http://localhost:8080/uploadMultipleFiles")

		.then()
			.statusCode(200)
			.body("[1].fileName", equalTo("DWSample2-TXT.txt"))
			.body("[1].size", equalTo(2859))
			.log().all();
	}
	
	@Test(priority = 2)
	void fileDownload() {
		
		given()
		
		.when()
			.get("http://localhost:8080/downloadFile/DWSample1-TXT.txt")
		
		.then()
			.statusCode(200);
	}
}
