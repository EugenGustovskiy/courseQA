package lesson_17;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Scenario1Test {
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
    void selectTest() {
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
                By.xpath("//div[contains(text(), 'Select')]")
        ));
        selectOption.click();

        Select countrySelect = new Select(driver.findElement(By.cssSelector("[title='Select country']")));
        countrySelect.selectByVisibleText("USA");

        Select languageSelect = new Select(driver.findElement(By.cssSelector("[title='Select language']")));
        languageSelect.selectByVisibleText("English");

        Select typeSelect = new Select(driver.findElement(By.cssSelector("[title='Select type']")));
        typeSelect.selectByVisibleText("Testing");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        LocalDate today = LocalDate.now();
        LocalDate nextMonday = today.with(DayOfWeek.MONDAY);
        if (!nextMonday.isAfter(today)) {
            nextMonday = nextMonday.plusWeeks(1);
        }

        String startDateStr = nextMonday.format(formatter);
        WebElement startDateInput = driver.findElement(By.cssSelector("[title='Start date']"));
        startDateInput.clear();
        startDateInput.sendKeys(startDateStr);

        LocalDate lastDate = nextMonday.plusWeeks(2);
        String lastDateStr = lastDate.format(formatter);
        WebElement lastDateInput = driver.findElement(By.cssSelector("[title='End date']"));
        lastDateInput.clear();
        lastDateInput.sendKeys(lastDateStr);

        WebElement coursesSelectElement = driver.findElement(By.id("MultipleSelect"));

        WebElement optionPython = coursesSelectElement.findElement(By.xpath(".//option[text()='AQA Python']"));
        WebElement optionJava = coursesSelectElement.findElement(By.xpath(".//option[text()='AQA Java']"));

        Actions action = new Actions(driver);
        action.click(optionPython)
                .keyDown(Keys.SHIFT)
                .click(optionJava)
                .keyUp(Keys.SHIFT)
                .build()
                .perform();

        WebElement searchButton = driver.findElement(By.name("SelectPageSearchButton"));
        searchButton.click();

        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[contains(text(),'Unfortunately, we did not find any courses matching your chosen criteria.')]")
        ));
        Assertions.assertTrue(message.isDisplayed(), "Expected message is not displayed.");
    }
}