Feature: Validating Place API's 

@AddPlace
Scenario Outline: Verify if Place is being Successully added using AddPlaceAPI 
	Given Add Place Payload with "<name>" "<language>" "<address>" 
	When user calls "addPlaceAPI" with "post" http request 
	Then the API call got success with status code 200 
	And "status" in response body is "OK" 
	And "scope" in response body is "APP" 
	And verify place_Id created maps to "<name>" using "getPlaceAPI" 
	
	Examples: 
		|name |language |address |
		|John |English  |World Trade Center|
	#	|Dani |French   |Sea Cross Center|

@DeletePlace	
Scenario: Verify if delete place functionality is working 

	Given Delete Place Payload
	When user calls "deletePlaceAPI" with "post" http request 
	Then the API call got success with status code 200 
	And "status" in response body is "OK" 