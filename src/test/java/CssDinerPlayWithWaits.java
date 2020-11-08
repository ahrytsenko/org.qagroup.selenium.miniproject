import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CssDinerPlayWithWaits {
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.gekodriver", "src\\main\\java\\gekodriver.exe");
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");

        driver = new FirefoxDriver(options);
        driver.get("https://flakeout.github.io");
        driver.manage().window().setSize(new Dimension(1024, 768));
    }

    @AfterClass
    public void teardown() { driver.quit(); }

    @Test
    public void letsPlay() {
        System.out.println();

        // //div[contains(@class,'enter-button')]

        // task
        // field for answer

        //waitAfterIntputAnswer

        // TODO: пройти гру до 32 рівня
        // TODO: пройдення ведення гри
        // TODO: запушити на репозиторій результат
        // TODO:
        // TODO:


    }
}
