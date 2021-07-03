package stepDefinition;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResourcesEnum;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils {
    RequestSpecification request;
    ResponseSpecification res;
    Response response;
    TestDataBuild data = new TestDataBuild();
    static String place_id;

    @Given("Add Place Payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {
        // Write code here that turns the phrase above into concrete actions
	// REQUEST SEPERATED
		request = given().spec(requestSpecification()).body(data.addPlacePayload(name,language,address));
    }

    @Given("Add Place Payload")
    public void add_place_payload() throws IOException {

	// Write code here that turns the phrase above into concrete actions

	// REQUEST SEPERATED
	//request = given().spec(requestSpecification()).body(data.addPlacePayload());
    }

    @When("user calls {string} with {string} http request")
    public void user_calls_with_post_http_request(String resource, String httpMethod) {
	// Write code here that turns the phrase above into concrete actions

	//ENUM - read resource URL from ENUM (Add, Get or Delete API)
	APIResourcesEnum resourceAPI = APIResourcesEnum.valueOf(resource);
	String api = resourceAPI.getResource();
	System.out.println(api);
	
	// Response Spec Builder
	res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

	if(httpMethod.equalsIgnoreCase("POST")) {
	response = request.when().post(api);
    }
	else if(httpMethod.equalsIgnoreCase("GET")) {
		response = request.when().get(api);
	    }
    }

    @Then("the API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer int1) {
	// Write code here that turns the phrase above into concrete actions
	assertEquals(response.getStatusCode(), 200);
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String expectedValue) {
	// Write code here that turns the phrase above into concrete actions
	assertEquals(getJsonPath(response,keyValue).toString(), expectedValue);
    }
    @Then("verify place_Id created maps to {string} using {string}")
    public void verify_place_Id_created_maps_to_using(String expectedName, String resource) throws IOException {
	// requestSpec
	place_id = getJsonPath(response,"place_id");
	request = given().spec(requestSpecification()).queryParam("place_id",place_id);
	user_calls_with_post_http_request(resource, "get");
	String actualName = getJsonPath(response,"name");
	assertEquals(expectedName, actualName);
    }
    
    @Given("Delete Place Payload")
    public void delete_place_payload() throws IOException {
        //Request Specification
	request = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
    }








}
