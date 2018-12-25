package webTests;

import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import pageObjects.Pages.HomePage;
import pageObjects.Pages.MortgagePage;
import org.testng.Assert;
import org.testng.annotations.Test;

@Feature("Verify Mortgage Payment Value")
@Story("Mortgage Payment")
public class MortgagePaymentTests extends BaseTest {
    MortgagePage mortgagePage;
    HomePage homePage;


    @Step("Navigate on Mortgage Payment page and move the Purchase slider to right")
    @Test(enabled = true, description = "Scenario to check if there is possibility to move Purchase slider")
    public void checkPurchasePriceSlider() {
        homePage = openHomePage();
        mortgagePage = homePage.navigateToMortagePaymentPage();
        mortgagePage.movePriceSliderToRight();

        Assert.assertTrue(mortgagePage.isPurchasePriceMove("left: 100"),
                "Purchase Price slider does not work");
    }

    @Step("Populate values at Mortgage Payments page and pull Weekly Payment value")
    @Test(enabled = true, description = "Scenario to check if the correct Weekly Payment value is pulled")
    public void checkWeeklyPaymentsValue() {
        mortgagePage = homePage.navigateToMortagePaymentPage();
        mortgagePage.populateValuesForMortgagePaymentCalculator("500000",
                "100000", "15 years", "weekly", "5");

        Assert.assertTrue(mortgagePage.isWeeklyPaymentEqauls("$726.35"), "Incorrect Weekly Payment value is pulled");
    }
}