package lesson_15;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AndersenAuthorization {
    public static void login(WebDriver driver, String username, String password) throws InterruptedException {
        driver.get("https://learn.andersenlab.com/login/index.php");

        Thread.sleep(1000);

        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));

        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);

        // Click login button
        WebElement loginButton = driver.findElement(By.id("loginbtn"));
        loginButton.click();

        Thread.sleep(3000); // Wait for login to complete
    }
}