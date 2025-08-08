package lesson_18;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    private final By emailField = By.xpath("//input[@placeholder='Enter email']");
    private final By passwordField = By.xpath("//input[@placeholder='Enter password']");
    private final By signInButton = By.xpath("//button[@type='submit']");
    private final By registrationLink = By.linkText("Registration");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickSignIn() {
        driver.findElement(signInButton).click();
    }

    public void clickRegistrationLink() {
        driver.findElement(registrationLink).click();
    }
}