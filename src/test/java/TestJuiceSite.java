import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestJuiceSite {
    private WebDriver driver;
    private JuiceLoginPage loginPage;
    private JuiceSearchPage searchPage;

    private final String URL = "https://juice-shop.herokuapp.com/";

    @BeforeClass
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.get(URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        loginPage = new JuiceLoginPage(driver);
        searchPage = new JuiceSearchPage(driver);
    }

    @AfterClass
    public void teardown() { driver.quit(); }

    public void logout() {
        loginPage.clickNavbarAccount();
        searchPage.clickNavbarLogoutButton();
    }

    @Test(priority = 1)
    public void closeWelcomeBanner() {
        loginPage.closeBanner();
    }

    @Test(priority = 2)
    public void testForSQLInjectionOnLoginPage() {
        loginPage.clickNavbarAccount()
                .clickNavbarLoginButton()
                .waitForLoginWindow(URL)
                .fillEmailField("' OR 1=1 --")
                .fillPasswordField("123")
                .waitForLoginButtonEnabled()
                .clickLoginButton();
        new WebDriverWait(driver, 10).until(
                ExpectedConditions.urlContains("#/search"));
        Assert.assertTrue(driver.getCurrentUrl().contains("#/search"));
        this.logout();
    }

    @Test(priority = 3)
    public void testForValidCredentialsLoginPage() {
        loginPage.clickNavbarAccount()
                .clickNavbarLoginButton()
                .waitForLoginWindow(URL)
                .fillEmailField("email@domain.example")
                .fillPasswordField("12345")
                .waitForLoginButtonEnabled()
                .clickLoginButton();
        new WebDriverWait(driver, 10).until(
                ExpectedConditions.urlContains("#/search"));
        Assert.assertTrue(driver.getCurrentUrl().contains("#/search"));
    }

    @Test(priority = 20)
    public void selectProductForPurchase() {
        searchPage.cleanupBasket()
                .waitForItemsWereShown()
                .waitForAddToBasketButtonBecomeClickable(1)
                .addAnItemIntoTheBasket(1)
                .goToBasket();
        new WebDriverWait(driver, 10).until(
                ExpectedConditions.urlContains("#/basket"));
        Assert.assertTrue(driver.getCurrentUrl().contains("#/basket"));
        this.logout();
    }


}
