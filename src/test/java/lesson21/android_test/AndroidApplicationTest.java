package lesson21.android_test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import lesson21.android.AppiumDriverInit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.List;

public class AndroidApplicationTest {

    AppiumDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new AppiumDriverInit().getDriver();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    // Test 1: Check number of elements on Views screen
    @Test
    public void testViewsButtonCount() {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        List<WebElement> buttons = driver.findElements(By.className("android.widget.TextView"));
        System.out.println("Number of buttons: " + buttons.size());
        assert buttons.size() == 42 : "Expected 42 buttons, but found " + buttons.size();
    }

    // Test 2: Set date and time in Dialog
    @Test
    public void testSetDateTimeInDialog() {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Date Widgets")).click();
        driver.findElement(AppiumBy.accessibilityId("1. Dialog")).click();

        // Set Date: tomorrow
        driver.findElement(By.id("io.appium.android.apis:id/pickDate")).click();
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        String day = String.valueOf(tomorrow.getDayOfMonth());
        driver.findElement(By.xpath("//android.view.View[@text='" + day + "']")).click();
        driver.findElement(By.id("android:id/button1")).click();

        // Set Time: 11:11 PM
        driver.findElement(By.id("io.appium.android.apis:id/pickTime")).click();
        driver.findElement(By.xpath("//android.widget.RadialTimePickerView$RadialPickerTouchHelper[@content-desc='11']")).click();
        driver.findElement(By.xpath("//android.widget.RadialTimePickerView$RadialPickerTouchHelper[@content-desc='11']")).click();
        driver.findElement(By.id("android:id/am_pm_spinner")).click();
        driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='PM']")).click();
        driver.findElement(By.id("android:id/button1")).click();
    }

    // Test 3: Check TextSwitcher Next button functionality
    @Test
    public void testTextSwitcherNextButton() {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("TextSwitcher")).click();

        WebElement nextButton = driver.findElement(By.id("io.appium.android.apis:id/next"));
        WebElement textField = driver.findElement(By.id("io.appium.android.apis:id/text"));

        int presses = 5;
        for (int i = 0; i < presses; i++) {
            nextButton.click();
        }

        String text = textField.getText();
        System.out.println("TextSwitcher value: " + text);
        assert text.equals(String.valueOf(presses)) : "Expected " + presses + " but found " + text;
    }
}
