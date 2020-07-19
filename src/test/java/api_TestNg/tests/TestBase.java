package api_TestNg.tests;

import api_TestNg.utilities.ApiBookitUtils;
import api_TestNg.utilities.ConfigurationReader;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.baseURI;

public class TestBase {


    @BeforeClass
    public void setUp(){
        baseURI = ConfigurationReader.get("qa1_api_uri");
    }

    public static String token = ApiBookitUtils.generateAutorizationToken("teacher");
}
