package api_TestNg.tests.end_points.campus;

import api_TestNg.tests.TestBase;
import api_TestNg.utilities.ApiBookitUtils;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetAllCampuses extends TestBase {

    //TODO convert to data provider to test all user Types
  //  String token = ApiBookitUtils.generateAutorizationToken("teacher");


    //positive test
    @Test
    public void get_all_Campuses(){
        //System.out.println(token);
        Response response = given().header("Authorization", token)
                .when().get("/api/campuses");

        response.then().assertThat().statusCode(200);
      //  response.prettyPrint();

    }


    // negative / invalid or empty token test
    // verify message that "empty or invalid sign.",
    // Verify 422 status code
    // verify "422 Unprocessable Entity" status line
    //verfy "text/html;charset=utf-8" content-type
    @Test
    public void invalid_empty_token_test(){

        Response responseEmpty = given().header("Authorization", "")
                .when().get("/api/campuses");

        responseEmpty.then().assertThat().statusCode(422)
                .and().contentType("text/html;charset=utf-8")
                .and().statusLine("HTTP/1.1 422 Unprocessable Entity")
                .body(Matchers.equalTo("empty or invalid sign."));
    }



    // verify that locations are "VA", "IL", "break time"
    // verify that "VA" locations cluster name is contains : "light-side",
    @Test
    public void test2(){
        given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .when().get("/api/campuses")
                .then().assertThat().statusCode(200).contentType("application/json")
                .and().body("location",hasItems("VA", "IL", "break time"))
                .body("findAll{it.location==\"VA\"}clusters.name[0]",hasItem("light-side"));

    }


    // verify that "IL" locations cluster name is : "il",
    @Test
    public void test3() {
        given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .when().get("/api/campuses")
                .then().assertThat().statusCode(200).contentType("application/json")
                .and().body("findAll{it.location == \"IL\"}clusters[0].name[0]", equalTo("il"));

    }


    // verify that VA locations cluster rooms capacity is between 4 and 6
    @Test
    public void test4() {
        given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .when().get("/api/campuses")
                .then().assertThat().statusCode(200).contentType("application/json")
                .and().body("findAll{it.location == \"VA\"}clusters[0].rooms[0].capacity[0]",Matchers.greaterThan(3),
                "findAll{it.location == \"VA\"}clusters[0].rooms[0].capacity[0]", Matchers.lessThanOrEqualTo(7),
                "findAll{it.location == \"VA\"}clusters.rooms[0].capacity[0]",hasItems(4,6));
    }



    // verify id number 150 campus's clusters array is empty;
    @Test
    public void test5() {
        given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .when().get("/api/campuses")
                .then().assertThat().statusCode(200).contentType("application/json")
                .and().body("findAll{it.id == 150}clusters[0]", is(empty()),
                "findAll{it.id == 150}clusters[0]",hasSize(0));
    }


    /** When "location": "IL", "name": "il"
     *  For "id": 217, verify them;
     *   "name": "facebook",
     *  "description": "move fast and break things",
     *  "capacity": 6,
     *  "withTV": true,
     * "withWhiteBoard": true
     * via POJO
     */
    @Test
    public void test6() {
        Response response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .when().get("/api/campuses");


        Gson gson = new Gson();
        System.out.println("json = " + response.path("findAll{it.location == \"IL\"}clusters[0].rooms[0]"));

        List<Map<String,Object>>listJson = response.path("findAll{it.location == \"IL\"}clusters[0].rooms[0]");
        String json = gson.toJson(listJson);
        System.out.println("path = " + listJson);

    //    List<Rooms> rooms = response.path("findAll{it.location == \"IL\"}clusters[0].rooms[0]");

        Rooms[] rooms =  gson.fromJson(json,Rooms[].class);
        //System.out.println("rooms = " + rooms.toString());

        for (Rooms room : rooms) {
            //System.out.println("room = " + room.toString());
            if(room.getId() == 217){
                assertEquals(room.getName(),"facebook");
                assertEquals(room.getDescription(),"move fast and break things");
                assertEquals((int)room.getCapacity(),6);
                assertEquals((boolean) room.getWithTV(),true);
                assertTrue((room.getWithWhiteBoard().equals(true)));
            }

        }


    }


    }
