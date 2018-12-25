package webTests;

import io.qameta.allure.Step;
import pageObjects.Pages.HomePage;
import tools.browserSetUp.ChromeDriverManager;
import tools.browserSetUp.DriverManager;
import tools.browserSetUp.DriverManagerFactory;
import tools.browserSetUp.DriverType;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import java.util.concurrent.TimeUnit;

public class BaseTest extends ChromeDriverManager {
    DriverManager driverManager;
    public WebDriver driver;

    @BeforeTest
    public void beforeTest() {
        driverManager = DriverManagerFactory.getManager(DriverType.CHROME);
    }

    @BeforeClass
    public void beforeClass() {
        driver=driverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Step("Set the site URL address")
    public HomePage openHomePage(){
        driver.get("http://www.ia.ca/individuals");
        return new HomePage(driver);
    }

    @Step ("Quite the driver and close the browser after each test class execution")
    @AfterClass
    public void afterClass() {
        driverManager.quitDriver();
    }
}
