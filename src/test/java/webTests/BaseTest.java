package webTests;

import org.testng.annotations.BeforeMethod;
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
    public void setDriver() {
        driver = driverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    public HomePage openLoginPage(){
        driver.get("http://www.ia.ca/individuals");
        return new HomePage(driver);
    }

    @AfterClass
    public void afterMethod() {
        driverManager.quitDriver();
    }
}
