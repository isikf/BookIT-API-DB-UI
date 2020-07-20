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

    /** Sample JSON Response From API for 1 studen:
     *   {
     *         "id": 8338,
     *         "firstName": "Lesly",
     *         "lastName": "SDET",
     *         "role": "student-team-member"
     *     }
     *     *************JAMAL PLS Read First Here,**********************
     *     Dokumandaki parametreler ile gelen responsedeki data sayıları farklı !!??
     *     Postman den değişiklik(PUT) yapıyoruz/deniyoruz , ok diyor ama kontrol ettiğimizde değişikliği save yapmamıs oluyor
     *     POST hiç olmuyor 422 hatası geliyor her türklü data türü tipi ile denedik
     *     DELETE http methodu çalışıyor diğerlerine göre çalışması tuhaf...
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
//        String json = "{\n" +
//                "    \"firstName\": \"Asli\",\n" +
//                "    \"lastName\": \"Ozleblebici\",\n" +
//                "    \"email\": \"tlh43@gmail.com\",\n" +
//                "    \"password\": \"Test1234\",\n" +
//                "    \"role\": \"student-team-leader\",\n" +
//                "    \"campus-location\": \"VA\",\n" +
//                "    \"batch-number\": 16,\n" +
//                "    \"team-name\": \"BugBusters\"\n" +
//                "}";

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
                .queryParam("first-name","Talha")
                .queryParam("last-name","Ozleblebici")
                .queryParam("email","tlh43@gmail.com")
                .queryParam("password","Test1234")
                .queryParam("role","student-team-member")
                .queryParam("campus-location","VA")
                .queryParam("batch-number",16)
                .queryParam("team-name","BugBusters")

                .when().post("/api/students/student");
        //TODO 422 HATA KODU ALIYORUZ, 201 yerine!
                postResponse.then().assertThat().statusCode(201);

        String postMessage = postResponse.body().asString();
        System.out.println("postMessage = " + postMessage);
        int id = Integer.parseInt(postMessage.substring(5, 9));
        System.out.println("id = " + id);


        given().queryParam("key", token)
                .pathParam("id", id)
                .when().get("/api/student/{id}")
                .then().assertThat().body("id", equalTo(id)).log().all();



    }
}