Feature: Testing different requests on the student application


Scenario: Check if the student application can be accessed by users
	Given User sends a GET request to the list endpoint,they must get back a valid status code 200
	
Scenario: Create a new student & verify if the student is added
     When I create a new student by providing the information firstName, lastName, email, programme and courses 
      | firstName | lastName | email                                      | programme        | courses |
      | Declan    | Smith    | nnon12.ante.bibendum@risusDonecegestas.edu | Computer Science | Java    |
      | Mark      | Taylor   | nnon2.ante.bibendum@risusDonecegestas.edu  | Computer Science | Java    |
    Then I verify that the student with firstname is created
	  | firstName |
	  | Declan    |
	  | Mark      |
  
      
  