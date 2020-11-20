import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JuiceLoginPage {
    private WebDriver driver;

    By imgLogo = By.xpath("//img[@class='logo']");
    By navbarAccount = By.xpath("//button[@id='navbarAccount']");
    By navbarLoginButton = By.xpath("//button[@id='navbarLoginButton']");
    By h1Login = By.xpath("//h1[contains(text(), 'Login')]");
    By emailField = By.xpath("//input[@id='email']");
    By passwordField = By.xpath("//input[@id='password']");
    By loginButton = By.xpath("//button[@id='loginButton']");
    By welcomeBanner = By.xpath("//app-welcome-banner");
    By closeBannerButton = By.xpath("//button[@aria-label='Close Welcome Banner']");


    public JuiceLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public JuiceLoginPage closeBanner(){
        if (driver.findElement(welcomeBanner).isDisplayed()) {
            driver.findElement(closeBannerButton).click();
        }
        return this;
    }

    public JuiceLoginPage checkLogoPresence() {
        //Assert.assertTrue(driver.findElement(imgLogo));
        return this;
    }

    public JuiceLoginPage clickNavbarAccount() {
        new WebDriverWait(driver, 10).until(
                ExpectedConditions.presenceOfElementLocated(navbarAccount));
        driver.findElement(navbarAccount).click();
        return this;
    }

    public JuiceLoginPage clickNavbarLoginButton() {
        new WebDriverWait(driver, 10).until(
                ExpectedConditions.presenceOfElementLocated(navbarLoginButton));
        driver.findElement(navbarLoginButton).click();
        return this;
    }

    public JuiceLoginPage waitForLoginWindow(String baseURL) {
        new WebDriverWait(driver, 15).until(
                ExpectedConditions.urlContains(baseURL + "#/login"));
        new WebDriverWait(driver, 10).until(
                ExpectedConditions.presenceOfElementLocated(h1Login));
        return this;
    }

    public JuiceLoginPage fillEmailField(String email) {
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    public JuiceLoginPage fillPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    public JuiceLoginPage waitForLoginButtonEnabled() {
        new WebDriverWait(driver, 10).until(
                ExpectedConditions.elementToBeClickable(driver.findElement(loginButton)));
        return this;
    }

    public JuiceLoginPage clickLoginButton() {
        driver.findElement(loginButton).click();
        return this;
    }

}
