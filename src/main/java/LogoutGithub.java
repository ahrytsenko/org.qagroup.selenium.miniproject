import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogoutGithub {
    private WebDriver driver;

    public LogoutGithub(WebDriver driver) {
        this.driver = driver;
    }

    By headerButton = By.xpath("//summary[@aria-....]");
    By signOutItemFormList = By.xpath("//button[contains(@class, 'drop....')]");
    By nameInDropDownList = By.xpath("//strong[@class='']");

    public LogoutGithub headerButtonClick() {
        driver.findElement(headerButton).click();
        return this;
    }

    public LogoutGithub signOutItemFormListClick() {
        driver.findElement(signOutItemFormList).click();
        return this;
    }

    public LogoutGithub waitForPageTitle() {
        new WebDriverWait(driver, 10).until(
                ExpectedConditions.textToBePresentInElement(
                        driver.findElement(By.xpath("//div[@class='py-6 py-sm-8....']")), "Built for Developers")
        );
        return this;
    }

    public LogoutGithub waitForNameInDropDownList(String text) {
        new WebDriverWait(driver, 10).until(
                ExpectedConditions.textToBePresentInElement(
                        driver.findElement(nameInDropDownList), text));
        return this;
    }

    public LogoutGithub logoutUserFromAccount(String text) {
        headerButtonClick();
        waitForNameInDropDownList(text);
        signOutItemFormListClick();
        waitForPageTitle();
        return this;
    }

}
