import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class TestElementPresence {
    private WebDriver driver;
    private final String URL = "http://rozetka.com.ua";
    private final String TARGET_TEXT = "Доставка по всей Украине";
    private final String PASSWORD = "12345678";

    @BeforeTest
    public void setup(){
        // Disable pop-ups
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");

        driver = new ChromeDriver(options);
        driver.get(URL);
        driver.manage().window().maximize();
        // implicit waits
        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test(priority = 1, description = "get page element and check, match, extract ...")
    public void findElements(){
        String mainPageSource = driver.getPageSource();

        // 1. check the certain text on the page
        Assert.assertTrue(mainPageSource.contains(TARGET_TEXT));

        // 2. check if expected result is equal to actual one
        Assert.assertEquals(driver.findElement(By.xpath("//span[@class='menu-toggler__text']")).getText(), "Каталог товаров");

        // 3. find icon position

        Assert.assertEquals(driver.findElements(By.xpath("//span[@class='menu-toggler__text']")).size(), 17);


    }

    @AfterTest
    public void teardown(){ driver.quit(); }
}
