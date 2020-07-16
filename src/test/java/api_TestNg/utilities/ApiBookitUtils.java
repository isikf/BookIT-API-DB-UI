package api_TestNg.utilities;

import io.restassured.response.Response;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class ApiBookitUtils {


    public static String generateAutorizationToken (String userType) {

        Response response = given().queryParam("email", ConfigurationReader.get(userType+"_email"))
                .queryParam("password", ConfigurationReader.get(userType+"_password"))
                .when().get(ConfigurationReader.get("qa1_api_uri")+"/sign");

        int statusCode = response.statusCode();

        String token = "Bearer " + response.body().path("accessToken");
        if (statusCode==200){
          //  return (String) response.body().path("accessToken");  //"Bearer " +
            return token;
        }
        Assert.fail("CHECK API / Not have successful connection");
        return "";
    }
}
