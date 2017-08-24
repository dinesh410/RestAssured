Feature: Testing different requests on the student application in diffrenet feature file


@smoke
Scenario: Check if the student application can be accessed by users
	Given User sends a GET request to the list endpoint,they must get back a valid status code 200
	
	
  