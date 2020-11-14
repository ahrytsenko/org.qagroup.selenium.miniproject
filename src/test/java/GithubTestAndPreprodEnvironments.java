import com.sun.corba.se.impl.resolver.SplitLocalResolverImpl;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GithubTestAndPreprodEnvironments {
    private WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://github.com");
        driver.manage().window().maximize();
    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }

    @Test(priority = 1)
    public void loginInto() {
        LoginGithub loginGithub = new LoginGithub(driver);
        loginGithub.loginUserIntoAccount("", "");
    }

    @Test(priority = 2)
    public void inspectMainPage() {
        InspectionGithub inspectionGithub = new InspectionGithub(driver);
        inspectionGithub.checkTheAccountNameIs("");
    }

    @Test(priority = 3)
    public void logoutFromGithub() {
        LogoutGithub logoutGithub = new LogoutGithub(driver);
        logoutGithub.logoutUserFromAccount("");
    }

}

