package api_TestNg.tests.end_points.student;

import api_TestNg.tests.TestBase;
import io.cucumber.java.sl.In;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PostMethodTests extends TestBase {

    /**
     * Add a Student
     * Query Parameters
     * Parameter	    Demand	    Description
     * first-name	    required	first name of the student
     * last-name	    required	last name of the student
     * email	        required	email of the student, will be used for an authentication
     * password	        required	password of the account, will be used for an authentication
     * role	            required	role of the student, [student-team-leader, student-team-member]
     * campus-location	required	name of the campus which student will be added to
     * batch-number	    required	number of the batch which student will be added to
     * team-name	    required	name of the team which student will be added to
     *
     * user {student-id} has been added to the database
     */
    @Test
    public void addStudent() {

        // 1. WAY
        String json = "{"+
                 "  first-name: \"Talha2\",\n" +
                "    last-name: \"Ozleblebici\",\n" +
                "    email: \"tlh43@hotmail.com\",\n" +
                "    password: \"Test1234\",\n" +
                "    role: \"student-team-leader\"\n" +
                "    campus-location: \"Newcastle\"\n" +
                "    batch-number: 13\n" +
                "    team-name: \"GroupStudy\"\n" +
                "}";
        Map<String,Object> queryParams = new HashMap<>();
        queryParams.put("first-name",",tlh");
        queryParams.put("last-name",",ozl");
        queryParams.put("email",",tlh@comcom");
        queryParams.put("password",",tlhtlh");
        queryParams.put("role",",student-team-leader");
        queryParams.put("campus-location","Newcastle");
        queryParams.put("batch-number",13);
        queryParams.put("team-name",",GroupStudy");




        Response postResponse = given().contentType(ContentType.JSON)
               // .queryParam("key", token)
                .header("Authorization", token)
                //.body(json)
                //.queryParams(queryParams)
                .queryParam("first-name","tlh")
                .queryParam("last-name"," ")
                .queryParam(" "," ")
                .queryParam(" "," ")
                .when().post("/api/students/student");
        //TODO 422 HATA KODU ALIYORUZ BURDAN DEVAM KE
                postResponse.then().assertThat().statusCode(201);

        String postMessage = postResponse.body().asString();
        System.out.println("postMessage = " + postMessage);
        int id = Integer.parseInt(postMessage.substring(5, 9));
        System.out.println("id = " + id);

//        given().queryParam("key", token)
//                .pathParam("id", id)
//                .when().get("/api/student/{id}")
//                .then().assertThat().body("id", equalTo(id)).log().all();



    }
}