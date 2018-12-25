package pageObjects.Pages;

import io.qameta.allure.Step;
import tools.general.GlobalMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MortgagePage {
    GlobalMethods globalMethods;
    WebDriver driver;


    public MortgagePage(WebDriver driver) {
        this.driver = driver;
        globalMethods = new GlobalMethods(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//div[@class='slider-handle min-slider-handle custom'])[1]")
    private WebElement priceSliderLocator;

    @FindBy(xpath = ".//button[@id='PrixProprietePlus']")
    private WebElement purchasePricePlusButton;

    @FindBy(xpath = ".//button[@id='MiseDeFondPlus']")
    private WebElement downPaymentPlusButton;

    @FindBy(xpath = ".//input[@id='sliderPrixPropriete']")
    private WebElement sliderPriceValue;

    @FindBy(xpath = ".//input[@id='sliderMiseDeFond']")
    private WebElement sliderDownPaymentValue;

    @FindBy(xpath = ".//div[@class='col-med-1-2 col-lg-1-3']//div[@class='selectric']/b")
    private WebElement amortizationDropdown;

    @FindBy(xpath = "(.//div[@class='selectric']//b)[2]")
    private WebElement paymentFrequencyDropdown;

    @FindBy(xpath = ".//input[@id='TauxInteret']")
    private WebElement interestRateField;

    @FindBy(xpath = ".//button[@id='btn_calculer']")
    private WebElement calculateButton;

    @FindBy(xpath = ".//span[@id='paiement-resultats']")
    private WebElement weeklyPaymentValue;

    @FindBy(xpath = "(.//div[@class='selectric-items']/div[@class='selectric-scroll'])[1]")
    private WebElement amortizationDropDownField;

    @FindBy(xpath = "(.//div[@class='selectric-items']/div[@class='selectric-scroll'])[2]")
    private WebElement paymentFrequencyDropDownField;

    public WebElement getPriceSlider() {
        return priceSliderLocator;
    }

    @Step("Move Purchase Price slider")
    public MortgagePage movePriceSliderToRight() {
       globalMethods.moveSlider(priceSliderLocator,100);
       return this;
    }

    @Step("Check if the Purchase Price is changing together with Purchase Price slider")
    public boolean isPurchasePriceMove(String purchaseValue) {
        return (getPriceSlider().getAttribute("style").contains(purchaseValue));
    }

    @Step("Check if the Weekly Payment value contains proper value")
    public boolean isWeeklyPaymentEqauls(String weeklyValue) {
        return (globalMethods.getTextValue(getWeeklyPaymentValue()).equals(weeklyValue));
    }

    @Step("Get the Weekly Payment value")
    public WebElement getWeeklyPaymentValue() {
        return weeklyPaymentValue;
    }

    @Step("Change the price on a slider using plus button")
    public MortgagePage changePriceUsingPlusButton(String inputPrice, WebElement sliderPrice, WebElement plusButton) {
        for (int i = 0; i < 20; i++) {
            if (inputPrice.equals(sliderPrice.getAttribute("value"))) {
                break;
            } else {
                plusButton.click();
            }
            if (i == 19) {
                throw new IllegalStateException("Price value hasn't been changed to [" + inputPrice + "] after trying to click on Plus button");
            }
        }
        return new MortgagePage(driver);
    }

    @Step("Populate all required values for Mortgage Payment Calculator")
    public MortgagePage populateValuesForMortgagePaymentCalculator(String inputPurchasePrice, String inputDownPayment, String authorizationValue,
                                                                   String paymentFrequencyValue, String interestValue) {
        changePriceUsingPlusButton(inputPurchasePrice, sliderPriceValue, purchasePricePlusButton);
        changePriceUsingPlusButton(inputDownPayment, sliderDownPaymentValue, downPaymentPlusButton);
        amortizationDropdown.click();
        globalMethods.selectValueFromDropdownByLiTag(amortizationDropDownField, authorizationValue);
        paymentFrequencyDropdown.click();
        globalMethods.selectValueFromDropdownByLiTag(paymentFrequencyDropDownField, paymentFrequencyValue);
        globalMethods.clearAndPopulateInputField(interestRateField, interestValue);
        globalMethods.clickAnElement(calculateButton);
        return new MortgagePage(driver);
    }
}
