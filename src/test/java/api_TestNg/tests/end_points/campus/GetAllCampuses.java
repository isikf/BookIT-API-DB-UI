package api_TestNg.tests.end_points.campus;

import api_TestNg.tests.TestBase;
import api_TestNg.utilities.ApiBookitUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;



public class GetAllCampuses extends TestBase {

    //TODO convert to data provider to test all user Types
    String token = ApiBookitUtils.generateAutorizationToken("teacher");

    @Test
    public void get_all_Campuses(){
        System.out.println(token);
        Response response = given().header("Authorization", token)
                .when().get("/api/campuses");

        response.then().assertThat().statusCode(200);
      //  response.prettyPrint();

    }
}
