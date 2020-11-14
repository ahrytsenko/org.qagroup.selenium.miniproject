import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class InspectionGithub {
    private WebDriver driver;

    public InspectionGithub(WebDriver driver) {
        this.driver = driver;
    }

    By accountNameField = By.xpath("");

    public InspectionGithub checkTheAccountNameIs(String text) {
        //Assert.assertEquals(text);
        return this;
    }

}
