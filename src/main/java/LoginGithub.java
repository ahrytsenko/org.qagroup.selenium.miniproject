import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginGithub {
    private WebDriver driver;

    public LoginGithub(WebDriver driver) {
        this.driver = driver;
    }

    By signInButtonPage = By.xpath("//a[contains(@data-ga-click, 'clicked Sign in')]");
    By loginField = By.xpath("//button[text()=' Найти ']");
    By passwordField = By.xpath("");
    By signInButtonAuthForm = By.xpath("//div[@class='central-wrapper']//div[@class='layout']");

    public LoginGithub signInButtonClick() {
        driver.findElement(signInButtonPage).click();
        return this;
    }

    public LoginGithub waitForPageTitle() {
        new WebDriverWait(driver, 10).until(
                ExpectedConditions.textToBePresentInElement(
                        driver.findElement(By.xpath("//div[@class='']/h1")), "Sign In Github")
        );
        return this;
    }

    public LoginGithub fillLoginField(String login) {
        driver.findElement(loginField).sendKeys(login);
        return this;
    }

    public LoginGithub fillPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    public LoginGithub clockSignInButtonOnAuthForm() {
        driver.findElement(signInButtonAuthForm).click();
        return this;
    }

    public LoginGithub loginUserIntoAccount(String login, String password) {
        signInButtonClick();
        waitForPageTitle();
        fillLoginField(login);
        fillPasswordField(password);
        clockSignInButtonOnAuthForm();
        return this;
    }



}
