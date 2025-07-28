package lesson_15;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationPositiveTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "D:\\EugenGustovskiy\\Andersen\\AndersenWeb\\AndersenWeb\\src\\test\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testSuccessfulRegistration() throws InterruptedException {
        driver.get("https://qa-course-01.andersenlab.com/login");
        Thread.sleep(1000);


        WebElement registrationLink = driver.findElement(By.linkText("Registration"));
        registrationLink.click();
        Thread.sleep(1000);

        driver.findElement(By.cssSelector("input[placeholder='First Name']")).sendKeys("Stepan");
        driver.findElement(By.cssSelector("input[placeholder='Last Name']")).sendKeys("Pupkin");
        driver.findElement(By.cssSelector("input[placeholder='Date of birth']")).sendKeys("01/01/1990");

        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ESCAPE).perform();

        driver.findElement(By.cssSelector("input[placeholder='Enter email']")).sendKeys("stepan_pupkin9@example.com");
        driver.findElement(By.cssSelector("input[placeholder='Enter password']")).sendKeys("Password123!");
        driver.findElement(By.cssSelector("input[placeholder='Confirm Password']")).sendKeys("Password123!");

        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();
        Thread.sleep(3000);

        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/login"), "Expected to be redirected to login page after successful registration.");
    }
}