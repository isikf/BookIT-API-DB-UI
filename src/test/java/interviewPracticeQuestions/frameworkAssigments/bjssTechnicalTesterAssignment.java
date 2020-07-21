package interviewPracticeQuestions.frameworkAssigments;

import interviewPracticeQuestions.utilities.BrowserUtils;
import interviewPracticeQuestions.utilities.DBUtils;
import interviewPracticeQuestions.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class bjssTechnicalTesterAssignment {

    /**
     * INSTRUCTIONS
     * Deliver a working test framework that aims towards "good practices". If you feel that there may be
     * technical debt, future improvements, or any other comments you'd like us to know about then
     * mention them in your README.
     * Please publish your work to a private GitHub repository and add the user “bjss-bristol” as a
     * collaborator or upload to Greenhouse as a zip.
     *
     * TECH
     * Java, JavaScript, C#, Ruby, or Python with any supporting libraries you need.
     *
     * WHAT WE LIKE
     * Simplicity – your work should do what it needs to do in the most effective way (readability counts!)
     * Extendibility – anyone should be able to pick up from where you've written and add more tests
     * (i.e. use page object model for UI tests)
     *
     * WHAT WE DON'T LIKE
     * Complexity - There is no need to introduce complexity for the sake of showing us your knowledge
     * of the language, see our point on Simplicity above
     * Bloat – don't keep unnecessary methods, commented out code, or stuff you don't think we need to
     * see in your work
     * If you don’t agree with what we “like” and “don’t like” please explain why in your README.
     *
    *
     */

    WebDriver driver =  Driver.get();
    @BeforeMethod
    public void setup(){

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://the-internet.herokuapp.com/");
    }
    @AfterMethod
    public void teardown() throws InterruptedException {

        Thread.sleep(2000);
        Driver.closeDriver();
    }


    /**
     *  * TEST 1:
     *      * Navigate to https://the-internet.herokuapp.com/
     *      * Click Challenging DOM
     *      * Confirm that the blue, red, and green button ids change after the red button is clicked
     */
    @Test
    public void test1(){
        Driver.get().findElement(By.xpath("//a[contains(text(),'Challenging DOM')]")).click();
        BrowserUtils.waitFor(2);
        WebElement blueButton = driver.findElement(By.xpath("//*[@class='button']"));
        WebElement redButton = driver.findElement(By.xpath("//*[@class='button alert']"));
        WebElement greenButton = driver.findElement(By.xpath("//*[@class='button success']"));

        List<String> beforeClick= getIdValue();
        redButton.click();
        List<String> afterClick= getIdValue();

        Assert.assertFalse(beforeClick.equals(afterClick));

    }

    public static List<String> getIdValue(){
        List<String> list= new ArrayList<>();
        WebElement blueButton = Driver.get().findElement(By.xpath("//*[@class='button']"));
        WebElement redButton = Driver.get().findElement(By.xpath("//*[@class='button alert']"));
        WebElement greenButton = Driver.get().findElement(By.xpath("//*[@class='button success']"));
        list.add(blueButton.getAttribute("id"));
        list.add(redButton.getAttribute("id"));
        list.add(greenButton.getAttribute("id"));
        return list;
    }


    /**
     * TEST 2:
     * Navigate to https://the-internet.herokuapp.com/
     * Click Dynamically Loaded Page Elements
     * Click Example 2: Element rendered after the fact
     * Confirm 'Hello World!' is rendered after the loading bar disappears
     */

    @Test
    public void test2(){

    }




    @Test
    public void test3(){

    }
}
