package lesson_15;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AndersenAuthorization {
    public static void login(WebDriver driver, String email, String password) throws InterruptedException {
        driver.get("https://qa-course-01.andersenlab.com/login");

        Thread.sleep(1000);

        WebElement emailInput = driver.findElement(By.name("email"));
        WebElement passwordInput = driver.findElement(By.name("password"));

        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);

        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        loginButton.click();

        Thread.sleep(3000);
    }
}