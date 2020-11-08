import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CssDinerHomeWork {
    private WebDriver driver;
    private final String URL = "https://flukeout.github.io/";
    private String[] solutions = new String[]
            {
                    "Solutions list:",
                    "plate",                        // 01
                    "bento",                        // 02
                    "plate#fancy",                  // 03
                    "plate apple",                  // 04
                    "plate#fancy pickle",           // 05
                    "apple.small",                  // 06
                    "orange.small",                 // 07
                    "bento orange.small",           // 08
                    "plate, bento",                 // 09
                    "*",                            // 10
                    "plate *",                      // 11
                    "plate + apple",                // 12
                    "bento ~ pickle",               // 13
                    "plate > apple",                // 14
                    "plate > orange:first-child",   // 15
                    "plate apple:only-child, plate pickle:only-child",  // 16
                    "",                     // 17
                    "",                     // 18
                    "",                     // 19
                    "",                     // 20
                    "",                     // 21
                    "",                     // 22
                    "",                     // 23
                    "",                     // 24
                    "",                     // 25
                    "",                     // 26
                    "",                     // 27
                    "",                     // 28
                    "",                     // 29
                    "",                     // 30
                    "",                     // 31
                    "",                     // 32

            };

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "src\\main\\java\\geckodriver.exe");
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
        driver = new FirefoxDriver(options);
        driver.get(URL);
        driver.manage().window().setSize(new Dimension(1000, 600));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterTest
    public void tearDown() {
        //driver.quit();
    }

    @Test
    public void playGame() {
        // define key variables

        WebDriverWait wait = new WebDriverWait(driver, 10);

        System.out.println("GAME: " + driver.getTitle());
        System.out.println("Let's start\n");

        //for (int i = 1; i < solutions.length; i++) {
        for (int i = 1; i < 17; i++) {
            // Display current task and solution
            System.out.println(driver.findElement(By.xpath("//span[@class='level-text']")).getText());
            wait.until(ExpectedConditions.attributeToBe(driver.findElement(By.xpath("//input[@type='text']")),
                    "placeholder", "Type in a CSS selector"));
            System.out.println("Task is: " + driver.findElement(By.cssSelector("h2.order")).getText());
            System.out.println("Solution is: " + solutions[i]);

            // Type the solution
            wait.until(ExpectedConditions.attributeToBe(driver.findElement(By.xpath("//input[@type='text']")),
                    "placeholder", "Type in a CSS selector"));
            driver.findElement(By.xpath("//input[@type='text']")).sendKeys(solutions[i]);

            // Submit the solution
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(
                    By.xpath("//div[contains(@class, 'enter-button')]"))));
            driver.findElement(By.xpath("//div[contains(@class, 'enter-button')]")).click();

            // Check if solution completed
            wait.until(ExpectedConditions.attributeContains(driver.findElement(By.tagName("h1")),
                    "class","completed"));
            Assert.assertTrue(driver.findElement(By.tagName("h1")).getAttribute("class").contains("completed"));

            // Check if level changed
            wait.until(ExpectedConditions.textToBe(By.xpath("//span[@class='level-text']"),
                    "Level " + Integer.toString(i+1) + " of 32"));
            Assert.assertEquals(driver.findElement(By.xpath("//span[@class='level-text']")).getText(),
                    "Level " + Integer.toString(i+1) + " of 32");

            System.out.println("OK\n");

        }
    }
}
