package com.dellemc.iot.connectivity.provisioning.service.impl;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dellemc.iot.connectivity.provisioning.FTBase;

import io.restassured.RestAssured;
import io.restassured.http.Header;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProvisioningServiceImplTest extends FTBase {

	@Test
	public void testRegistrationCertSigningRequestBlank() throws Exception {
		setResponseFileName("ProvisioningSRSRegistrationCertSigningRequestBlankResponseBody.json");
		RestAssured.given().header(new Header("Content-Type", "application/json"))
				.body(readFileToString("ProvisioningSRSRegistrationCertSigningRequestBlankRequestBody.json")).when()
				.urlEncodingEnabled(false).post("/connectivity/appliance/provisioning").then().log().ifValidationFails()
				.statusCode(400)
				.body(matchesJsonSchemaInClasspath(
						"ProvisioningSRSRegistrationCertSigningRequestBlankResponseBody.json"))
				.body("errorMessage", equalTo("CertRequest value is null/empty")).body("errorCode", equalTo("SR02909"));
	}

	@Test
	public void testRegistrationIpAddressesBlank() throws Exception {
		setResponseFileName("ProvisioningSRSRegistrationIpAddressBlankResponseBody.json");
		RestAssured.given().header(new Header("Content-Type", "application/json"))
				.body(readFileToString("ProvisioningSRSRegistrationIpAddressBlankRequestBody.json")).when()
				.urlEncodingEnabled(false).post("/connectivity/appliance/provisioning").then().log().ifValidationFails()
				.statusCode(400)
				.body(matchesJsonSchemaInClasspath("ProvisioningSRSRegistrationIpAddressBlankResponseBody.json"))
				.body("errorMessage", equalTo("IpAddresses value is null/empty"))
				.body("errorCode", equalTo("SR02910"));
	}

	@Test
	public void testRegistrationMacAddressBlank() throws Exception {
		setResponseFileName("ProvisioningSRSRegistrationMacAddressBlankResponseBody.json");
		RestAssured.given().header(new Header("Content-Type", "application/json"))
				.body(readFileToString("ProvisioningSRSRegistrationMacAddressBlankRequestBody.json")).when()
				.urlEncodingEnabled(false).post("/connectivity/appliance/provisioning").then().log().ifValidationFails()
				.statusCode(400)
				.body(matchesJsonSchemaInClasspath("ProvisioningSRSRegistrationMacAddressBlankResponseBody.json"))
				.body("errorMessage", equalTo("MacAddresses value is null/empty"))
				.body("errorCode", equalTo("SR02911"));
	}

	@Test
	public void testRegistrationModelBlank() throws Exception {
		setResponseFileName("ProvisioningSRSRegistrationModelBlankResponseBody.json");
		RestAssured.given().header(new Header("Content-Type", "application/json"))
				.body(readFileToString("ProvisioningSRSRegistrationModelBlankRequestBody.json")).when()
				.urlEncodingEnabled(false).post("/connectivity/appliance/provisioning").then().log().ifValidationFails()
				.statusCode(400)
				.body(matchesJsonSchemaInClasspath("ProvisioningSRSRegistrationModelBlankResponseBody.json"))
				.body("errorMessage", equalTo("Model Id is null/empty")).body("errorCode", equalTo("SR02912"));
	}

	@Test
	public void testRegistrationKeyIdBlank() throws Exception {
		setResponseFileName("ProvisioningSRSRegistrationKeyIdBlankResponseBody.json");
		RestAssured.given().header(new Header("Content-Type", "application/json"))
				.body(readFileToString("ProvisioningSRSRegistrationKeyIdBlankRequestBody.json")).when()
				.urlEncodingEnabled(false).post("/connectivity/appliance/provisioning").then().log().ifValidationFails()
				.statusCode(400)
				.body(matchesJsonSchemaInClasspath("ProvisioningSRSRegistrationKeyIdBlankResponseBody.json"))
				.body("errorMessage", equalTo("Key Id value is null/empty")).body("errorCode", equalTo("SR02913"));
	}

	
	@Test
	public void testRegistrationUserIdNotValid() throws Exception {
		setResponseFileName("ProvisioningSRSRegistrationUserIdInvalidResponseBody.json");
		RestAssured.given().header(new Header("Content-Type", "application/json"))
				.body(readFileToString("ProvisioningSRSRegistrationUserIdInvalidRequestBody.json")).when()
				.urlEncodingEnabled(false).post("/connectivity/appliance/provisioning").then().log().ifValidationFails()
				.statusCode(400)
				.body(matchesJsonSchemaInClasspath("ProvisioningSRSRegistrationUserIdInvalidResponseBody.json"))
				.body("errorMessage", equalTo("User Id is null/empty")).body("errorCode", equalTo("SR02915"));
	}

	@Test
	public void testRegistrationSiteIdNotValid() throws Exception {
		setResponseFileName("ProvisioningSRSRegistrationSiteIdInvalidResponseBody.json");
		RestAssured.given().header(new Header("Content-Type", "application/json"))
				.body(readFileToString("ProvisioningSRSRegistrationSiteIdInvalidRequestBody.json")).when()
				.urlEncodingEnabled(false).post("/connectivity/appliance/provisioning").then().log().ifValidationFails()
				.statusCode(400)
				.body(matchesJsonSchemaInClasspath("ProvisioningSRSRegistrationSiteIdInvalidResponseBody.json"))
				.body("errorMessage", equalTo("Site Id is null/empty")).body("errorCode", equalTo("SR02916"));
	}

	
	@Test
	public void testRegistrationElmsModelNotValid() throws Exception {
		setResponseFileName("ProvisioningSRSRegistrationElmsModelInvalidResponseBody.json");
		RestAssured.given().header(new Header("Content-Type", "application/json"))
				.body(readFileToString("ProvisioningSRSRegistrationElmsModelInvalidRequestBody.json")).when()
				.urlEncodingEnabled(false).post("/connectivity/appliance/provisioning").then().log().ifValidationFails()
				.statusCode(500)
				.body(matchesJsonSchemaInClasspath("ProvisioningSRSRegistrationElmsModelInvalidResponseBody.json"))
				.body("errorMessage", equalTo("Associated ELMS model name doesn't exist for given model name"))
				.body("errorCode", equalTo("SR02903"));
	}
	 

}
