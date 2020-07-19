package api_TestNg.tests.end_points.student;

import api_TestNg.tests.TestBase;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;


import static org.testng.Assert.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class GetMethodTests extends TestBase {


    /**
     * Verify the result paths has Id,firstname,lastname and role json object and value
     */
    @Test
    public void getAllStudents(){

        given().accept(ContentType.JSON)
                .header("Authorization", token)
                .when().get("/api/students")
                .then().assertThat().statusCode(200)
                .contentType("application/json")
                .body("id", is(notNullValue()), "firstName",
                        is(notNullValue()), "lastName", is(notNullValue()), "role", is(notNullValue()))  ;

    }


    /**
     * Verify this student via using ID path param
     *   {
     *         "id": 7122,
     *         "firstName": "Katina",
     *         "lastName": "Conn",
     *         "role": "student-team-member"
     *     }
     */
    @Test
    public void getStudentById(){

        given().accept(ContentType.JSON)
                .header("Authorization", token)
                .pathParam("id", 7122)
                .when().get("/api/students/{id}")
                .then().assertThat().statusCode(200)
                .contentType("application/json")
                .body("id", is(7122), "firstName", is("Katina"),
                        "lastName", is("Conn"), "role", is("student-team-member")) ;

    }






}
