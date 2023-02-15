package stepDefination;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class login {
WebDriver driver;

HashMap<String, By> loginPageElements = new HashMap<>();

@Before(order=0)
public void initializingDriver(){
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.get("https://www.amazon.in/");
}

    @Before(order=1)
    public void gettingLocators() throws FileNotFoundException {


Yaml yaml = new Yaml();
Map<String, Object> object = yaml.load(new FileReader(new File(System.getProperty("user.dir")+ "/src/test/resources/locators.yml")));
Object locators = object.get("login Page");
        ArrayList al = (ArrayList) locators;
       // List<locator_Element> pojos = new ObjectMapper().convertValue(al, new TypeReference<List<locator_Element>>() { });
        List<locator_Element> pojos = new ObjectMapper().convertValue(al, new TypeReference<>() { });
        for(int i=0;i<pojos.size();i++)
        {
            By elementInternal = null;
            if(pojos.get(i).getType().equalsIgnoreCase("xpath")) {
                elementInternal = By.xpath(pojos.get(i).getValue());
            }
            else if(pojos.get(i).getType().equalsIgnoreCase("id")){
                elementInternal = By.id(pojos.get(i).getValue());
            }
            else if(pojos.get(i).getType().equalsIgnoreCase("name")){
                elementInternal = By.name(pojos.get(i).getValue());
        }
            loginPageElements.put(pojos.get(i).getName(), elementInternal);
        }
    }

    @Given("User moves to amazon retail website")
    public void user_moves_to_amazon_retail_website() {

    }
    @And("User moves to login page of amazon")
    public void user_moves_to_login_page_of_amazon() {
    driver.findElement(loginPageElements.get("Link_loginPage")).click();
    }
    @When("User gives email for login as {string}")
    public void user_gives_email_for_login_as(String string) {
        driver.findElement(loginPageElements.get("TextField_Email")).sendKeys(string);
        driver.findElement(loginPageElements.get("Button_Continue")).click();
    }
    @And("User gives password for login as {string}")
    public void user_gives_password_for_login_as(String string) {
        driver.findElement(loginPageElements.get("TextField_Password")).sendKeys(string);
        driver.findElement(loginPageElements.get("Button_SignIn")).click();
    }
    @Then("User should be verified to be logged in successfully")
    public void user_should_be_verified_to_be_logged_in_successfully() {
        Assert.assertTrue(driver.findElement(loginPageElements.get("LoggedInName")).getText().toLowerCase().contains("rahul"));
    }

    @Then("User should not be able to login with error message as {string}")
    public void user_should_not_be_able_to_login_with_error_message_as(String string) {
        String errorMessageGot = driver.findElement(loginPageElements.get("Text_ErrorMessage")).getText();
Assert.assertTrue("expected error message differs from actual error message got," +
        "expected Error message: "+string+ "," +
        "Actual Error message: "+errorMessageGot , errorMessageGot.equals(string));

    }

    @Given("User searches for dataprovider")
    public void user_searches_for_dataprovider(DataTable dataTable) {
    int rowCount = dataTable.height();
       for(int i=0;i<dataTable.height();i++)
       {
           for(int j=0;j<dataTable.width();j++)
               System.out.println("value is "+ dataTable.row(i).get(j));
       }
    }
    @When("User verifies colour of {string} is {string}")
    public void user_verifies_colour_of_is(String string, String string2) {
        System.out.println("value we got is "+ string + " and "+ string2);
    }

    @After
    public void closingDriverInstance(){
    driver.quit();
    }

}
