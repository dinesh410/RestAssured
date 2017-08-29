Feature: SecondCucumber.feature


@smoke
Scenario: Check if the student application can be accessed by users
	Given User sends a GET request to the list endpoint,they must get back a valid status code 200
	
		Scenario Outline: Create a new student & verify if the student is added using scneario outline method
	    When I create a new student by providing the information firstName '<firstName>' lastName '<lastName>' email '<email>' programme '<programme>' courses '<courses>'
	    Then I verify that the student with '<email>' is created	
	    Examples: 
	      | firstName | lastName | email                           | programme        | courses |
	      | Declan    | Smith    | personoyuu@risusDonecegestas.edu | Computer Science | Java    |
	      | Mark      | Taylor   | personrterteo@risusDonecegestas.edu | Computer Science | Java    |
      
  