import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginAndersenDataProviderTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://qa-course-01.andersenlab.com/login");
    }

    @DataProvider(name = "userData")
    public Object[][] userDataProvider() {
        return new Object[][]{
                {"sidorov.k@gmail.com", "12345678", "Кирилл Сидоров"},
                {"mavrin.v@gmail.com", "87654321", "Вася Маврин"},
                {"petrov.g@gmail.com", "98765432", "Герман Петров"}
        };
    }

    @Test(dataProvider = "userData")
    public void testLogin(String email, String password, String expectedH1) throws InterruptedException {
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Thread.sleep(3000);

        WebElement h1Element = driver.findElement(By.tagName("h1"));
        String actualH1 = h1Element.getText();

        Assert.assertEquals(actualH1, expectedH1, "Incorrect H1 for user: " + email);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
