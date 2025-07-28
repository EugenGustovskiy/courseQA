package lesson_15;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProfilePhotoUploader {
    public static void uploadPhoto(WebDriver driver, String email, String password, String imagePath) throws InterruptedException {
        AndersenAuthorization.login(driver, email, password);

        Thread.sleep(2000);

        WebElement uploadIcon = driver.findElement(By.cssSelector("img[src*='upload_photo']"));
        uploadIcon.click();

        Thread.sleep(1000);

        WebElement fileInput = driver.findElement(By.cssSelector("input[type='file']"));
        fileInput.sendKeys(imagePath);

        Thread.sleep(3000);
    }
}