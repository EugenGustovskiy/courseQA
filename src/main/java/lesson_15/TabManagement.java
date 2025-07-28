package lesson_15;

import org.openqa.selenium.WebDriver;

import java.util.List;

public class TabManagement {

    public static void printAllTabsInfo(WebDriver driver, List<String> tabs) throws InterruptedException {
        for (String handle : tabs) {
            driver.switchTo().window(handle);
            Thread.sleep(1000);
            String title = driver.getTitle();
            String currentUrl = driver.getCurrentUrl();

            System.out.println("Title: " + title);
            System.out.println("URL: " + currentUrl);
        }
    }

    public static void closeTabsWithZoo(WebDriver driver, List<String> tabs) throws InterruptedException {
        for (String handle : tabs) {
            driver.switchTo().window(handle);
            Thread.sleep(1000);
            String title = driver.getTitle();

            if (title.toLowerCase().contains("zoo")) {
                driver.close();
            }
        }
    }
}