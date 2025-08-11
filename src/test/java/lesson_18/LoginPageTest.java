package lesson_18;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginPageTest {
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
        driver.get("https://qa-course-01.andersenlab.com/login");
    }

    @AfterEach
    void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Successful login redirects to dashboard")
    void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);

        String validEmail = "mariya.gribwww@mail.ru";
        String validPassword = "Password123!";

        loginPage.enterEmail(validEmail);
        loginPage.enterPassword(validPassword);
        loginPage.clickSignIn();

        By signOutButtonLocator = By.xpath("//div[contains(text(),'Sign Out')]");

        WebElement signOutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(signOutButtonLocator));

        Assertions.assertTrue(signOutButton.isDisplayed(), "Sign Out button should be visible after successful login");
    }
}

