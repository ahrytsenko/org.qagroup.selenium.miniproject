import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestLoginAndLogoutHomework01 {
//    Админ панель
//    demo.leadget.ru/adm
//    Логин: admin@leadget.ru
//    Пароль: 12345678

    private WebDriver driver;
    private final String URL = "http://demo.leadget.ru/adm";
    private final String USER_NAME = "admin@leadget.ru";
    private final String PASSWORD = "12345678";

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();
    }

    @Test (priority = 1, description = "Login and Logout")
    public void testLoginAndLogout() throws InterruptedException {

        // Check is this a Login Page
        // URL contains "controller=AdminLogin" and page contains a Login Form
        Assert.assertTrue(driver.getCurrentUrl().contains("controller=AdminLogin"));
        Assert.assertTrue(driver.getPageSource().contains("login_form"));

        // Define control elements for Login
        WebElement inputEmail = driver.findElement(By.id("email"));
        WebElement inputPasswd = driver.findElement(By.id("passwd"));
        WebElement submitButton = driver.findElement(By.id("submit_login"));

        // Emulate login
        inputEmail.sendKeys(USER_NAME);
        inputPasswd.sendKeys(PASSWORD);
        submitButton.click();

        // Wait until logged in
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlContains("/sell/catalog/products"));

        // Check if logged in successfully
        Assert.assertTrue(driver.getCurrentUrl().contains("/sell/catalog/products"));
        Assert.assertFalse(driver.findElements(By.xpath("//div[@class='title-row']")).isEmpty());

        // Do logout
        driver.findElement(By.xpath("//div[@class=\"dropdown employee-dropdown\"]")).click();
        driver.findElement(By.id("header_logout")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("controller=AdminLogin"));

        // back to previous page
        driver.navigate().back();
        Assert.assertFalse(driver.getCurrentUrl().contains("/sell/catalog/products"));

    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

}
