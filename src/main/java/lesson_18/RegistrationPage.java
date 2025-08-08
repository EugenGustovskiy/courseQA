package lesson_18;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {
    private WebDriver driver;

    private final By firstNameField = By.xpath("//input[@placeholder='First Name']");
    private final By lastNameField = By.xpath("//input[@placeholder='Last Name']");
    private final By dateOfBirthField = By.xpath("//input[@placeholder='Date of birth']");
    private final By emailField = By.xpath("//input[@placeholder='Enter email']");
    private final By passwordField = By.xpath("//input[@placeholder='Enter password']");
    private final By confirmPasswordField = By.xpath("//input[@placeholder='Confirm Password']");

    private final By submitButton = By.xpath("//button[@type='submit']");
    private final By signInLink = By.linkText("Sign in");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterFirstName(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    public void enterDateOfBirth(String dob) {
        driver.findElement(dateOfBirthField).sendKeys(dob);
    }

    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        driver.findElement(confirmPasswordField).sendKeys(confirmPassword);
    }

    public void clickSubmit() {
        driver.findElement(submitButton).click();
    }

    public void clickSignInLink() {
        driver.findElement(signInLink).click();
    }
}
