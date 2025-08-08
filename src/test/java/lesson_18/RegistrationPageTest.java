package lesson_18;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RegistrationPageTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeAll
    void setupDriver() {
        System.setProperty("webdriver.chrome.driver",
                "D:\\EugenGustovskiy\\Andersen\\AndersenWeb\\AndersenWeb\\src\\test\\resources\\chromedriver.exe");
    }

    @BeforeEach
    void startBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @AfterEach
    void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    @ParameterizedTest(name = "test user registration: {0} {1}")
    @CsvSource({
            "Mariya, Grib, 01/01/1990, mariya.gribwww@mail.ru, Password123!",
            "Anna, Dub, 02/02/1985, anna.dubww@mail.ru, Password456!"
    })
    void testValidRegistration(String firstName, String lastName, String dateOfBirth, String email, String password) {
        driver.get("https://qa-course-01.andersenlab.com/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegistrationLink();

        wait.until(ExpectedConditions.urlContains("/registration"));

        RegistrationPage reg = new RegistrationPage(driver);

        reg.enterFirstName(firstName);
        reg.enterLastName(lastName);
        reg.enterDateOfBirth(dateOfBirth);

        new Actions(driver).sendKeys(Keys.ESCAPE).perform();

        reg.enterEmail(email);
        reg.enterPassword(password);
        reg.enterConfirmPassword(password);
        reg.clickSubmit();

        wait.until(ExpectedConditions.urlToBe("https://qa-course-01.andersenlab.com/login"));

        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
        String headerText = header.getText();
        Assertions.assertTrue(headerText.contains(firstName), "Header should contain first name");
        Assertions.assertTrue(headerText.contains(lastName), "Header should contain last name");
    }

    @Test
    @DisplayName("test registration with empty fields")
    void testEmptySubmitShowsRequiredMessages() {
        driver.get("https://qa-course-01.andersenlab.com/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegistrationLink();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.clickSubmit();

        wait.until(ExpectedConditions.numberOfElementsToBe(
                By.xpath("//span[text()='Required']"), 6));

        List<WebElement> requiredMessages = driver.findElements(
                By.xpath("//span[text()='Required']"));

        Assertions.assertEquals(6, requiredMessages.size(), "Expected exactly 6 'Required' messages");
    }

    @ParameterizedTest(name = "test missing field: {5}")
    @CsvSource({
            "'', Grib, 01/01/1990, user1@mail.com, Password123!, firstName",
            "Ivan, '', 01/01/1990, user2@mail.com, Password123!, lastName",
            "Ivan, Grib, '', user3@mail.com, Password123!, dateOfBirth",
            "Ivan, Grib, 01/01/1990, '', Password123!, email",
            "Ivan, Grib, 01/01/1990, user4@mail.com, '', password",
            "Ivan, Grib, 01/01/1990, user5@mail.com, Password123!, confirmPassword"
    })
    void testSubmitDisabledWhenFieldMissing(String firstName, String lastName, String dateOfBirth,
                                            String email, String password, String missingField) {

        driver.get("https://qa-course-01.andersenlab.com/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegistrationLink();

        wait.until(ExpectedConditions.urlContains("/registration"));

        RegistrationPage reg = new RegistrationPage(driver);

        if (!firstName.isEmpty()) {
            reg.enterFirstName(firstName);
        }
        if (!lastName.isEmpty()) {
            reg.enterLastName(lastName);
        }
        if (!dateOfBirth.isEmpty()) {
            reg.enterDateOfBirth(dateOfBirth);
            new Actions(driver).sendKeys(Keys.ESCAPE).perform();
        }
        if (!email.isEmpty()) {
            reg.enterEmail(email);
        }
        if (!password.isEmpty()) {
            reg.enterPassword(password);
        }
        if (!missingField.equals("confirmPassword")) {
            reg.enterConfirmPassword(password);
        }

        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
        Assertions.assertFalse(submitButton.isEnabled(),
                "Submit button should be disabled when " + missingField + " is missing");
    }

    @ParameterizedTest(name = "test passwords do not match: {0} {1}")
    @CsvSource({
            "Mariya, Grib, 01/01/1990, mariya.grib@mail.ru, Password123!, AnotherPassword456!"
    })
    void testPasswordsDoNotMatchShowsError(
            String firstName,
            String lastName,
            String dateOfBirth,
            String email,
            String password,
            String confirmPassword
    ) {
        driver.get("https://qa-course-01.andersenlab.com/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegistrationLink();

        wait.until(ExpectedConditions.urlContains("/registration"));

        RegistrationPage reg = new RegistrationPage(driver);

        reg.enterFirstName(firstName);
        reg.enterLastName(lastName);
        reg.enterDateOfBirth(dateOfBirth);
        new Actions(driver).sendKeys(Keys.ESCAPE).perform();

        reg.enterEmail(email);
        reg.enterPassword(password);
        reg.enterConfirmPassword(confirmPassword);
        reg.clickSubmit();

        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(text(), 'Passwords must match')]")));

        Assertions.assertTrue(errorMessage.isDisplayed(),
                "Error message 'Passwords must match' should be displayed when passwords do not match");
    }
}