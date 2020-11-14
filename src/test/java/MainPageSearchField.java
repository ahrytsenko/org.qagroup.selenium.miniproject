import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class MainPageSearchField {
    private WebDriver driver;
    private final String URL = "http://rozetka.com.ua";
    private MainPageSearch mainSearch;

    @BeforeTest
    public void setup() {
        // Disable pop-ups
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");

        // go to website
        driver = new ChromeDriver(options);
        driver.get(URL);
        driver.manage().window().maximize();

        // implicit waits
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        mainSearch = new MainPageSearch(driver);
    }

    @AfterTest
    public void teardown() { driver.quit(); }

    @Test(priority = 1)
    public void findValidValue() {
        // MainPageSearch mainSearch = new MainPageSearch(driver);
        mainSearch.searchFieldInput("пылесос");
        mainSearch.searchButtonClick();
        mainSearch.waitForPageTitle("Пылесосы");
        mainSearch.checkTheFirstElementIs("Пылесос");
    }

    @Test(priority = 2)
    public void findInvalidValue() {
        // MainPageSearch mainSearch = new MainPageSearch(driver);
        mainSearch.searchFieldInput("::::");
        mainSearch.searchButtonClick();
        mainSearch.waitForPageTitle("::::");
        mainSearch.checkTheFirstElementIs("::::");
    }

    @Test(priority = 3)
    public void findValueNotFormBreadCrumbs() {
        // MainPageSearch mainSearch = new MainPageSearch(driver);
        mainSearch.searchFieldInput("чупакабра");
        mainSearch.searchButtonClick();
        mainSearch.waitForPageTitle("чупакабра");
        mainSearch.checkTheFirstElementIs("чупакабра");
        mainSearch.checkFindItemsText("чупакабра");
    }
}
