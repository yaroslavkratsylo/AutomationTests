package pageObjects.Pages;

import io.qameta.allure.Step;
import tools.general.GlobalMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;
    GlobalMethods globalMethods;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        globalMethods = new GlobalMethods(driver);
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath = ".//div[@class='navbar-sub-main']//i[@class='icone-menu-prets']")
    private WebElement loansLink;

    @FindBy(xpath = ".//div[@class='navbar-sub-main']//ul[@class='dropdown-menu dropdown-menu-large']//a[@href='/mortgage']")
    private WebElement mortgagesLink;

    @FindBy(xpath = ".//div[@id='grille-zone-capacite-versements']//h3[.= 'Mortgage payment']")
    private WebElement mortgagePaymentLink;

    @FindBy(xpath = ".//div[@class='col-1-1']//div[@class='col-2-2 col-lg-1-2 field-input']//label[contains(text(),'Purchase price')]")
    private WebElement selectPurchasePriceRadioButton;

    @Step("Navigate on Mortgage Payment page")
    public MortgagePage navigateToMortagePaymentPage() {
        globalMethods.clickTheElement(loansLink);
        globalMethods.clickTheElement(mortgagesLink);
        globalMethods.clickTheElement(mortgagePaymentLink);
        globalMethods.clickTheElement(selectPurchasePriceRadioButton);
        return new MortgagePage(driver);
    }
}
