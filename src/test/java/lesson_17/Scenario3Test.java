package lesson_17;

import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Scenario3Test {
    WebDriver driver;

    String baseUrl = "https://qa-course-01.andersenlab.com/login";
    String userEmail = "sidorov.k@gmail.com";
    String userPassword = "12345678";
    String cancelReason = "Test";

    @BeforeAll
    void setupDriver() {
        System.setProperty("webdriver.chrome.driver",
                "D:\\EugenGustovskiy\\Andersen\\AndersenWeb\\AndersenWeb\\src\\test\\resources\\chromedriver.exe");
    }

    @BeforeEach
    void startBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

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
                By.xpath("//div[contains(text(), 'Iframes')]")));
        selectOption.click();

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(
                By.xpath("//iframe[@title='Finish your registration']")));
    }

    @AfterEach
    void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("ConfirmTest")
    void testConfirm() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement confirmButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Confirm']")));
        confirmButton.click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assertions.assertEquals("You have called alert!", alert.getText(), "Alert text mismatch after Confirm");
        alert.accept();

        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(), 'Congratulations, you have successfully enrolled in the course!')]")));
        Assertions.assertTrue(successMessage.isDisplayed(), "Success enrollment message not displayed");
    }

    @Test
    @DisplayName("GetDiscountTest")
    void testGetDiscount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Actions actions = new Actions(driver);

        WebElement getDiscountButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[contains(text(), 'Get Discount')]")));
        actions.doubleClick(getDiscountButton).perform();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assertions.assertEquals("Are you sure you want to apply the discount?", alert.getText(), "Alert text mismatch after Get Discount");
        alert.accept();

        WebElement discountMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(), 'You received a 10% discount on the second course.')]")));
        Assertions.assertTrue(discountMessage.isDisplayed(), "Discount success message not displayed");
    }

    @Test
    @DisplayName("CancelCourseTest")
    void testCancelCourse() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Actions actions = new Actions(driver);

        WebElement cancelCourseButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(), 'Cancel course')]")));
        actions.contextClick(cancelCourseButton).perform();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        String expectedPromptText = "Here you may describe a reason why you are cancelling your registration (or leave this field empty).";
        Assertions.assertTrue(alert.getText().contains(expectedPromptText), "Alert text mismatch on cancel course");

        alert.sendKeys(cancelReason);
        alert.accept();

        WebElement cancelMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(), 'Your course application has been cancelled.')]")));
        Assertions.assertTrue(cancelMessage.isDisplayed(), "Course cancellation message not displayed");

        String cancelText = cancelMessage.getText();
        Assertions.assertTrue(cancelText.contains("Reason: " + cancelReason),
                "Cancellation message does not contain the correct Reason. Actual message: " + cancelText);
    }
}