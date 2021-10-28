package com.assessment.api;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class ApiTestRunner {

	String base_url = "https://eacp.energyaustralia.com.au/codingtest";

	@Test(priority = 0)
	public void responsecode_validation() {

		// Status code validation - 200 Success
		RestAssured.given().when().get(base_url + "/api/v1/festivals").then().assertThat().statusCode(200);

	}

	@Test(priority = 1)
	public void schema_validation() {

		// Validate json Schema
		RestAssured.given().when().get(base_url + "/api/v1/festivals").then().assertThat()
				.body(matchesJsonSchemaInClasspath("JsonSchemaFile.json"));

	}

	@Test(priority = 2)
	public void data_validation() {

		// Validate data
		Response response = RestAssured.given().when().get(base_url + "/api/v1/festivals");
		Assert.assertEquals(response.asString().contains("LOL-palooza"), true , "Response body contains LOL-palooza");
	}

}
