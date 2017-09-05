Feature: SecondCucumber.feature

#@smoke
#Scenario: Check if the student application can be accessed by users
#	Given User sends a GET request to the list endpoint,they must get back a valid status code 200
#	
#		Scenario Outline: Create a new student & verify if the student is added using scneario outline method
#	    When I create a new student by providing the information firstName '<firstName>' lastName '<lastName>' email '<email>' programme '<programme>' courses '<courses>'
#	    Then I verify that the student with '<email>' is created	
#	    Examples: 
#	      | firstName | lastName | email                               | programme        | courses |
#	      | Declan    | Smith    | personoyuu@risusDonecegestas.edu    | Computer Science | Java    |
#	      | Mark      | Taylor   | personrterteo@risusDonecegestas.edu | Computer Science | Java    |      


Scenario: Update an existing student & verify if the student is updated 
	When I update an existing student by providing the information firstName, lastName, email, programme and courses 
		| firstName  | lastName | email                                        | programme        | courses |
		| DeclanEdit | Smith    | nnon12.ante.bibendum@risusDonecegestas.edu   | Computer Science | Java    |		
	Then I verify that the student with email is updated 
		| email |
		| nnon12.ante.bibendum@risusDonecegestas.edu  |		
		
@wip
Scenario: Delete an existing student & verify if the student is deleted 
	When I delete an existing student by providing the information firstName, lastName, email, programme and courses 
		| firstName  | lastName | email                                        | programme        | courses |
		| DeclanEdit | Smith    | nnon12.ante.bibendum@risusDonecegestas.edu   | Computer Science | Java    |		
	Then I verify that the student with email is deleted 
		| email |
		| nnon12.ante.bibendum@risusDonecegestas.edu  |			