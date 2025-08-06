package lesson_17;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Scenario2Test {
    WebDriver driver;

    String baseUrl = "https://qa-course-01.andersenlab.com/login";
    String userEmail = "sidorov.k@gmail.com";
    String userPassword = "12345678";

    @BeforeAll
    void setupDriver() {
        System.setProperty("webdriver.chrome.driver",
                "D:\\EugenGustovskiy\\Andersen\\AndersenWeb\\AndersenWeb\\src\\test\\resources\\chromedriver.exe");
    }

    @BeforeEach
    void startBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void dragAndDropTest() {
        driver.get(baseUrl);

        driver.findElement(By.name("email")).sendKeys(userEmail);
        driver.findElement(By.name("password")).sendKeys(userPassword);
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        Actions actions = new Actions(driver);
        WebElement practiceMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(text(), 'AQA Practice')]")));

        actions.moveToElement(practiceMenu).perform();

        WebElement selectOption = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(text(), 'Drag & Drop')]")
        ));
        selectOption.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("manual1")));

        WebElement manual1 = driver.findElement(By.id("manual1"));
        WebElement targetManual1 = driver.findElement(By.id("target-manual1"));
        actions.clickAndHold(manual1)
                .moveToElement(targetManual1)
                .pause(Duration.ofMillis(300))
                .release()
                .perform();

        WebElement manual2 = driver.findElement(By.id("manual2"));
        WebElement targetManual2 = driver.findElement(By.id("target-manual2"));
        actions.clickAndHold(manual2)
                .moveToElement(targetManual2)
                .pause(Duration.ofMillis(300))
                .release()
                .perform();

        WebElement auto1 = driver.findElement(By.id("auto1"));
        WebElement targetAuto1 = driver.findElement(By.id("target-auto1"));
        actions.clickAndHold(auto1)
                .moveToElement(targetAuto1)
                .pause(Duration.ofMillis(300))
                .release()
                .perform();

        WebElement auto2 = driver.findElement(By.id("auto2"));
        WebElement targetAuto2 = driver.findElement(By.id("target-auto2"));
        actions.clickAndHold(auto2)
                .moveToElement(targetAuto2)
                .pause(Duration.ofMillis(300))
                .release()
                .perform();

        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(text(), 'Congratulations!')]")
        ));
        Assertions.assertTrue(message.isDisplayed(), "Expected message is not displayed.");
    }
}