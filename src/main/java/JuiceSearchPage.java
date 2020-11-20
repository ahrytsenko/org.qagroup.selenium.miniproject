import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class JuiceSearchPage {
    private WebDriver driver;

    By itemsInBasket = By.xpath("//button[@routerlink='/basket']//span[contains(@class, 'warn-notification')]");
    By addToBasketButtons = By.xpath("//button[@aria-label='Add to Basket']");
    By basketButton = By.xpath("//button[@routerlink='/basket']");
    By matGridList = By.xpath("//mat-grid-list");
    By navbarAccount = By.xpath("//button[@id='navbarAccount']");
    By navbarLogoutButton = By.xpath("//button[@id='navbarLogoutButton']");

    public JuiceSearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public JuiceSearchPage waitForItemsWereShown(){
        new WebDriverWait(driver, 10).until(
                ExpectedConditions.presenceOfElementLocated(matGridList));
        return this;
    }

    public JuiceSearchPage cleanupBasket() {
        if (!driver.findElement(itemsInBasket).getText().equals("0")) {
            // TODO: cleanup the basket
        }
        return this;
    }

    public JuiceSearchPage waitForAddToBasketButtonBecomeClickable(int item){
        new WebDriverWait(driver, 10).until(
                ExpectedConditions.elementToBeClickable(driver.findElements(addToBasketButtons).get(item)));
        Assert.assertTrue(driver.findElements(addToBasketButtons).get(item).isEnabled());
        return this;
    }

    public JuiceSearchPage addAnItemIntoTheBasket(int item) {
        Assert.assertFalse(driver.findElements(addToBasketButtons).isEmpty());
        driver.findElements(addToBasketButtons).get(item).click();
        return this;
    }

    public JuiceSearchPage goToBasket() {
        driver.findElement(basketButton).click();
        return this;
    }

    public JuiceSearchPage clickNavbarLogoutButton() {
        new WebDriverWait(driver, 10).until(
                ExpectedConditions.presenceOfElementLocated(navbarLogoutButton));
        driver.findElement(navbarLogoutButton).click();
        return this;
    }

}
