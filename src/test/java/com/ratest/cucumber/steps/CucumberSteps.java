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
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.core.Serenity;

import org.junit.Assert;
import org.junit.Assert.*;
import org.hamcrest.Matchers.*;
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

	@When("^I create a new student by providing the information firstName '(.*)' lastName '(.*)' email '(.*)' programme '(.*)' courses '(.*)'$")
	public void createStudent(String firstName,String lastName,String _email,String programme,String course){
		List<String> courses = new ArrayList<String>();
		courses.add(course);
		email = TestUtils.getRandomValue()+_email;
		
		System.out.println("The email is "+_email);
		steps.createStudent(firstName, lastName, email, programme, courses)
		.statusCode(201);
		
	}


	@Then("^I verify that the student with (.*) is created$")
	public void i_verify_that_the_student_with_name_is_created(String email){
		HashMap<String, Object> resVal=  steps.getStudentInfoByEmail(email);
		
		System.out.println("The values are : "+resVal);
		//assertThat(resVal,hasValue(emailId));
	}	
	
	
	@When("^I create a new student by providing the below information in datatable$")
	public void i_create_a_new_student_by_providing_the_below_information_in_datatable(DataTable table) throws Throwable {
	 
        steps.createStudentFromDataTable(table);
		
		//List<StudentObject> asList = table.asList(StudentObject.class);

		
	}
	
	@Given("^I have a student with the below details$")
	public void i_have_a_student_with_the_below_details(DataTable table) throws Throwable {	 
		
		List<StudentObject> asList = table.asList(StudentObject.class);
		ValidatableResponse resp = steps.createSingleStudentAndGetStudentId(table);
		HashMap<String,Object> studValue = steps.getStudentInfoByEmail(asList.get(0).email);
		Serenity.setSessionVariable("studentId").to((Integer)studValue.get("id"));
	}
	
	@Then("^I verify that the student is created with below email in datatable$")
	public void i_verify_that_the_student_is_created_with_below_email_in_datatable(DataTable table){
		
		List<StudentObject> asList = table.asList(StudentObject.class);
		
		HashMap<String, Object> resVal=  steps.getStudentInfoByEmail(asList.get(0).email);
		
		System.out.println("The values are : "+resVal);
		
		//assertThat(resVal,hasValue(emailId));		
	}
	
	
	
	@When("^I update an existing student by providing the information firstName, lastName, email, programme and courses$")
	public void i_update_an_existing_student_by_providing_the_information_firstName_lastName_email_programme_and_courses(DataTable table) throws Throwable {
	  
		List<StudentObject> asList = table.asList(StudentObject.class);		
		Serenity.setSessionVariable("studList").to(asList);
		HashMap<String, Object> resVal=  steps.getStudentInfoByEmail(asList.get(0).email);
		int studentId = (int) resVal.get("id");
		System.out.println("Student id is: " + studentId);		
		steps.updateStudent(asList.get(0).firstName, asList.get(0).lastName,  asList.get(0).email, asList.get(0).programme, asList.get(0).courses ,studentId);
		
	}

	@SuppressWarnings("unchecked")
	@Then("^I verify that the student with email is updated$")
	public void i_verify_that_the_student_with_email_is_updated(DataTable table) throws Throwable {
		
        List<StudentObject> asList = table.asList(StudentObject.class);		
		HashMap<String, Object> resVal=  steps.getStudentInfoByEmail(asList.get(0).email);			
		List<StudentObject> studList = (List<StudentObject>) Serenity.sessionVariableCalled("studList");
		Assert.assertTrue("Firstname is not updated", resVal.get("firstName").equals(studList.get(0).firstName));
	   
	}
	
	@When("^I delete the student$")	
	public void i_delete_the_student() throws Throwable {	   
		
		int studentId = (Integer)Serenity.sessionVariableCalled("studentId");
		System.out.println("Student id is: " + studentId);
		steps.deleteStudent(studentId);
		
	}

	@Then("^I verify that the student with email is deleted$")
	public void i_verify_that_the_student_with_email_is_deleted() throws Throwable {
		
		ValidatableResponse response= steps.getStudentById((Integer)Serenity.sessionVariableCalled("studentId"));
		response.assertThat().statusCode(404);		
	   
	}



}
