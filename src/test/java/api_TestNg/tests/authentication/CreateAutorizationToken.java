package api_TestNg.tests.authentication;

import static io.restassured.RestAssured.*;

import api_TestNg.tests.TestBase;
import api_TestNg.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreateAutorizationToken extends TestBase {



    @Test
    public void getAutorizatioToken () {

        Response response = given().queryParam("email", ConfigurationReader.get("teacher_email"))
                .queryParam("password", ConfigurationReader.get("teacher_password"))
                .when().get("/sign");

        response.then().assertThat().statusCode(200);
        response.prettyPrint();
        String accessToken = response.path("accessToken");
        //System.out.println(accessToken);
    }

}
