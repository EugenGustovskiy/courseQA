package lesson_15;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver",
                "D:\\EugenGustovskiy\\Andersen\\AndersenWeb\\AndersenWeb\\src\\test\\resources\\chromedriver.exe");

        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        List<String> urls = List.of(
                "http://www.automationpractice.pl/index.php",
                "https://zoo.waw.pl/",
                "https://www.w3schools.com/",
                "https://www.clickspeedtester.com/click-counter/",
                "https://andersenlab.com/"
        );

        driver.get(urls.get(0));
        Thread.sleep(1000);

        for (int i = 1; i < urls.size(); i++) {
            driver.executeScript("window.open('" + urls.get(i) + "','_blank');");
            Thread.sleep(1000);
        }

        List<String> tabs = new ArrayList<>(driver.getWindowHandles());

        TabManagement.printAllTabsInfo(driver, tabs);
        TabManagement.closeTabsWithZoo(driver, tabs);

        driver.switchTo().window(tabs.get(0));

        WebElement element1 = driver.findElement(By.cssSelector(".logo.img-responsive"));
        WebElement element2 = driver.findElements(By.cssSelector(".item-img")).get(1);

        ElementComparator.compareElements(element1, element2);

        String email = "testemailivanov@gmail.com";
        String password = "6548TEST321!";
        AndersenAuthorization.login(driver, email, password);
        WebElement signOutButton = driver.findElement(By.xpath("//div[contains(text(),'Sign Out')]/parent::div"));
        signOutButton.click();

        String imagePath = "D:\\EugenGustovskiy\\Andersen\\AndersenWeb\\AndersenWeb\\src\\test\\resources\\dog.jpg";

        ProfilePhotoUploader.uploadPhoto(driver, email, password, imagePath);

        driver.quit();
    }
}