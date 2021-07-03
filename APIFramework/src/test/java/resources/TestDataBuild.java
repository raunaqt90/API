package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
    public AddPlace addPlacePayload(String name, String language, String address) {
	// Serialization
	AddPlace p = new AddPlace();
	p.setAccuracy(50);
	p.setAddress(address);
	p.setLanguage(language);
	p.setPhone_number("(+91) 983 893 3937");
	p.setWebsite("http://google.com");
	p.setName(name);

	// LIST type JSON
	List<String> myList = new ArrayList<String>();
	myList.add("shoe park");
	myList.add("shop");
	// added list of string below
	p.setTypes(myList);

	// Nested JSON
	Location l = new Location();
	l.setLat(-38.383494);
	l.setLng(33.427362);
	// added nested json object to main class -> ADDPLACE class
	p.setLocation(l);

	return p;
    }
    
    public String deletePlacePayload(String place_id) {
	return "{\r\n\"place_id\": \""+place_id+"\"\r\n}";
    }
}
