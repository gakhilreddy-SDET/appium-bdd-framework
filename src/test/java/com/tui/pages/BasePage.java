package com.tui.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.tui.driver.DriverFactory;
import com.tui.utils.GestureUtils;
import com.tui.utils.WaitUtils;
import java.time.Duration;

public class BasePage {
    protected AppiumDriver driver;
    protected WaitUtils waitUtils;
    protected GestureUtils gestureUtils;

    public BasePage() {
        this.driver = DriverFactory.getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
        this.waitUtils = new WaitUtils(driver);
        this.gestureUtils = new GestureUtils(driver);
    }

    public void click(WebElement element) {
        waitUtils.waitForClickable(element);
        element.click();
    }

    public void sendKeys(WebElement element, String text) {
        waitUtils.waitForVisibility(element);
        element.clear();
        element.sendKeys(text);
    }

    public String getText(WebElement element) {
        waitUtils.waitForVisibility(element);
        return element.getText();
    }

    public void swipeUp() {
        gestureUtils.swipeUp();
    }

    public void scrollToElement(WebElement element) {
        // Implement scroll to element
    }

    public void waitForVisibility(WebElement element) {
        waitUtils.waitForVisibility(element);
    }

    public WebElement waitForElementToBeClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
}