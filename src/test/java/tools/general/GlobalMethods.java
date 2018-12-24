package tools.general;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class GlobalMethods {
    WebDriver driver;

    public GlobalMethods(WebDriver driver) {
        this.driver = driver;
    }

    public void clearAndPopulateInputField(WebElement element, String inputValue) {
        element.clear();
        element.sendKeys(inputValue);
    }

    public String getTextValue(WebElement element) {
        waitForElementVisible(element, 500);
        return element.getText();
    }

    public void waitForElementVisible(WebElement element, long waitTime) {
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void clickTheElement(WebElement element) {
        waitForElementVisible(element, 1000);
        element.click();
    }

    public void moveSlider(WebElement priceSliderLocator, int value) {
        clickTheElement(priceSliderLocator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'left: " + value + "%')", priceSliderLocator);
        priceSliderLocator.click();
    }

    public void selectValueFromDropdownByLiTag(WebElement element, String dropvalue) {
        List<WebElement> options = element.findElements(By.tagName("li"));

        for (WebElement option : options) {
            if (dropvalue.equals(option.getText()))
                option.click();
        }
    }
}
