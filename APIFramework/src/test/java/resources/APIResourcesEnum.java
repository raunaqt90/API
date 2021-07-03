package resources;

//enum is special class in java having collection of constants and methods
public enum APIResourcesEnum {
    addPlaceAPI("/maps/api/place/add/json"), 
    getPlaceAPI("/maps/api/place/get/json"),
    deletePlaceAPI("/maps/api/place/delete/json");
    
    private String resource;
    
    APIResourcesEnum(String resource){
	this.resource = resource;
    }
    
    public String getResource() {
	return resource;
    }
}
