package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lesson_20.LoginPage;
import lesson_20.RegistrationPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegistrationSteps {

    private WebDriver driver;
    private WebDriverWait wait;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        System.setProperty("webdriver.chrome.driver",
                "D:\\EugenGustovskiy\\Andersen\\AndersenWeb\\AndersenWeb\\src\\test\\resources\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://qa-course-01.andersenlab.com/login");

        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @When("the user clicks the {string} button")
    public void the_user_clicks_the_button(String buttonName) {
        if (buttonName.equalsIgnoreCase("Registration")) {
            loginPage.clickRegistrationLink();
        } else if (buttonName.equalsIgnoreCase("Submit")) {
            registrationPage.clickSubmit();
        }
    }

    @When("enters {string} in the First Name field")
    public void enters_first_name(String firstName) {
        registrationPage.enterFirstName(firstName);
    }

    @When("enters {string} in the Last Name field")
    public void enters_last_name(String lastName) {
        registrationPage.enterLastName(lastName);
    }

    @When("enters {string} in the Date of Birth field")
    public void enters_date_of_birth(String dob) {
        registrationPage.enterDateOfBirth(dob);
    }

    @When("enters {string} in the Email field")
    public void enters_email(String email) {
        registrationPage.enterEmail(email);
    }

    @When("enters {string} in the Password field")
    public void enters_password(String password) {
        registrationPage.enterPassword(password);
    }

    @When("enters {string} in the Confirm Password field")
    public void enters_confirm_password(String confirmPassword) {
        registrationPage.enterConfirmPassword(confirmPassword);
    }

    @When("clicks the {string} button")
    public void clicks_button(String buttonName) {
        if (buttonName.equalsIgnoreCase("Submit")) {
            registrationPage.clickSubmit();
        }
    }

    @Then("the user should be successfully registered and redirected to the login page")
    public void user_registered_and_redirected() {
        wait.until(ExpectedConditions.urlContains("/login"));
        assertTrue(driver.getCurrentUrl().contains("/login"));
    }

    @When("the user submits the registration form without filling any fields")
    public void submit_registration_form_empty() {
        registrationPage.clickSubmit();
    }

    @Then("all required field messages are displayed")
    public void all_required_messages_displayed() {
        wait.until(ExpectedConditions.numberOfElementsToBe(
                By.xpath("//span[text()='Required']"), 6));

        List<WebElement> requiredMessages = driver.findElements(
                By.xpath("//span[text()='Required']"));

        assertEquals("Expected exactly 6 'Required' messages", 6, requiredMessages.size());
        driver.quit();
    }

    @Then("the Submit button should be disabled")
    public void submit_button_should_be_disabled() {
        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
        assertTrue("Submit button is clickable, but it should be disabled", !submitButton.isEnabled());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}