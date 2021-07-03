package stepDefinition;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
	// write a code to get place_id
	// execute this code only when place id is null
	StepDefinition sd = new StepDefinition();
	if (sd.place_id == null) {
	    sd.add_place_payload_with("John", "English", "America");
	    sd.user_calls_with_post_http_request("addPlaceAPI", "post");
	    sd.verify_place_Id_created_maps_to_using("John", "getPlaceAPI");
	}
    }
}
