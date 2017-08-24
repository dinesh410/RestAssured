package com.ratest.cucumber.steps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ratest.cucumber.serenity.SerenitySteps;

import com.ratest.utils.TestUtils;
import com.ratests.model.StudentClass;
import com.ratests.model.StudentObject;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
//import groovy.ui.SystemOutputInterceptor;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;

public class CucumberSteps {
	
	@Steps
	SerenitySteps steps;
	
	static String email=null;
	
	@Given("^User sends a GET request to the list endpoint,they must get back a valid status code 200$")
	public void user_sends_a_GET_request_to_the_list_endpoint_they_must_get_back_a_valid_status_code() throws Throwable {
		SerenityRest.rest().given()
		.when()
		.get("/list")
		.then().statusCode(200);
		System.out.println("Test passed");
	}

	public void createStudent(String firstName,String lastName,String _email,String programme,String course){
		List<String> courses = new ArrayList<String>();
		courses.add(course);
		
		.statusCode(201);
		
	}

	@Then("^I verify that the student with (.*) is created$")
	public void i_verify_that_the_student_with_name_is_created(String firstName){
		HashMap<String, Object> resVal=  steps.getStudentInfoByFirstName(firstName);
		
		System.out.println("The values are : "+resVal);
		//assertThat(resVal,hasValue(emailId));
	}
	
	//@When("^I create a new student by providing the information firstName, lastName, email, programme and courses$")
	//public void i_create_a_new_student_by_providing_the_information_firstName_lastName_email_programme(List<StudentObject> students) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	    // E,K,V must be a scalar (String, Integer, Date, enum etc)
	    
	//		StudentObject student = students.get(0);
		
	//		steps.createStudentFromTable(student);
	//}
	
	@When("^I create a new student by providing the information firstName, lastName, email, programme and courses$")
	public void i_create_a_new_student_by_providing_the_information_firstName_lastName_email_programme(DataTable table) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	    // E,K,V must be a scalar (String, Integer, Date, enum etc)
		steps.createStudentFromDataTable(table);
		
		//List<StudentObject> asList = table.asList(StudentObject.class);

		
	}

}
