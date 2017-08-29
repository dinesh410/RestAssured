package com.ratest.cucumber.serenity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ratest.utils.ReusableSpecifications;
import com.ratests.model.StudentClass;
import com.ratests.model.StudentObject;

import cucumber.api.DataTable;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class SerenitySteps {

	@Step("Creating student with firstName:{0}, lastName:{1}, email:{2},programme{3} ,courses:{4}")
	public ValidatableResponse createStudent(String firstName,String lastName, String email, String programme,
			List<String> courses){
		
		StudentClass student = new StudentClass();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setProgramme(programme);
		student.setCourses(courses);
	
	return	SerenityRest.rest().given()
		.spec(ReusableSpecifications.getGenericRequestSpec())
		.when()
		.body(student)
		.post()
		.then();
				
	}
	
	@Step("Updating student with firstName:{0}, lastName:{1}, email:{2},programme{3} ,courses:{4}")
	public ValidatableResponse updateStudent(String firstName,String lastName, String email, String programme,
			List<String> courses , int studentId){
		
		StudentClass student = new StudentClass();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setProgramme(programme);
		student.setCourses(courses);
	
		System.out.println("Student id is: " + studentId);
	return	SerenityRest.rest().given()
		.spec(ReusableSpecifications.getGenericRequestSpec())
		.when()
		.body(student)
		.put("/" + studentId)
		.then();
				
	}
	
	@Step("Getting the student information with email: {0}")
	public HashMap<String,Object> getStudentInfoByEmail(String email){
		String p1 = "findAll{it.email=='";
		String p2 = "'}.get(0)";
		System.out.println("email : " + p1+email+p2);
		HashMap<String,Object> value = SerenityRest.rest().given()
				.when()
				.get("/list")
				.then()
				.log()
				.all()
				.statusCode(200)
				.extract()
				.path(p1+email+p2);
				
		return	value;
	}
	
	@Step("Updating student information with studnetID: {0} firstName:{1}, lastName:{2}, email:{3},programme: {4} ,courses:{5}")
	public  ValidatableResponse updateStudent(int studentid, String firstName,
			String lastName, String email, String programme,
			List<String> courses) {

		StudentClass student = new StudentClass();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setProgramme(programme);
		student.setCourses(courses);

		return SerenityRest.rest().given()
				.spec(ReusableSpecifications.getGenericRequestSpec()).log().all()
				.when().body(student).put("/" + studentid).then();
	}
	
	@Step("Deleting student information with ID: {0}")
	public  void deleteStudent(int studentId) {
		SerenityRest.rest().given().when().delete("/" + studentId);
	}
	

	@Step("Getting information of the student with ID: {0}")
	public ValidatableResponse getStudentById(int studentId){
		return 
		SerenityRest
		.rest()
		.given()
		.when()
		.get("/" + studentId).then();
		
	}
	
	@Step("Creating student with firstName, lastName, email,programme ,courses")
	public ValidatableResponse createStudentFromTable(StudentObject student){
		
			
	return	SerenityRest.rest().given()
		.spec(ReusableSpecifications.getGenericRequestSpec())
		.when()
		.body(student)
		.post()
		.then();
				
	}
	
	@Step("Creating student with firstName, lastName, email,programme ,courses")
	public void createStudentFromDataTable(DataTable table){
		
		List<StudentObject> asList = table.asList(StudentObject.class);
		for (StudentObject list : asList){	
		 get_response(list);
			}
				
	}

	private ValidatableResponse get_response(StudentObject data) {
		return	SerenityRest.rest().given()
			.spec(ReusableSpecifications.getGenericRequestSpec())
			.when()
			.body(data)
			.post()
			.then();
	}
}
