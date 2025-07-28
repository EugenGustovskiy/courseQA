package lesson_15;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationNegativeTest {

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
    public void testEmptyRegistrationShowsAllRequiredMessages() throws InterruptedException {
        driver.get("https://qa-course-01.andersenlab.com/login");
        Thread.sleep(1000);

        WebElement registrationLink = driver.findElement(By.linkText("Registration"));
        registrationLink.click();
        Thread.sleep(1000);

        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();
        Thread.sleep(2000);

        List<WebElement> requiredMessages = driver.findElements(By.xpath("//*[text()='Required']"));

        assertEquals(6, requiredMessages.size(), "Expected 6 'Required' messages on empty registration form.");
    }
}